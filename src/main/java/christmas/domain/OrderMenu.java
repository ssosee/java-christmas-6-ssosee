package christmas.domain;

import christmas.utils.StringUtils;
import christmas.validation.OrderMenuValidationHandler;
import java.util.EnumMap;
import java.util.List;

public class OrderMenu {
    private static final String HYPHEN = "-";
    private static final int NAME_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    private EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
    private final OrderMenuValidationHandler menusValidationHandler;

    public OrderMenu(String readMenus, OrderMenuValidationHandler menusValidationHandler) {
        this.menusValidationHandler = menusValidationHandler;

        List<String> menus = StringUtils.splitCommaToListString(readMenus);
        for (String menu : menus) {
            this.menusValidationHandler.validationPattern(menu);

            String[] splitMenu = menu.split(HYPHEN);
            String name = splitMenu[NAME_INDEX];
            String quantity = splitMenu[QUANTITY_INDEX];

            validateAndAddMenu(name, quantity);
        }

        this.menusValidationHandler.validationHasOnlyBeverage(this.menus);
        this.menusValidationHandler.validationTotalQuantity(getTotalQuantity());
    }

    private void validateAndAddMenu(String name, String quantity) {
        menusValidationHandler.validationQuantity(quantity);

        Menu findMenu = Menu.findMenuByName(name);
        menusValidationHandler.validationDuplicatedMenu(this.menus, findMenu);

        this.menus.put(findMenu, Integer.parseInt(quantity));
    }

    private int getTotalQuantity() {
        return this.menus.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalPrice() {
        return this.menus.entrySet().stream()
                .mapToInt(menu -> menu.getKey().getPrice() * menu.getValue())
                .sum();
    }
}
