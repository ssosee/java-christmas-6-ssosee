package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.validation.OrderMenuValidationHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasEventTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();
    OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", orderMenuValidationHandler);
    ChristmasEvent event = new ChristmasEvent(orderMenu);

    @ParameterizedTest
    @CsvSource(value = {"1:1000", "2:1100", "3:1200", "4:1300", "5:1400", "6:1500", "7:1600", "8:1700", "9:1800",
            "10:1900", "11:2000", "12:2100", "13:2200", "14:2300", "15:2400", "16:2500", "17:2600",
            "18:2700", "19:2800", "20:2900", "21:3000", "22:3100", "23:3200", "24:3300", "25:3400"}, delimiter = ':')
    @DisplayName("1일에 1,000원, 2일에 1,100원, 3일에 1,200원 ,,, 25일에 3,500원 할인한다.(25일까지 할인금액을 100원씩 증가한다.)")
    void discount(int day, int expectedDiscount) {

        // given // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    @ParameterizedTest
    @CsvSource(value = {"26:0", "27:0", "28:0", "29:0", "30:0", "31:0"}, delimiter = ':')
    @DisplayName("25일 이후에는 크리스마스 디데이 할인이 적용되지 않는다.")
    void noDiscount(int day, int expectedDiscount) {

        // given // when
        int discount = event.discount(day);

        // then
        assertThat(discount).isEqualTo(expectedDiscount);
    }
}