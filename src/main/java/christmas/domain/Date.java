package christmas.domain;

import christmas.domain.constant.DateConstant;

public class Date {
    private final int date;

    public Date(String date) {
        validation(date);
        this.date = Integer.parseInt(date);
    }

    public int getDate() {
        return date;
    }

    private void validation(String date) {
        validationNumeric(date);
        validationRange(Integer.parseInt(date));
    }

    private void validationRange(int date) {
        if(!isRange(date)) {
            throw new IllegalArgumentException(DateConstant.INVALID_DATE_MESSAGE);
        }
    }

    private boolean isRange(int date) {
        return date <= DateConstant.MAX_DATE_RANGE && date >= DateConstant.MIN_DATE_RANGE;
    }

    private void validationNumeric(String date) {
        if(!isNumeric(date)) {
            throw new IllegalArgumentException(DateConstant.INVALID_DATE_MESSAGE);
        }
    }

    private boolean isNumeric(String date) {
        return date.chars().allMatch(Character::isDigit);
    }
}
