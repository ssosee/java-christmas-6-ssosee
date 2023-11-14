package christmas.domain;

public class Badge {
    private BadgeType badgeType;

    public BadgeType serveBadeType(int totalBenefitPrice) {
        return this.badgeType = BadgeType.getBadgeType(totalBenefitPrice);
    }
}
