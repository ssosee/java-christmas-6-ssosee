package christmas.domain;

import java.util.List;

public abstract class Event {
    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    public static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    public static final int NO_DISCOUNT = 0;
    public static final int MIN_TOTAL_ORDER_AMOUNT_FOR_EVENT = 10_000;

    private final OrderMenu orderMenu;

    public Event(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public abstract int discount(int day);

    public int getTotalMenuCountByMenuCategory(MenuCategory menuCategory) {
        return orderMenu.getTotalMenuCountByMenuCategory(menuCategory);
    }

    public int getTotalOrderMenuPriceBeforeDiscount() {
        return orderMenu.getTotalPrice();
    }

    public boolean isApplyEvent() {
        return orderMenu.getTotalPrice() >= MIN_TOTAL_ORDER_AMOUNT_FOR_EVENT;
    }
}
