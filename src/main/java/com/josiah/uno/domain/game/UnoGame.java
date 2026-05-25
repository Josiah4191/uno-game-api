package com.josiah.uno.domain.game;

import com.josiah.uno.domain.card.UnoCard;
import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.card.UnoValue;
import com.josiah.uno.domain.player.PlayerType;
import com.josiah.uno.domain.player.UnoPlayer;

import java.util.Random;

public class UnoGame {

    private final UnoGameState state = new UnoGameState();
    private final UnoRules rules = new UnoRules();

    public UnoGameState getState() {
        return state;
    }

    public void initializeGame(String playerName, int computerCount) {
        setupPlayers(playerName, computerCount);
        selectFirstPlayer();
        startGame();
    }

    public void startGame() {
        state.getTable().dealCards(7, state.getPlayers());
        playOpeningCard();
    }

    public void setupPlayers(String playerName, int computerCount) {
        addPlayer(playerName, PlayerType.HUMAN);
        addComputerPlayers(computerCount);
    }

    public void playOpeningCard() {
        UnoCard card;

        do {
            card = state.getTable().drawCard();

            if (card.getValue() == UnoValue.WILD_DRAW_FOUR) {
                state.getTable().addCardToDrawPile(card);
                state.getTable().shuffleDrawPile();
            }

        } while (card.getValue() == UnoValue.WILD_DRAW_FOUR);

        state.getTable().addCardToDiscardPile(card);
        state.setCurrentColor(card.getColor());
        applyOpeningCardEffect(card);
    }

    public void playOpeningCard(UnoCard card) {
        state.getTable().addCardToDiscardPile(card);
        state.setCurrentColor(card.getColor());
        applyOpeningCardEffect(card);
    }

    public UnoCard drawCardForPlayer(int playerIndex) {
        UnoPlayer player = state.getPlayer(playerIndex);
        UnoCard card = state.getTable().drawCard();
        player.addCard(card);
        return card;
    }

    public boolean canPlayCard(UnoCard card) {
        return rules.isPlayable(card, state);
    }

    public void callUno(int playerIndex) {
        UnoPlayer player = state.getPlayer(playerIndex);
        if (rules.shouldApplyUnoPenalty(player)) {
            applyPenalty(playerIndex, 2);
        }
    }

    public void playCard(int playerIndex, int cardIndex, UnoColor chosenColor) {
        UnoPlayer player = state.getPlayer(playerIndex);
        UnoCard card = player.getCard(cardIndex);

        if (!(playerIndex == state.getCurrentPlayerIndex())) {
            return;
        }

        if ((card.getValue() == UnoValue.WILD || card.getValue() == UnoValue.WILD_DRAW_FOUR) && chosenColor == null) {
            return;
        }

        if (!(rules.isPlayable(card, state))) {
            return;
        }

        UnoCard playedCard = player.playCard(cardIndex);
        state.getTable().addCardToDiscardPile(playedCard);

        if ((playedCard.getValue() == UnoValue.WILD) || (playedCard.getValue() == UnoValue.WILD_DRAW_FOUR)) {
            state.setCurrentColor(chosenColor);
        } else {
            state.setCurrentColor(playedCard.getColor());
        }

        applyCardEffect(playedCard);

        if (player.getHandSize() == 0) {
            //winner
        }
    }

    public void setTopCard(UnoCard card) {
        state.getTable().addCardToDiscardPile(card);
        state.setCurrentColor(card.getColor());
    }

    public void playCard(int playerIndex, int cardIndex) {
        playCard(playerIndex, cardIndex, null);
    }

    public void passTurn(int playerIndex) {
        if (playerIndex != state.getCurrentPlayerIndex()) {
            return;
        }
        moveToNextPlayer(1);
    }

    public void drawCards(int count) {
        for (int i = 0; i < count; i++) {
            drawCardForPlayer(state.getCurrentPlayerIndex());
        }
    }

