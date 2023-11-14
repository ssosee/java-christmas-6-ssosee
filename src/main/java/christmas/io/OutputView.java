package christmas.io;

import java.text.DecimalFormat;

public class OutputView {
    public static final String NEW_LINE = "\n";
    public static final String PRICE_FORMAT = "###,###";
    public static final String NOT_EXIST = "없음";
    private static final String EVENT_PREVIEW_GUIDE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_MESSAGE = "<<할인 전 총주문 금액>>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_HISTORY_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String TOTAL_ORDER_MENU_PRICE_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String DEC_EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    public static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String TOTAL_ORDER_MENU_PRICE_FORMAT = "%s원";
    private static final String TOTAL_BENEFIT_AMOUNT_FORMAT = "-%s원";
    public static final String BENEFIT_HISTORY_FORMAT = "%s: -%s원";

    public void printEventPreviewGuide(int day) {
        String message = String.format(EVENT_PREVIEW_GUIDE_FORMAT, day);
        System.out.println(message + NEW_LINE);
    }

    public void printOrderMenus(String orderMenus) {
        System.out.println(ORDER_MENU_MESSAGE + NEW_LINE + orderMenus + NEW_LINE);
    }

    public void printTotalOrderMenuPriceBeforeDiscount(int totalOrderMenuPriceBeforeDiscount) {
        DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);
        String priceFormat = decimalFormat.format(totalOrderMenuPriceBeforeDiscount);
        String message = String.format(TOTAL_ORDER_MENU_PRICE_FORMAT, priceFormat);

        System.out.println(TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_MESSAGE + NEW_LINE + message + NEW_LINE);
    }

    public void printGiftMenu(String giftMenu) {
        System.out.println(GIFT_MENU_MESSAGE + NEW_LINE + giftMenu + NEW_LINE);
    }

    public void printBenefitHistory(String benefitHistory) {
        System.out.println(BENEFIT_HISTORY_MESSAGE + NEW_LINE + benefitHistory + NEW_LINE);
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);
        String priceFormat = decimalFormat.format(totalBenefitAmount);
        String message = String.format(TOTAL_BENEFIT_AMOUNT_FORMAT, priceFormat);

        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE + NEW_LINE + message + NEW_LINE);
    }

    public void printTotalOrderMenuPriceAfterDiscount(int totalOrderMenuPriceAfterDiscount) {
        DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);
        String priceFormat = decimalFormat.format(totalOrderMenuPriceAfterDiscount);
        String message = String.format(TOTAL_ORDER_MENU_PRICE_FORMAT, priceFormat);

        System.out.println(TOTAL_ORDER_MENU_PRICE_AFTER_DISCOUNT_MESSAGE + NEW_LINE + message + NEW_LINE);
    }

    public void printDecEventBadge(String eventBadge) {
        System.out.print(DEC_EVENT_BADGE_MESSAGE + NEW_LINE + eventBadge);
    }
}
