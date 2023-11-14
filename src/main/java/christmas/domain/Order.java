package christmas.domain;

import christmas.domain.constant.GiftEventConstant;
import christmas.io.OutputView;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    private final Map<String, Event> events;
    private final OrderMenu orderMenu;
    private final Date date;

    public Order(Map<String, Event> events, OrderMenu orderMenu, Date date) {
        this.events = events;
        this.orderMenu = orderMenu;
        this.date = date;
    }

    public int getTotalOrderPriceBeforeDiscount() {
        return orderMenu.getTotalOrderMenuPriceBeforeDiscount();
    }

    public int getTotalBenefitAmount() {
        return getTotalDiscounts() + getGiftEventDiscount(orderMenu.getTotalOrderMenuPriceBeforeDiscount());
    }

    public int getTotalOrderMenuPriceAfterDiscount() {
        return orderMenu.getTotalOrderMenuPriceBeforeDiscount() - getTotalDiscounts();
    }

    private int getTotalDiscounts() {
        return this.events.values().stream()
                .mapToInt(event -> event.discount(date.getDay()))
                .sum();
    }

    public String generateGiftMenu(Menu menu, int giftQuantity) {
        if (isGiftEvent(orderMenu.getTotalOrderMenuPriceBeforeDiscount())) {
            return String.format(OutputView.ORDER_MENU_FORMAT, menu.getKrName(), giftQuantity);
        }
        return OutputView.NOT_EXIST;
    }

    public String generateBenefitHistory() {
        DecimalFormat decimalFormat = new DecimalFormat(OutputView.PRICE_FORMAT);
        String benefitHistory = getBenefitHistory(decimalFormat);

        if (isGiftEvent(orderMenu.getTotalOrderMenuPriceBeforeDiscount())) {
            String giftBenefitMessage = String.format(OutputView.BENEFIT_HISTORY_FORMAT,
                    GiftEventConstant.NAME, decimalFormat.format(GiftEventConstant.DISCOUNT));

            return benefitHistory + OutputView.NEW_LINE + giftBenefitMessage;
        } else if (benefitHistory.isBlank()) {
            return OutputView.NOT_EXIST;
        }

        return benefitHistory;
    }

    private String getBenefitHistory(DecimalFormat decimalFormat) {
        return this.events.entrySet().stream()
                .filter(event -> event.getValue().discount(date.getDay()) != 0)
                .sorted(Comparator.comparingInt((value -> value.getValue().discount(date.getDay()))))
                .map(event -> String.format(OutputView.BENEFIT_HISTORY_FORMAT, event.getKey(),
                        decimalFormat.format(event.getValue().discount(date.getDay()))))
                .collect(Collectors.joining(OutputView.NEW_LINE));
    }

    private int getGiftEventDiscount(int totalOrderMenuPriceBeforeDiscount) {
        if (isGiftEvent(totalOrderMenuPriceBeforeDiscount)) {
            return GiftEventConstant.DISCOUNT;
        }
        return Event.NO_DISCOUNT;
    }

    private boolean isGiftEvent(int totalOrderMenuPriceBeforeDiscount) {
        return totalOrderMenuPriceBeforeDiscount >= GiftEventConstant.MIN_TOTAL_ORDER_AMOUNT_FOR_FREE_CHAMPAGNE;
    }
}