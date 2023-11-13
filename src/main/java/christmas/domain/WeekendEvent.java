package christmas.domain;

import christmas.domain.constant.WeekendEventConstant;

public class WeekendEvent extends Event {
    public WeekendEvent(OrderMenu orderMenu) {
        super(orderMenu);
    }

    @Override
    public int discount(int day) {
        if (Date.isWeekendEvent(day)) {
            int count = super.getTotalMenuCountByMenuCategory(MenuCategory.MAIN_COURSE);
            return count * WeekendEventConstant.DISCOUNT;
        }
        return 0;
    }
}
