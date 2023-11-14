package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @ParameterizedTest
    @CsvSource(value = {"4999:NONE", "5000:STAR", "10000:TREE", "20000:SANTA"}, delimiter = ':')
    @DisplayName("총 혜택 금액에 맞는 이벤트 뱃지를 부여한다.")
    void serveBadeType(int totalBenefitPrice, BadgeType expectedBadgeType) {
        // given
        Badge badge = new Badge();

        // when
        BadgeType badgeType = badge.serveBadeType(totalBenefitPrice);

        // then
        assertThat(badgeType).isEqualTo(expectedBadgeType);
    }
}