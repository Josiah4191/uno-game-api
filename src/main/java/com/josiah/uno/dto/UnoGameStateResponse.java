package com.josiah.uno.dto;

import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.game.PlayDirection;

import java.util.List;

public class UnoGameStateResponse {
    private UnoColor currentColor;
    private PlayDirection playDirection;
    private int currentPlayerIndex;
    private UnoCardResponse topCard;
    private List<UnoPlayerPublicResponse> opponents;
    private UnoActionResponse lastAction;

    public UnoGameStateResponse() {}

    public UnoColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(UnoColor currentColor) {
        this.currentColor = currentColor;
    }

    public PlayDirection getPlayDirection() {
        return playDirection;
    }

    public void setPlayDirection(PlayDirection playDirection) {
        this.playDirection = playDirection;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public UnoCardResponse getTopCard() {
        return topCard;
    }

    public void setTopCard(UnoCardResponse topCard) {
        this.topCard = topCard;
    }

    public List<UnoPlayerPublicResponse> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<UnoPlayerPublicResponse> opponents) {
        this.opponents = opponents;
    }

    public UnoActionResponse getLastAction() {
        return lastAction;
    }

    public void setLastAction(UnoActionResponse lastAction) {
        this.lastAction = lastAction;
    }
}
