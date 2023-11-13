package christmas.io;

import java.text.DecimalFormat;

public class OutputView {
    public static final String NEW_LINE = "\n";
    private static final String PRICE_FORMAT = "###,###";
    public static final String NOT_EXIST = "없음";
    private static final String EVENT_PREVIEW_GUIDE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_MESSAGE = "<<할인 전 총주문 금액>>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    public static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_FORMAT = "%s원";

    public void printEventPreviewGuide(int day) {
        String message = String.format(EVENT_PREVIEW_GUIDE_FORMAT, day);
        System.out.println(message);
    }

    public void printOrderMenus(String orderMenus) {
        System.out.println(ORDER_MENU_MESSAGE + NEW_LINE + orderMenus);
    }

    public void printTotalOrderMenuPriceBeforeDiscount(int totalOrderMenuPriceBeforeDiscount) {
        DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);
        String priceFormat = decimalFormat.format(totalOrderMenuPriceBeforeDiscount);
        String message = String.format(TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_FORMAT, priceFormat);

        System.out.println(TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_MESSAGE + NEW_LINE + message);
    }

    public void printGiftEvent(String giftEventMenu) {
        System.out.println(GIFT_MENU_MESSAGE + NEW_LINE + giftEventMenu);
    }
}
