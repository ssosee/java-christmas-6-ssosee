package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.validation.OrderMenuValidationHandler;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventHandlerTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();

    @Test
    @DisplayName("12만원 이상 주문하면 증정 이벤트 대상이고 '샴페인 1개' 문자열을 반환한다.")
    void getGiftMenuOutputView() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);
        ChristmasEvent event = new ChristmasEvent(orderMenu);
        EventHandler eventHandler = new EventHandler(List.of(event), orderMenu);

        // when
        String message = eventHandler.generateGiftMenuOutput(Menu.CHAMPAGNE, 1);

        // then
        assertThat(message).isEqualTo("샴페인 1개");
    }

    @Test
    @DisplayName("12만원 미만으로 주문하면 증정 이벤트 대상이 아니고 '없음' 문자열을 반환한다.")
    void getGiftMenuOutputViewNotExist() {
        // given
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);
        ChristmasEvent event = new ChristmasEvent(orderMenu);
        EventHandler eventHandler = new EventHandler(List.of(event), orderMenu);

        // when
        String message = eventHandler.generateGiftMenuOutput(Menu.CHAMPAGNE, 1);

        // then
        assertThat(message).isEqualTo("없음");
    }
}