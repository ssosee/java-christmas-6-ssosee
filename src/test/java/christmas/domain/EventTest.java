package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.validation.OrderMenuValidationHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();

    @Test
    @DisplayName("할인 전 총 주문 금액이 10,000원 이상이면 이벤트 적용 대상이다.")
    void isApplyEventTrue() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);
        ChristmasEvent event = new ChristmasEvent(orderMenu);

        // when
        boolean applyEvent = event.isApplyEvent();

        // then
        assertThat(applyEvent).isTrue();
    }

    @Test
    @DisplayName("할인 전 총 주문 금액이 10,000원 미만이면 이벤트 적용 대상이 아니다.")
    void isApplyEventFalse() {
        // given
        OrderMenu orderMenu = new OrderMenu("아이스크림-1,제로콜라-1", orderMenuValidationHandler);
        ChristmasEvent event = new ChristmasEvent(orderMenu);

        // when
        boolean applyEvent = event.isApplyEvent();

        // then
        assertThat(applyEvent).isFalse();
    }
}