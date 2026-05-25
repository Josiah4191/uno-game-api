package com.josiah.uno.dto;

import com.josiah.uno.domain.player.PlayerType;

import java.util.List;
import java.util.UUID;

public class UnoPlayerPrivateResponse {
    private UUID id;
    private String name;
    private PlayerType type;
    private int handSize;
    private List<UnoCardResponse> hand;

    public UnoPlayerPrivateResponse() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public List<UnoCardResponse> getHand() {
        return hand;
    }

    public void setHand(List<UnoCardResponse> hand) {
        this.hand = hand;
    }
}
