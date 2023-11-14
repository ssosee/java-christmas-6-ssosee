package christmas.validation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidationHandlerTest {

    DateValidationHandler dateValidationHandler = new DateValidationHandler();

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "10", "20", "31", "100", "1000"})
    @DisplayName("문자열이 모두 숫자이면 예외가 발생하지 않는다.")
    void validationNumeric(String date) {
        // given // when // then
        assertThatCode(() -> dateValidationHandler.validationNumeric(date))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-", "2가", "3a", "4!", "오", "10일", "20_", "31,", "1공0", "일1", "a10"})
    @DisplayName("문자열이 모두 숫자가 아니면 예외가 발생한다.")
    void validationNumericException(String date) {
        // given // when // then
        assertThatThrownBy(() -> dateValidationHandler.validationNumeric(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DateValidationHandler.INVALID_DATE_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 10, 20, 30, 31})
    @DisplayName("1이상 31이하의 숫자이면 예외가 발생하지 않는다.")
    void validationRange(int date) {
        // given // when // then
        assertThatCode(() -> dateValidationHandler.validationRange(date))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 100})
    @DisplayName("1이상 31이하의 숫자가 아니면 예외가 발생한다.")
    void validationRangeException(int date) {
        // given // when // then
        assertThatThrownBy(() -> dateValidationHandler.validationRange(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DateValidationHandler.INVALID_DATE_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1", "일", "!"})
    @DisplayName("공백이거나 빈값이 아니면 예외가 발생하지 않는다.")
    void validationHasText(String date) {
        // given // when // then
        assertThatCode(() -> dateValidationHandler.validationHasText(date))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백이거나 빈값이면 예외가 발생한다.")
    void validationHasTextException(String date) {
        // given // when // then
        assertThatThrownBy(() -> dateValidationHandler.validationHasText(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DateValidationHandler.INVALID_DATE_MESSAGE);
    }
}