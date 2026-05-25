package com.josiah.uno.domain.card;

public class UnoCard {
    public UnoCard(UnoColor color, UnoValue value) {
        this.color = color;
        this.value = value;
    }

    private final UnoColor color;
    private final UnoValue value;

    public UnoColor getColor() {
        return color;
    }

    public UnoValue getValue() {
        return value;
    }

    public String toString() {
        return "Suit: " + color + " | " + "Value: " + value;
    }

}
