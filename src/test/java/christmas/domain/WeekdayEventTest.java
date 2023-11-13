package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.validation.OrderMenuValidationHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayEventTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();

    @ParameterizedTest
    @CsvSource(value = {"3:초코케이크-1:2023", "4:초코케이크-1,아이스크림-1:4046", "5:초코케이크-2:4046",
            "6:초코케이크-1,아이스크림-2:6069", "7:초코케이크-2,아이스크림-2:8092"}, delimiter = ':')
    @DisplayName("평일(일요일 ~ 목요일)에 디저트 메뉴 1개당 2,023원을 할인 한다.")
    void discount(int day, String orderMenus, int expectedDiscount) {
        // given
        OrderMenu orderMenu = new OrderMenu(orderMenus, orderMenuValidationHandler);
        WeekdayEvent event = new WeekdayEvent(orderMenu);

        // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    @ParameterizedTest
    @CsvSource(value = {"8:초코케이크-1", "9:초코케이크-1,아이스크림-1", "15:초코케이크-2",
            "16:초코케이크-1,아이스크림-2", "22:초코케이크-2,아이스크림-2", "23:초코케이크-2,아이스크림-2",
            "29:초코케이크-2,아이스크림-2", "30:초코케이크-2,아이스크림-2"}, delimiter = ':')
    @DisplayName("평일(일요일 ~ 목요일)이 아니면 디저트 메뉴 1개당 할인하지 않는다. ")
    void noDiscount(int day, String orderMenus) {
        // given
        OrderMenu orderMenu = new OrderMenu(orderMenus, orderMenuValidationHandler);
        WeekdayEvent event = new WeekdayEvent(orderMenu);

        // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(0);
    }
}