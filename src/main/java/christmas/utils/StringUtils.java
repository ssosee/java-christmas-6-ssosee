package christmas.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static final String COMMA = ",";
    public static final String HYPHEN = "-";

    private StringUtils() {
    }

    public static List<String> splitCommaToListString(String str) {
        return Arrays.stream(str.split(COMMA))
                .toList();
    }

    public static List<String> splitHyphenToListString(String str) {
        return Arrays.stream(str.split(HYPHEN))
                .toList();
    }

    public static boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    public static boolean hasText(String str) {
        if (str.isBlank() || str.isEmpty()) {
            return false;
        }
        return true;
    }
}
