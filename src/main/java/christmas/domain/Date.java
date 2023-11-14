package christmas.domain;

import christmas.domain.constant.ChristmasEventConstant;
import christmas.validation.DateValidationHandler;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private final int day;
    private final DateValidationHandler dateValidationHandler;

    public Date(String readDate, DateValidationHandler dateValidationHandler) {
        this.dateValidationHandler = dateValidationHandler;

        validation(readDate);

        this.day = Integer.parseInt(readDate);
    }

    public static boolean isChristmasEvent(int day) {
        return day >= ChristmasEventConstant.EVENT_START_DAY && day <= ChristmasEventConstant.EVENT_END_DAY;
    }

    public static boolean isWeekdayEvent(int day) {
        LocalDate localDate = LocalDate.of(Event.EVENT_YEAR, Event.EVENT_MONTH, day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return !DayOfWeek.FRIDAY.equals(dayOfWeek) && !DayOfWeek.SATURDAY.equals(dayOfWeek);
    }

    public static boolean isWeekendEvent(int day) {
        LocalDate localDate = LocalDate.of(Event.EVENT_YEAR, Event.EVENT_MONTH, day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return DayOfWeek.FRIDAY.equals(dayOfWeek) || DayOfWeek.SATURDAY.equals(dayOfWeek);
    }

    public static boolean isSpecialEvent(int day) {
        return Event.SPECIAL_DAYS.stream()
                .anyMatch(specialDay -> specialDay == day);
    }

    private void validation(String date) {
        this.dateValidationHandler.validationHasText(date);
        this.dateValidationHandler.validationNumeric(date);
        this.dateValidationHandler.validationRange(Integer.parseInt(date));
    }

    public int getDay() {
        return day;
    }
}
