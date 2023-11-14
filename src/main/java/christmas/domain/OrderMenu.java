package christmas.domain;

import christmas.domain.constant.OrderMenuConstant;
import christmas.io.OutputView;
import christmas.utils.StringUtils;
import christmas.validation.OrderMenuValidationHandler;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMenu {
    private final EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
    private final OrderMenuValidationHandler menusValidationHandler;

    public OrderMenu(String readMenus, OrderMenuValidationHandler menusValidationHandler) {
        this.menusValidationHandler = menusValidationHandler;

        this.menusValidationHandler.validationHasText(readMenus);

        List<String> menus = StringUtils.splitCommaToListString(readMenus);
        for (String menu : menus) {
            this.menusValidationHandler.validationPattern(menu);

            String[] splitMenu = menu.split(StringUtils.HYPHEN);
            String name = splitMenu[OrderMenuConstant.NAME_INDEX];
            String quantity = splitMenu[OrderMenuConstant.QUANTITY_INDEX];

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

    public int getTotalOrderMenuPriceBeforeDiscount() {
        return this.menus.entrySet().stream()
                .mapToInt(menu -> menu.getKey().getPrice() * menu.getValue())
                .sum();
    }

    public int getTotalMenuCountByMenuCategory(MenuCategory menuCategory) {
        return this.menus.keySet().stream()
                .filter(menu -> menu.getMenuCategory().equals(menuCategory))
                .mapToInt(menus::get)
                .sum();
    }

    public String generateOrderMenusByOutputViewFormat() {
        return this.menus.entrySet().stream()
                .map(menus -> String.format(OutputView.ORDER_MENU_FORMAT,
                        menus.getKey().getKrName(), menus.getValue()))
                .collect(Collectors.joining(OutputView.NEW_LINE));
    }
}
