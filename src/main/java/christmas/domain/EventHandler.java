package christmas.domain;

import christmas.domain.constant.GiftEventConstant;
import christmas.io.OutputView;
import java.util.List;

public class EventHandler {
    private final List<Event> events;
    private final OrderMenu orderMenu;

    public EventHandler(List<Event> events, OrderMenu orderMenu) {
        this.events = events;
        this.orderMenu = orderMenu;
    }

    public String generateGiftMenuOutput(Menu menu, int giftQuantity) {
        if (isGiftEvent(orderMenu.getTotalPrice())) {
            return String.format(OutputView.ORDER_MENU_FORMAT, menu.getKrName(), giftQuantity);
        }
        return OutputView.NOT_EXIST;
    }

    private boolean isGiftEvent(int totalOrderMenuPriceBeforeDiscount) {
        return totalOrderMenuPriceBeforeDiscount >= GiftEventConstant.MIN_TOTAL_ORDER_AMOUNT_FOR_FREE_CHAMPAGNE;
    }
}