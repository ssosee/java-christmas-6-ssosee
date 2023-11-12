package christmas.domain;

import christmas.validation.DateValidationHandler;

public class Date {
    private final int date;
    private final DateValidationHandler dateValidationHandler;

    public Date(String readDate, DateValidationHandler dateValidationHandler) {
        this.dateValidationHandler = dateValidationHandler;

        validation(readDate);

        this.date = Integer.parseInt(readDate);
    }

    public int getDate() {
        return date;
    }

    private void validation(String date) {
        this.dateValidationHandler.validationNumeric(date);
        this.dateValidationHandler.validationRange(Integer.parseInt(date));
    }
}
