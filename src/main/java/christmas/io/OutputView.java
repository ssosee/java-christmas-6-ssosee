package christmas.io;

import christmas.domain.OrderMenu;
import java.text.DecimalFormat;

public class OutputView {

    public static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    public static final String TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_MESSAGE = "<<할인 전 총주문 금액>>";
    public static final String ORDER_MENU_FORMAT = "%s %d개\n";
    public static final String TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_FORMAT = "%s원";
    public static final String PRICE_FORMAT = "###,###";

    public void printMenu(OrderMenu orderMenu) {
        System.out.println(ORDER_MENU_MESSAGE);

        String orderMenus = orderMenu.getOrderMenusByOutputViewFormat();

        System.out.print(orderMenus);
    }

    public void printTotalOrderMenuPriceBeforeDiscount(OrderMenu orderMenu) {
        System.out.println(TOTAL_ORDER_MENU_PRICE_BEFORE_DISCOUNT_MESSAGE);

        int totalPrice = orderMenu.getTotalPrice();
        DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);

        System.out.println(decimalFormat.format(totalPrice));
    }

}
