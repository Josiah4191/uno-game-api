package com.josiah.uno.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UnoCreateGameRequest {

    @Min(value = 1, message = "Computer player count must be at least 1")
    @Max(value = 4, message = "Computer player count must be at most 4")
    private int computerPlayerCount;

    @NotBlank(message="Name is required")
    @Size(min=2, max=40, message="Name must be between 2 and 40 characters")
    private String playerName;

    public UnoCreateGameRequest() {}

    public int getComputerPlayerCount() {
        return computerPlayerCount;
    }

    public void setComputerPlayerCount(int computerPlayerCount) {
        this.computerPlayerCount = computerPlayerCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
