package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.validation.OrderMenuValidationHandler;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class MenuTest {

    @ParameterizedTest
    @MethodSource("validMenu")
    @DisplayName("주문 메뉴명에 알맞은 enum 타입을 반환한다.")
    void findMenuByName(String name, Menu expectedMenu) {
        // given // when
        Menu menu = Menu.findMenuByName(name);

        // then
        assertThat(menu).isEqualTo(expectedMenu);
    }

    private static Stream<Arguments> validMenu() {
        return Stream.of(
                Arguments.of("티본스테이크", Menu.T_BONE_STEAK),
                Arguments.of("바비큐립", Menu.BBQ_RIBS),
                Arguments.of("해산물파스타", Menu.SEAFOOD_PASTA),
                Arguments.of("크리스마스파스타", Menu.CHRISTMAS_PASTA),
                Arguments.of("초코케이크", Menu.CHOCOLATE_CAKE),
                Arguments.of("아이스크림", Menu.ICE_CREAM),
                Arguments.of("제로콜라", Menu.ZERO_COKE),
                Arguments.of("레드와인", Menu.RED_WINE),
                Arguments.of("샴페인", Menu.CHAMPAGNE),
                Arguments.of("양송이수프", Menu.MUSHROOM_SOUP),
                Arguments.of("타파스", Menu.TAPAS),
                Arguments.of("시저샐러드", Menu.CAESAR_SALAD)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"안창살-1", "치즈케이크-1", "봉골레-2"})
    @DisplayName("enum타입에 메뉴명이 없으면 예외가 발생한다.")
    void findMenuByNameException(String name) {
        // given // when // then
        assertThatThrownBy(() -> Menu.findMenuByName(name))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(OrderMenuValidationHandler.INVALID_MENU_MESSAGE);
    }
}