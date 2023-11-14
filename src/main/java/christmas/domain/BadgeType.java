package christmas.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum BadgeType {
    SANTA("산타", totalBenefitPrice -> totalBenefitPrice >= 20_000),
    TREE("트리", totalBenefitPrice -> totalBenefitPrice >= 10_000),
    STAR("별", totalBenefitPrice -> totalBenefitPrice >= 5_000),
    NONE("없음", totalBenefitPrice -> totalBenefitPrice < 5_000);

    private final String krName;
    private final Predicate<Integer> isMatch;

    BadgeType(String krName, Predicate<Integer> isMatch) {
        this.krName = krName;
        this.isMatch = isMatch;
    }

    public static BadgeType getBadgeType(final int totalBenefitPrice) {
        return Arrays.stream(BadgeType.values())
                .filter(badgeType -> badgeType.isMatch.test(totalBenefitPrice))
                .findAny()
                .orElse(NONE);
    }

    public String getKrName() {
        return krName;
    }
}
