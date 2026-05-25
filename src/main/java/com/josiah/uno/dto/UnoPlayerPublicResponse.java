package com.josiah.uno.dto;

import com.josiah.uno.domain.player.PlayerType;

import java.util.UUID;

public class UnoPlayerPublicResponse {
    private UUID id;
    private String name;
    private PlayerType type;
    private int handSize;

    public UnoPlayerPublicResponse() {}

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
}
