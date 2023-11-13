package christmas.domain;

import christmas.domain.constant.WeekdayEventConstant;

public class WeekdayEvent extends Event {

    public WeekdayEvent(OrderMenu orderMenu) {
        super(orderMenu);
    }

    @Override
    public int discount(int day) {
        if (Date.isWeekdayEvent(day)) {
            int count = super.getTotalMenuCountByMenuCategory(MenuCategory.DESERT);
            return count * WeekdayEventConstant.DISCOUNT;
        }
        return 0;
    }
}
