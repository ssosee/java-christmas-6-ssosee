package christmas.domain;

import christmas.validation.OrderMenuValidationHandler;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Menu {
    T_BONE_STEAK("티본스테이크", 55_000, MenuCategory.MAIN_COURSE),
    BBQ_RIBS("바비큐립", 54_000, MenuCategory.MAIN_COURSE),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuCategory.MAIN_COURSE),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuCategory.MAIN_COURSE),

    MUSHROOM_SOUP("양송이수프", 6_000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5_500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuCategory.APPETIZER),

    CHOCOLATE_CAKE("초코케이크", 15_000, MenuCategory.DESERT),
    ICE_CREAM("아이스크림", 5_000, MenuCategory.DESERT),

    ZERO_COKE("제로콜라", 3_000, MenuCategory.BEVERAGE),
    RED_WINE("레드와인", 60_000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuCategory.BEVERAGE);

    private final String krName;
    private final int price;
    private final MenuCategory menuCategory;

    Menu(String krName, int price, MenuCategory menuCategory) {
        this.krName = krName;
        this.price = price;
        this.menuCategory = menuCategory;
    }

    public static Menu findMenuByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getKrName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(OrderMenuValidationHandler.INVALID_MENU_MESSAGE));
    }

    public String getKrName() {
        return krName;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }
}
