package com.josiah.uno.dto;

import java.util.UUID;

public class UnoCreateGameResponse {
    private UUID gameId;
    private UnoGameStateResponse gameState;

    public UnoCreateGameResponse() {}

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UnoGameStateResponse getGameState() {
        return gameState;
    }

    public void setGameState(UnoGameStateResponse gameState) {
        this.gameState = gameState;
    }
}
