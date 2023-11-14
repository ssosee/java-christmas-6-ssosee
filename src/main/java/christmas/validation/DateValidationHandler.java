package christmas.validation;

import christmas.domain.constant.DateConstant;
import christmas.utils.StringUtils;

public class DateValidationHandler {

    public static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public void validationHasText(String date) {
        if (!StringUtils.hasText(date)) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public void validationRange(int date) {
        if (!isRange(date)) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public void validationNumeric(String date) {
        if (!StringUtils.isNumeric(date)) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    private boolean isRange(int date) {
        return date <= DateConstant.MAX_DATE_RANGE && date >= DateConstant.MIN_DATE_RANGE;
    }
}
