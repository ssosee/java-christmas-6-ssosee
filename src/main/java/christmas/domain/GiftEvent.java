package christmas.domain;

import christmas.domain.constant.GiftEventConstant;

public class GiftEvent extends Event {
    public GiftEvent(OrderMenu orderMenu) {
        super(orderMenu);
    }

    /**
     * 실제 금액에서 할인되지는 않음 할인 금액은 혜택 내역에 사용
     */
    @Override
    int discount(int day) {
        int totalOrderMenuPrice = super.getTotalOrderMenuPrice();
        if (totalOrderMenuPrice >= GiftEventConstant.MIN_TOTAL_ORDER_AMOUNT_FOR_FREE_CHAMPAGNE) {
            return GiftEventConstant.DISCOUNT;
        }
        return 0;
    }
}