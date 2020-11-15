package com.jaeeyeonling.baseball.referee;

import com.jaeeyeonling.baseball.ball.Balls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("볼 판정에 대해 테스트한다.")
class BallRuleTest {

    private JudgeRule judgeRule;

    @BeforeEach
    void setUp() {
        judgeRule = BallRule.getInstance();
    }

    @DisplayName("동일한 볼일 경우 볼로 판정하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"123", "234", "543"})
    void isStrike(final String value) {
        // given
        final var myBall = Balls.of(value);
        final var otherBall = Balls.of(value);

        for (var i = 0; i < Balls.SIZE; i++) {
            // when
            final var isBall = judgeRule.judges(myBall, otherBall, i);

            // then
            assertThat(isBall).isFalse();
        }
    }

    @DisplayName("다른 위치에 동일한 볼이 존재할 경우 볼로 판정한다.")
    @ParameterizedTest
    @CsvSource({
            "123,231",
            "468,684",
            "321,213"
    })
    void isBall(final String rawMyBall,
                final String rawOtherBall) {
        // given
        final var myBall = Balls.of(rawMyBall);
        final var otherBall = Balls.of(rawOtherBall);

        for (var i = 0; i < Balls.SIZE; i++) {
            // when
            final var isBall = judgeRule.judges(myBall, otherBall, i);

            // then
            assertThat(isBall).isTrue();
        }
    }

    @DisplayName("동일한 볼이 존재하지 않을 경우 볼로 판정하지 않는다.")
    @ParameterizedTest
    @CsvSource({
            "123,456",
            "456,789",
            "234,986",
    })
    void notBall(final String rawMyBall,
                 final String rawOtherBall) {
        // given
        final var myBall = Balls.of(rawMyBall);
        final var otherBall = Balls.of(rawOtherBall);

        for (var i = 0; i < Balls.SIZE; i++) {
            // when
            final var isBall = judgeRule.judges(myBall, otherBall, i);

            // then
            assertThat(isBall).isFalse();
        }
    }
}