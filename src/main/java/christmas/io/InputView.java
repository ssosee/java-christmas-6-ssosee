package christmas.io;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.domain.OrderMenu;
import christmas.validation.DateValidationHandler;
import christmas.validation.OrderMenuValidationHandler;

public class InputView {
    private static final String READ_DATE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";


    public Date readDate() {
        System.out.println(READ_DATE_MESSAGE);
        String readDate = Console.readLine();

        return new Date(readDate, new DateValidationHandler());
    }

    public OrderMenu readOrderMenu() {
        System.out.println(READ_MENU_MESSAGE);
        String readOrderMenu = Console.readLine();

        return new OrderMenu(readOrderMenu, new OrderMenuValidationHandler());
    }
}
