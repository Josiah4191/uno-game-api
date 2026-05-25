package com.josiah.uno.dto;

import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.card.UnoValue;

public class UnoCardResponse {
    private UnoColor color;
    private UnoValue value;

    public UnoCardResponse() {}

    public UnoColor getColor() {
        return color;
    }

    public UnoValue getValue() {
        return value;
    }

    public void setColor(UnoColor color) {
        this.color = color;
    }

    public void setValue(UnoValue value) {
        this.value = value;
    }

}
