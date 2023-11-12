package christmas.io;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;

public class InputView {
    private static final String READ_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public Date readDate() {
        System.out.println(READ_DATE_MESSAGE);
        String readDate = Console.readLine();

        return new Date(readDate);
    }
}
