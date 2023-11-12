package christmas.domain;


import christmas.domain.constant.DateConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

class DateTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "10", "20", "31"})
    @DisplayName("1 ~ 31 사이의 값이면 예외가 발생하지 않는다.")
    void createDate(String input) {
        // given // when
        Date date = new Date(input);

        // then
        assertThat(date).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    @DisplayName("1 ~ 31 사이의 값이 아니면 예외가 발생한다.")
    void createDateException(String input) {
        // given // when // then
        assertThatThrownBy(() -> new Date(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DateConstant.INVALID_DATE_MESSAGE);
    }
}