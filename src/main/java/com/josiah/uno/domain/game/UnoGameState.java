package com.josiah.uno.domain.game;

import com.josiah.uno.domain.card.UnoCard;
import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.card.UnoTable;
import com.josiah.uno.domain.player.UnoPlayer;

import java.util.ArrayList;
import java.util.List;

public class UnoGameState {

    private List<UnoPlayer> players = new ArrayList<>();
    private UnoColor color;
    private UnoTable table = new UnoTable();
    private int currentPlayerIndex;
    private PlayDirection playDirection = PlayDirection.FORWARD;

    public UnoPlayer getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public int getPlayerIndex(UnoPlayer player) {
        return players.indexOf(player);
    }

    public List<UnoPlayer> getPlayers() {
        return players;
    }

    public void addPlayers(List<UnoPlayer> players) {
        for (var player : players) {
            addPlayer(player);
        }
    }

    public void addPlayer(UnoPlayer player) {
        players.add(player);
    }

    public UnoPlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public UnoColor getCurrentColor() {
        return color;
    }

    public void setCurrentColor(UnoColor color) {
        this.color = color;
    }

    public PlayDirection getPlayDirection() {
        return playDirection;
    }

    public void setPlayDirection(PlayDirection playDirection) {
        this.playDirection = playDirection;
    }

    public List<UnoCard> getDrawPile() {
        return table.getDrawPile();
    }

    public List<UnoCard> getDiscardPile() {
        return table.getDiscardPile();
    }

    public UnoTable getTable() {
        return table;
    }

    public void setTable(UnoTable table) {
        this.table = table;
    }

    public void setPlayers(List<UnoPlayer> players) {
        this.players = players;
    }

}
