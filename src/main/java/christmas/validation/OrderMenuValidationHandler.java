package christmas.validation;

import christmas.domain.Menu;
import christmas.domain.MenuCategory;
import christmas.domain.constant.OrderMenuConstant;
import christmas.utils.StringUtils;
import java.util.EnumMap;
import java.util.regex.Pattern;

public class OrderMenuValidationHandler {

    public static final String INVALID_MENU_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String MENU_PATTERN = "^[가-힣]+-\\d+\\s*$";


    public void validationHasText(String menu) {
        if (!StringUtils.hasText(menu)) {
            throw new IllegalArgumentException(INVALID_MENU_MESSAGE);
        }
    }

    public void validationPattern(String menu) {
        if (!Pattern.matches(MENU_PATTERN, menu.trim())) {
            throw new IllegalArgumentException(INVALID_MENU_MESSAGE);
        }
    }

    public void validationHasOnlyBeverage(EnumMap<Menu, Integer> menus) {
        if (isAllBeverage(menus)) {
            throw new IllegalArgumentException(INVALID_MENU_MESSAGE);
        }
    }

    public void validationTotalQuantity(int totalQuantity) {
        validationQuantityRange(totalQuantity);
    }

    public void validationDuplicatedMenu(EnumMap<Menu, Integer> menus, Menu findMenu) {
        if (menus.containsKey(findMenu)) {
            throw new IllegalArgumentException(INVALID_MENU_MESSAGE);
        }
    }

    public void validationQuantity(String quantity) {
        validationNumeric(quantity);
        validationQuantityRange(Integer.parseInt(quantity));
    }

    private void validationNumeric(String quantity) {
        if (!StringUtils.isNumeric(quantity)) {
            throw new IllegalArgumentException(INVALID_MENU_MESSAGE);
        }
    }

    private void validationQuantityRange(int quantity) {
        if (!isValidQuantity(quantity)) {
            throw new IllegalArgumentException(INVALID_MENU_MESSAGE);
        }
    }

    private boolean isValidQuantity(int quantity) {
        return quantity <= OrderMenuConstant.MAX_QUANTITY && quantity >= OrderMenuConstant.MIN_QUANTITY;
    }

    private boolean isAllBeverage(EnumMap<Menu, Integer> menus) {
        return menus.keySet().stream()
                .allMatch(menu -> menu.getMenuCategory().equals(MenuCategory.BEVERAGE));
    }
}
