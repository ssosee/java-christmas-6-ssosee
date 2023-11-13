package christmas.domain;

import christmas.domain.constant.ChristmasEventConstant;

public class ChristmasEvent extends Event {

    public ChristmasEvent(OrderMenu orderMenu) {
        super(orderMenu);
    }

    @Override
    public int discount(int day) {
        if (Date.isChristmasEvent(day)) {
            return ChristmasEventConstant.DEFAULT_DISCOUNT + ChristmasEventConstant.DAILY_INCREASE_DISCOUNT * (day - 1);
        }
        return 0;
    }
}
