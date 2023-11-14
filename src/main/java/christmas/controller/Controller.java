package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.BadgeType;
import christmas.domain.ChristmasEvent;
import christmas.domain.Date;
import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.SpecialEvent;
import christmas.domain.WeekdayEvent;
import christmas.domain.WeekendEvent;
import christmas.domain.constant.ChristmasEventConstant;
import christmas.domain.constant.SpecialEventConstant;
import christmas.domain.constant.WeekdayEventConstant;
import christmas.domain.constant.WeekendEventConstant;
import christmas.io.InputView;
import christmas.io.OutputView;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        Date date = inputView.readDate();
        OrderMenu orderMenu = inputView.readOrderMenu();

        outputView.printEventPreviewGuide(date.getDay());
        outputView.printOrderMenus(orderMenu.generateOrderMenusByOutputViewFormat());

        Order order = orderService(orderMenu, date);

        badgeService(order);

        //Console.close();
    }

    private void badgeService(Order order) {
        Badge badge = new Badge();
        BadgeType badgeType = badge.serveBadeType(order.getTotalBenefitAmount());
        outputView.printDecEventBadge(badgeType.getKrName());
    }

    private Order orderService(OrderMenu orderMenu, Date date) {
        Order order = new Order(initEvents(orderMenu), orderMenu, date);

        outputView.printTotalOrderMenuPriceBeforeDiscount(order.getTotalOrderPriceBeforeDiscount());
        outputView.printGiftMenu(order.generateGiftMenu(Menu.CHAMPAGNE, 1));
        outputView.printBenefitHistory(order.generateBenefitHistory());
        outputView.printTotalBenefitAmount(order.getTotalBenefitAmount());
        outputView.printTotalOrderMenuPriceAfterDiscount(order.getTotalOrderMenuPriceAfterDiscount());

        return order;
    }

    private static Map<String, Event> initEvents(OrderMenu orderMenu) {
        Map<String, Event> events = new HashMap<>();
        events.put(ChristmasEventConstant.NAME, new ChristmasEvent(orderMenu));
        events.put(SpecialEventConstant.NAME, new SpecialEvent(orderMenu));
        events.put(WeekendEventConstant.NAME, new WeekendEvent(orderMenu));
        events.put(WeekdayEventConstant.NAME, new WeekdayEvent(orderMenu));

        return events;
    }
}
