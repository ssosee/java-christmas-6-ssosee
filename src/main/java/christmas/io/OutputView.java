package christmas.io;

import christmas.domain.OrderMenu;

public class OutputView {

    public static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    public static final String ORDER_MENU_FORMAT = "%s %d개\n";

    public void printMenu(OrderMenu orderMenu) {
        System.out.println(ORDER_MENU_MESSAGE);

        String orderMenus = orderMenu.getOrderMenusByOutputViewFormat();

        System.out.print(orderMenus);
    }
}
