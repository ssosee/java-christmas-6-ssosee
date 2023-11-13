package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.validation.OrderMenuValidationHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendEventTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();

    @ParameterizedTest
    @CsvSource(value = {"1:티본스테이크-1:2023", "2:티본스테이크-1,바비큐립-1:4046", "8:티본스테이크-1,바비큐립-1,해산물파스타-1:6069",
            "9:티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1:8092", "15:티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1:8092",
            "16:티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1:8092", "22:티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1:8092",
            "23:티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1:8092", "29:티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1:8092",
            "30:티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1:8092"}, delimiter = ':')
    @DisplayName("주말(금요일 ~ 토요일)에 메인 메뉴 1개당 2,023원 씩 할인한다.")
    void discount(int day, String orderMenus, int expectedDiscount) {
        // given
        OrderMenu orderMenu = new OrderMenu(orderMenus, orderMenuValidationHandler);
        WeekendEvent event = new WeekendEvent(orderMenu);

        // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    @ParameterizedTest
    @CsvSource(value = {"3:티본스테이크-1:0", "4:티본스테이크-1,바비큐립-1:0", "5:티본스테이크-2:0",
            "6:티본스테이크-1,바비큐립-2:0", "7:티본스테이크-2,바비큐립-2:0"}, delimiter = ':')
    @DisplayName("주말(금요일 ~ 토요일)이 아니면 주말할인이 적용되지 않는다.")
    void noDiscount(int day, String orderMenus, int expectedDiscount) {
        // given
        OrderMenu orderMenu = new OrderMenu(orderMenus, orderMenuValidationHandler);
        WeekendEvent event = new WeekendEvent(orderMenu);

        // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(expectedDiscount);
    }
}