    // Remember the concept of passing or advancing turn if card isn't playable
        /*
        player draws a card
        if they cannot/will not play it
        they pass
        turn advances
        */

    public void applyPenalty(int playerIndex, int penalty) {
        UnoPlayer player = state.getPlayer(playerIndex);
        for (int i = 0; i < penalty; i++) {
            drawCardForPlayer(playerIndex);
        }
    }

    public void addPlayer(String name, PlayerType type) {
        UnoPlayer player = new UnoPlayer(name, type);
        state.addPlayer(player);
    }

    private void addComputerPlayers(int count) {
        for (int i = 0; i < count; i++) {
            addPlayer("Bot " + (i + 1), PlayerType.COMPUTER);
        }
    }

    public void applyOpeningCardEffect(UnoCard card) {
        int currentPlayerIndex = state.getCurrentPlayerIndex();

        switch (card.getValue()) {
            case REVERSE -> {
                reversePlayDirection();

                if (state.getPlayers().size() == 2) {
                    moveToNextPlayer(2);
                } else {
                    moveToNextPlayer(1);
                }
            }

            case SKIP -> moveToNextPlayer(1);

            case DRAW_TWO -> {
                applyPenalty(currentPlayerIndex, 2);
                moveToNextPlayer(1);
            }

            case WILD_DRAW_FOUR -> {
                applyPenalty(currentPlayerIndex, 4);
                moveToNextPlayer(1);
            }

            default -> {
            }
        }
    }

    private void applyCardEffect(UnoCard card) {
        int nextPlayerIndex = getNextPlayerIndex(1);

        switch (card.getValue()) {
            case REVERSE -> {
                reversePlayDirection();

                if (state.getPlayers().size() == 2) {
                    moveToNextPlayer(2);
                } else {
                    moveToNextPlayer(1);
                }
            }

            case SKIP -> moveToNextPlayer(2);

            case DRAW_TWO -> {
                applyPenalty(nextPlayerIndex, 2);
                moveToNextPlayer(2);
            }

            case WILD_DRAW_FOUR -> {
                applyPenalty(nextPlayerIndex, 4);
                moveToNextPlayer(2);
            }

            default -> moveToNextPlayer(1);
        }
    }

    private void reversePlayDirection() {
        state.setPlayDirection(
                state.getPlayDirection() == PlayDirection.FORWARD
                        ? PlayDirection.REVERSE
                        : PlayDirection.FORWARD
        );
    }

    private int getNextPlayerIndex(int offset) {
        int currentPlayerIndex = state.getCurrentPlayerIndex();
        int numberOfPlayers = state.getPlayers().size();

        if (numberOfPlayers < 2) {
            throw new IllegalStateException("UNO requires at least 2 players to play.");
        }

        if (state.getPlayDirection() == PlayDirection.FORWARD) {
            currentPlayerIndex += offset;
        } else {
            currentPlayerIndex -= offset;
        }

        return (currentPlayerIndex % numberOfPlayers + numberOfPlayers) % numberOfPlayers;
    }

    private void moveToNextPlayer(int offset) {
        int nextPlayerIndex = getNextPlayerIndex(offset);
        state.setCurrentPlayerIndex(nextPlayerIndex);
    }

    private void selectFirstPlayer() {
        int index = new Random().nextInt(state.getPlayers().size());
        state.setCurrentPlayerIndex(index);
    }

    public void verifyCardCounts() {
        int discardPileSize = state.getDiscardPile().size();
        int drawPileSize = state.getDrawPile().size();
        int totalPileSize = discardPileSize + drawPileSize;

        System.out.println("Total number of cards in draw pile: " + drawPileSize);
        System.out.println("Total number of cards in discard pile: " + discardPileSize);

        for (UnoPlayer player : state.getPlayers()) {
            int totalCards = player.getHand().size();
            System.out.println("Player: " + player.getName() + " | " + "Total number of cards: " + totalCards);
            totalPileSize += totalCards;
        }
        System.out.println("Total number of cards: " + totalPileSize + "\n");
    }

}
