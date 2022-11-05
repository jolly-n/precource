package baseball.validation;

import java.util.HashMap;
import java.util.Map;

public class InputValidation {
    public boolean isNotThreeLetters(String input) {
        return input.length() != 3;
    }

    public boolean isNot1To9(String input) {
        char[] chars = input.toCharArray();

        for (char letter : chars) {
            if (!('1' <= letter && letter <= '9')) {
                return true;
            }
        }

        return false;
    }

    public boolean hasDuplicatedValues(String input) {
        Map<String, Integer> map = new HashMap<>();

        for (String letter : input.split("")) {
            map.put(letter, map.getOrDefault(letter, 0) + 1);

            if (map.get(letter) >= 2) {
                return true;
            }
        }

        return false;
    }

    public boolean isNotCorrectChoice(String input) {
        return !(input.equals("1") || input.equals("2"));
    }
}
