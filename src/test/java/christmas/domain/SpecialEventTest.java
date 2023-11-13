package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.SpecialEventConstant;
import christmas.validation.OrderMenuValidationHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialEventTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();
    OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);
    SpecialEvent event = new SpecialEvent(orderMenu);

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @DisplayName("이벤트 달력에 별이 있으면 특별할인이 적용된다.(1,000원을 할인 한다.)")
    void discount(int day) {
        // given // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(SpecialEventConstant.DISCOUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30})
    @DisplayName("이벤트 달력에 별이 없으면 특별할인이 적용되지 않는다.")
    void noDiscount(int day) {
        // given // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(Event.NO_DISCOUNT);
    }
}