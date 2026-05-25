package com.josiah.uno.domain.card;

public enum UnoValue {
    ZERO(1),
    ONE(2),
    TWO(2),
    THREE(2),
    FOUR(2),
    FIVE(2),
    SIX(2),
    SEVEN(2),
    EIGHT(2),
    NINE(2),
    REVERSE(2),
    SKIP(2),
    DRAW_TWO(2),
    WILD_DRAW_FOUR(4),
    WILD(4);

    private final int count;

    UnoValue(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public boolean isWild() {
        return this == WILD || this == WILD_DRAW_FOUR;
    }

    public boolean isAction() {
        return switch (this) {
            case REVERSE, SKIP, DRAW_TWO -> true;
            default -> false;
        };
    }

    public boolean isNumber() {
        return switch (this) {
            case ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX,
                 SEVEN, EIGHT, NINE -> true;
            default -> false;
        };
    }
}
