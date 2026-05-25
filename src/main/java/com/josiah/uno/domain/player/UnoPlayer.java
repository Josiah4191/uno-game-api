package com.josiah.uno.domain.player;

import com.josiah.uno.domain.card.UnoCard;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UnoPlayer {

    private final UUID playerID;
    private String name;
    private final PlayerType type;
    private final UnoHand playerHand = new UnoHand();

    public UnoPlayer(String name, PlayerType type) {
        this.playerID = UUID.randomUUID();
        this.name = name;
        this.type = type;
    }

    public void addCard(UnoCard card) {
        playerHand.addCard(card);
    }

    public UnoCard playCard(int cardIndex) {
        return playerHand.playCard(cardIndex);
    }

    public UUID getPlayerID() {
        return playerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PlayerType getType() {
        return type;
    }

    public List<UnoCard> getHand() {
        return playerHand.getCards();
    }

    public int getHandSize() {
        return playerHand.size();
    }

    public UnoCard getCard(int cardIndex) {
        return playerHand.getCard(cardIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnoPlayer player = (UnoPlayer) o;
        return playerID == player.playerID && Objects.equals(name, player.name) && type == player.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID, name, type);
    }

    @Override
    public String toString() {
        return name;
    }
}
