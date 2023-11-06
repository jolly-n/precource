package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    InputValidator inputValidator = new InputValidator();

    @Test
    @DisplayName("사용자 입력 값이 없을 경우 예외가 발생한다.")
    void inputEmptyEx() {
        assertThatThrownBy(() -> inputValidator.validateNotEmpty(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "가", "!"})
    @DisplayName("사용자 입력 값이 숫자가 아닐 경우 예외가 발생한다.")
    void inputNotNumberEx(String inputValue) {
        assertThatThrownBy(() -> inputValidator.validateNumberFormat(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
