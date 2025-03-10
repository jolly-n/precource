package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumbersComparatorTest {

    private final NumbersComparator comparator = new NumbersComparator();
    private final Computer computer = new Computer(Arrays.asList(1, 2, 3));

    @DisplayName("몇 개의 수가 같은지 비교한다.")
    @CsvSource({
            "1, 2, 3, 3",
            "2, 3, 4, 2",
            "3, 4, 5, 1",
            "5, 6, 7, 0"
    })
    @ParameterizedTest
    void compareSameNumber(int number1, int number2, int number3, int expected) {
        Player player = new Player(Arrays.asList(number1, number2, number3));
        int actual = comparator.compareSameNumber(computer, player);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("몇 개의 수가 같은 자리에 있는지 비교한다.")
    @CsvSource({
            "1, 3, 2, 1",
            "1, 2, 4, 2",
            "1, 2, 3, 3 ",
            "2, 3, 1, 0"
    })
    @ParameterizedTest
    void compareSamePlace(int number1, int number2, int number3, int expected) {
        Player player = new Player(Arrays.asList(number1, number2, number3));
        int actual = comparator.compareSamePlace(computer, player);
        assertThat(actual).isEqualTo(expected);
    }
}
