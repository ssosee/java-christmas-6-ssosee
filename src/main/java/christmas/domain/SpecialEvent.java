package christmas.domain;

import christmas.domain.constant.SpecialEventConstant;

public class SpecialEvent extends Event {
    public SpecialEvent(OrderMenu orderMenu) {
        super(orderMenu);
    }

    @Override
    int discount(int day) {
        if (Date.isSpecialEvent(day)) {
            return SpecialEventConstant.DISCOUNT;
        }
        return 0;
    }
}
