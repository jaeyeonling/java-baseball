package com.jaeyeonling.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Set 클래스에 대해 테스트한다.")
class SetTest {

    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>(3);
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @DisplayName("Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.")
    @Test
    void size() {
        // when
        final var size = numbers.size();

        // then
        assertThat(size).isEqualTo(3);
    }

    @DisplayName("Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void contains(final int number) {
        // when
        final var contains = numbers.contains(number);

        // then
        assertThat(contains).isTrue();
    }

    @DisplayName("contains() 메서드를 입력 값에 따라 결과 값이 다른 경우에 대한 테스트도 가능하도록 구현한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "2,true", "3,true", "4,false", "5,false"})
    void contains(final int number,
                  final boolean expect) {
        // when
        final var contains = numbers.contains(number);

        // then
        assertThat(contains).isEqualTo(expect);
    }
}
