package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.validation.OrderMenuValidationHandler;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();

    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142000",
            "시저샐러드-1,티본스테이크-1,제로콜라-2:69000",
            "바비큐립-1,티본스테이크-1,해산물파스타-2,아이스크림-1,레드와인-1:244000"}, delimiter = ':')
    @DisplayName("주문한 메뉴의 총 가격을 계산한다.")
    void createOrderMenuAndGetTotalPrice(String readOrderMenu, int expectedTotalPrice) {
        // given
        OrderMenu orderMenu = new OrderMenu(readOrderMenu, orderMenuValidationHandler);

        // when
        int totalPrice = orderMenu.getTotalOrderMenuPriceBeforeDiscount();

        // then
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1", "제로콜라-1,레드와인-1", "제로콜라-1,레드와인-1,샴페인-1"})
    @DisplayName("음료만 주문할 경우 예외가 발생한다.")
    void createOrderMenuException(String readOrderMenu) {
        // given // when // then
        assertThatThrownBy(() -> new OrderMenu(readOrderMenu, orderMenuValidationHandler))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @ParameterizedTest
    @MethodSource("notExistMenu")
    @DisplayName("메뉴판에 없는 메뉴를 입력하는 경우 예외가 발생한다.")
    void createOrderMenuNotExistMenuException(String readOrderMenu) {
        // given // when // then
        assertThatThrownBy(() -> new OrderMenu(readOrderMenu, orderMenuValidationHandler))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    private static Stream<String> notExistMenu() {
        return Stream.of("안창살-1", "치즈케이크-1", "봉골레-2");
    }

    @ParameterizedTest
    @MethodSource("invalidMenu")
    @DisplayName("메뉴 입력 포맷이 맞지 않는 경우 예외가 발생한다.")
    void createOrderMenuInvalidMenuException(String readOrderMenu) {
        // given // when // then
        assertThatThrownBy(() -> new OrderMenu(readOrderMenu, orderMenuValidationHandler))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    private static Stream<String> invalidMenu() {
        return Stream.of("양송이수프--", "양송이수프-일", "양송이수프-a", "양송이수프-!", "양송이수프- ", "양송이수프-.", "TAPAS-a", "TAPAS-일");
    }

    @Test
    @DisplayName("중복 메뉴를 입력한 경우 예외가 발생한다.")
    void createOrderMenuDuplicatedMenuException() {
        // given
        String readOrderMenu = "시저샐러드-1,시저샐러드-1";

        // when // then
        assertThatThrownBy(() -> new OrderMenu(readOrderMenu, orderMenuValidationHandler))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-10,제로콜라-11", "시저샐러드-10,제로콜라-0", "시저샐러드-21,제로콜라-1", "시저샐러드-20,제로콜라-1"})
    @DisplayName("총 20개 초과 또는 1개 미만의 메뉴를 주문할 경우 예외가 발생한다.")
    void createOrderMenuMenuQuantityRangeException(String readOrderMenu) {
        // given // when // then
        assertThatThrownBy(() -> new OrderMenu(readOrderMenu, orderMenuValidationHandler))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource(value = {"MAIN_COURSE:2", "DESERT:2", "BEVERAGE:6", "APPETIZER:3"}, delimiter = ':')
    @DisplayName("메뉴 카테고리에 맞는 총 메뉴 갯수를 구한다.")
    void getTotalMenuCountByMenuCategory(MenuCategory category, int expectedCount) {
        // given
        String readOrderMenu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-3,레드와인-3,양송이수프-1,타파스-2";
        OrderMenu orderMenu = new OrderMenu(readOrderMenu, orderMenuValidationHandler);

        // when
        int count = orderMenu.getTotalMenuCountByMenuCategory(category);

        // then
        assertThat(count).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("주문 메뉴를 출력 포맷에 맞는 문자열로 반환한다.")
    void getOrderMenusByOutputViewFormat() {
        // given
        String readOrderMenu = "티본스테이크-1,바비큐립-3,초코케이크-2";
        OrderMenu orderMenu = new OrderMenu(readOrderMenu, orderMenuValidationHandler);

        // when
        String orderMenus = orderMenu.generateOrderMenusByOutputViewFormat();

        // then
        assertThat(orderMenus).isEqualTo("티본스테이크 1개\n바비큐립 3개\n초코케이크 2개");
    }
}