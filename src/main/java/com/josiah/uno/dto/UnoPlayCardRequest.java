package com.josiah.uno.dto;

import com.josiah.uno.domain.card.UnoColor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UnoPlayCardRequest {

    @NotNull(message = "Card index cannot be null")
    @Min(0)
    private Integer cardIndex;

    @NotNull(message = "Player index cannot be null")
    @Min(0)
    private Integer playerIndex;

    private UnoColor chosenColor;

    public UnoPlayCardRequest() {}

    public Integer getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(Integer cardIndex) {
        this.cardIndex = cardIndex;
    }

    public Integer getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(Integer playerIndex) {
        this.playerIndex = playerIndex;
    }

    public UnoColor getChosenColor() {
        return chosenColor;
    }

    public void setChosenColor(UnoColor chosenColor) {
        this.chosenColor = chosenColor;
    }
}
