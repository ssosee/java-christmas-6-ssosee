package christmas.validation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Menu;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuValidationHandlerTest {

    OrderMenuValidationHandler orderMenuValidationHandler = new OrderMenuValidationHandler();

    @ParameterizedTest
    @ValueSource(strings = {"한글-10", "안녕-100", "반가워-0"})
    @DisplayName("'한글-숫자'의 패턴이면 예외가 발생하지 않는다.")
    void validationPattern(String menu) {
        // given // when // then
        assertThatCode(() -> orderMenuValidationHandler.validationPattern(menu))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"한글-a", "a-1", "한글--", "한글-!", "한글- ", " -10", "한글-10십", "한글-10a", "한글a-10"})
    @DisplayName("'한글-숫자'의 패턴이 아니면 예외가 발생한다.")
    void validationPatternException(String menu) {
        // given // when // then
        assertThatThrownBy(() -> orderMenuValidationHandler.validationPattern(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @Test
    @DisplayName("메뉴에 음료수만 있지 않으면 예외가 발생하지 않는다.")
    void validationOnlyBeverage() {
        // given
        EnumMap<Menu, Integer> menus = new EnumMap<>(Map.of(
                Menu.ZERO_COKE, 1,
                Menu.RED_WINE, 1,
                Menu.CHAMPAGNE, 1,
                Menu.CHRISTMAS_PASTA, 1
        ));

        // when // then
        assertThatCode(() -> orderMenuValidationHandler.validationHasOnlyBeverage(menus))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음료수만 메뉴에 있으면 예외가 발생한다.")
    void validationOnlyBeverageException() {
        // given
        EnumMap<Menu, Integer> menus = new EnumMap<>(Map.of(
                Menu.ZERO_COKE, 1,
                Menu.RED_WINE, 1,
                Menu.CHAMPAGNE, 1
        ));

        // when // then
        assertThatThrownBy(() -> orderMenuValidationHandler.validationHasOnlyBeverage(menus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @Test
    @DisplayName("입력받은 메뉴가 이미 저장한 메뉴에 존재하지 않으면 예외가 발생하지 않는다.")
    void validationDuplicatedMenu() {
        // given
        EnumMap<Menu, Integer> menus = new EnumMap<>(Map.of(
                Menu.ZERO_COKE, 1,
                Menu.RED_WINE, 1,
                Menu.CHAMPAGNE, 1
        ));

        Menu inputMenu = Menu.CHOCOLATE_CAKE;

        // when // then
        assertThatCode(() -> orderMenuValidationHandler.validationDuplicatedMenu(menus, inputMenu))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력받은 메뉴가 이미 저장한 메뉴에 존재하면 예외가 발생한다.")
    void validationDuplicatedMenuException() {
        // given
        EnumMap<Menu, Integer> menus = new EnumMap<>(Map.of(
                Menu.ZERO_COKE, 1,
                Menu.RED_WINE, 1,
                Menu.CHAMPAGNE, 1
        ));

        Menu inputMenu = Menu.ZERO_COKE;

        // when // then
        assertThatThrownBy(() -> orderMenuValidationHandler.validationDuplicatedMenu(menus, inputMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }


    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "10", "15", "20"})
    @DisplayName("입력한 수량이 숫자이고 1이상 20이하 이면 예외가 발생하지 않는다.")
    void validationQuantity(String quantity) {
        // given // when // then
        assertThatCode(() -> orderMenuValidationHandler.validationQuantity(quantity))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", ".", ",", "!", "일", "a"})
    @DisplayName("입력한 수량이 숫자가 아니면 예외가 발생한다.")
    void validationQuantityNumericException(String quantity) {
        // given // when // then
        assertThatThrownBy(() -> orderMenuValidationHandler.validationQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "21", "22"})
    @DisplayName("입력한 수량이 20보다 크거나 1보다 작으면 예외가 발생한다.")
    void validationQuantityException(String quantity) {
        // given // when // then
        assertThatThrownBy(() -> orderMenuValidationHandler.validationQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 10, 15, 20})
    @DisplayName("주문한 총 메뉴의 갯수는 1이상 20이하이면 예외가 발생하지 않는다.")
    void validationTotalQuantity(int totalQuantity) {
        // given // when // then
        assertThatCode(() -> orderMenuValidationHandler.validationTotalQuantity(totalQuantity))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 21, 22})
    @DisplayName("입력한 수량이 20보다 크거나 1보다 작으면 예외가 발생한다.")
    void validationTotalQuantityException(int totalQuantity) {
        // given // when // then
        assertThatThrownBy(() -> orderMenuValidationHandler.validationTotalQuantity(totalQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1", "일", "!"})
    @DisplayName("공백이거나 빈값이 아니면 예외가 발생하지 않는다.")
    void validationHasText(String date) {
        // given // when // then
        assertThatCode(() -> orderMenuValidationHandler.validationHasText(date))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백이거나 빈값이면 예외가 발생한다.")
    void validationHasTextException(String date) {
        // given // when // then
        assertThatThrownBy(() -> orderMenuValidationHandler.validationHasText(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }
}