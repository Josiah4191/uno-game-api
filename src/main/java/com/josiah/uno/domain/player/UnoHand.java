package com.josiah.uno.domain.player;
import com.josiah.uno.domain.card.UnoCard;

import java.util.ArrayList;
import java.util.List;

public class UnoHand {
    private final List<UnoCard> cards = new ArrayList<>();

    public void fill(List<UnoCard> cards) {
        this.cards.addAll(cards);
    }

    public List<UnoCard> getCards() {
        return cards;
    }

    public void addCard(UnoCard card) {
        cards.add(card);
    }

    public UnoCard playCard(int cardIndex) {
        return cards.remove(cardIndex);
    }

    public UnoCard getCard(int cardIndex) {
        return cards.get(cardIndex);
    }

    public int size() {
        return cards.size();
    }
}
