package christmas.view;

public class InputViewValidator {

    private static final String EMPTY = "";
    private static final String DIGIT_REGEX = "[1-9]";
    private static final String EMPTY_ERROR_MESSAGE = "[ERROR] 입력된 값이 없습니다. 다시 입력해 주세요.";
    private static final String DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public void validateEmpty(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    public void validateDigit(String input) {
        if (removeDigit(input).length() > 0) {
            throw new IllegalArgumentException(DATE_ERROR_MESSAGE);
        }
    }

    private String removeDigit(String input) {
        return input.replaceAll(DIGIT_REGEX, EMPTY);
    }
}
