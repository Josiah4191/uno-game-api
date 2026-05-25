package com.josiah.uno.domain.card;

import com.josiah.uno.domain.player.UnoPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnoTable {
    private final UnoDrawPile drawPile = new UnoDrawPile(UnoDeckFactory.createDeck());
    private final UnoDiscardPile discardPile = new UnoDiscardPile();

    public UnoCard drawCard() {
        if (drawPile.getCards().isEmpty()) {
            reloadDrawPile();
        }

        if (drawPile.getCards().isEmpty()) {
            throw new IllegalStateException("Draw pile is empty");
        }

        return drawPile.drawCard();
    }

    private void reloadDrawPile() {
        UnoCard topCard = discardPile.getTopCard();
        discardPile.getCards().remove(topCard);

        List<UnoCard> cards = new ArrayList<>(discardPile.getCards());

        drawPile.fill(cards);
        Collections.shuffle(drawPile.getCards());

        discardPile.clear();
        discardPile.addCard(topCard);
    }

    public void addCardToDiscardPile(UnoCard card) {
        discardPile.addCard(card);
    }

    public void addCardToDrawPile(UnoCard card) {
        drawPile.addCard(card);
    }

    public List<UnoCard> getDrawPile() {
        return Collections.unmodifiableList(drawPile.getCards());
    }

    public List<UnoCard> getDiscardPile() {
        return Collections.unmodifiableList(discardPile.getCards());
    }

    public UnoCard getTopCard() {
        if (discardPile.getCards().isEmpty()) {
            throw new IllegalStateException("Discard pile is empty");
        }

        return discardPile.getTopCard();
    }

    public void shuffleDrawPile() {
        Collections.shuffle(drawPile.getCards());
    }

    public void dealCards(int numberOfCards, List<UnoPlayer> players) {
        for (var player : players) {
            for (int i = 0; i < numberOfCards; i++) {
                player.addCard(drawCard());
            }
        }
    }

}
