package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.validation.DateValidationHandler;
import christmas.validation.OrderMenuValidationHandler;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();
    DateValidationHandler dateValidationHandler = new DateValidationHandler();

    @Test
    @DisplayName("12만원 이상 주문하면 증정 이벤트 대상이고 '샴페인 1개' 문자열을 반환한다.")
    void getGiftMenuOutputView() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);
        ChristmasEvent event = new ChristmasEvent(orderMenu);
        Date date = new Date("1", dateValidationHandler);
        Order order = new Order(Map.of("크리스마스 디데이 할인", event), orderMenu, date);

        // when
        String message = order.generateGiftMenu(Menu.CHAMPAGNE, 1);

        // then
        assertThat(message).isEqualTo("샴페인 1개");
    }

    @Test
    @DisplayName("12만원 미만으로 주문하면 증정 이벤트 대상이 아니고 '없음' 문자열을 반환한다.")
    void getGiftMenuOutputViewNotExist() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);
        ChristmasEvent event = new ChristmasEvent(orderMenu);
        Date date = new Date("1", dateValidationHandler);
        Order order = new Order(Map.of("크리스마스 디데이 할인", event), orderMenu, date);

        // when
        String message = order.generateGiftMenu(Menu.CHAMPAGNE, 1);

        // then
        assertThat(message).isEqualTo("없음");
    }

    @Test
    @DisplayName("총 혜택 금액을 계산한다.")
    void getTotalBenefitAmount() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);

        Date date = new Date("3", dateValidationHandler);

        ChristmasEvent event1 = new ChristmasEvent(orderMenu);
        SpecialEvent event2 = new SpecialEvent(orderMenu);
        WeekdayEvent event3 = new WeekdayEvent(orderMenu);
        WeekendEvent event4 = new WeekendEvent(orderMenu);
        Map<String, Event> events = Map.of("크리스마스 디데이 할인", event1, "특별 할인", event2,
                "평일 할인", event3, "주말 할인", event4);

        Order order = new Order(events, orderMenu, date);

        // when
        int totalBenefitAmount = order.getTotalBenefitAmount();

        // then
        assertThat(totalBenefitAmount).isEqualTo(31246);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    void getPaymentAmount() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);

        Date date = new Date("3", dateValidationHandler);

        ChristmasEvent event1 = new ChristmasEvent(orderMenu);
        SpecialEvent event2 = new SpecialEvent(orderMenu);
        WeekdayEvent event3 = new WeekdayEvent(orderMenu);
        WeekendEvent event4 = new WeekendEvent(orderMenu);
        Map<String, Event> events = Map.of("크리스마스 디데이 할인", event1, "특별 할인", event2,
                "평일 할인", event3, "주말 할인", event4);

        Order order = new Order(events, orderMenu, date);

        // when
        int totalBenefitAmount = order.getTotalOrderMenuPriceAfterDiscount();

        // then
        assertThat(totalBenefitAmount).isEqualTo(135754);
    }

    @Test
    @DisplayName("혜택 내역을 문자열로 반환한다.(증정 이벤트를 제외한 할인금액을 기준으로 오름차순")
    void generateBenefitHistory() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);

        Date date = new Date("3", dateValidationHandler);

        ChristmasEvent event1 = new ChristmasEvent(orderMenu);
        SpecialEvent event2 = new SpecialEvent(orderMenu);
        WeekdayEvent event3 = new WeekdayEvent(orderMenu);
        WeekendEvent event4 = new WeekendEvent(orderMenu);
        Map<String, Event> events = Map.of("크리스마스 디데이 할인", event1, "특별 할인", event2,
                "평일 할인", event3, "주말 할인", event4);

        Order order = new Order(events, orderMenu, date);

        // when
        String benefitHistory = order.generateBenefitHistory();

        // then
        assertThat(benefitHistory).isEqualTo("특별 할인: -1,000원\n크리스마스 디데이 할인: -1,200원\n평일 할인: -4,046원\n증정 이벤트: -25,000원");
    }

    @Test
    @DisplayName("할인 전 주문금액을 계산한다.")
    void getTotalOrderPriceBeforeDiscount() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);

        Date date = new Date("3", dateValidationHandler);

        ChristmasEvent event1 = new ChristmasEvent(orderMenu);
        SpecialEvent event2 = new SpecialEvent(orderMenu);
        WeekdayEvent event3 = new WeekdayEvent(orderMenu);
        WeekendEvent event4 = new WeekendEvent(orderMenu);
        Map<String, Event> events = Map.of("크리스마스 디데이 할인", event1, "특별 할인", event2,
                "평일 할인", event3, "주말 할인", event4);

        Order order = new Order(events, orderMenu, date);

        // when
        int price = order.getTotalOrderPriceBeforeDiscount();

        // then
        assertThat(price).isEqualTo(142000);
    }
}