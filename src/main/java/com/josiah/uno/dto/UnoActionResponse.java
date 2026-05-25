package com.josiah.uno.dto;

public class UnoActionResponse {
    private ActionType type;
    private int actorPlayerIndex;
    private int targetPlayerIndex;
    private int cardsDrawn;
    private UnoCardResponse playedCard;

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public int getActorPlayerIndex() {
        return actorPlayerIndex;
    }

    public void setActorPlayerIndex(int actorPlayerIndex) {
        this.actorPlayerIndex = actorPlayerIndex;
    }

    public int getTargetPlayerIndex() {
        return targetPlayerIndex;
    }

    public void setTargetPlayerIndex(int targetPlayerIndex) {
        this.targetPlayerIndex = targetPlayerIndex;
    }

    public int getCardsDrawn() {
        return cardsDrawn;
    }

    public void setCardsDrawn(int cardsDrawn) {
        this.cardsDrawn = cardsDrawn;
    }

    public UnoCardResponse getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(UnoCardResponse playedCard) {
        this.playedCard = playedCard;
    }
}
