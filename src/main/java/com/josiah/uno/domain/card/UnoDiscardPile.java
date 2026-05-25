package com.josiah.uno.domain.card;

import java.util.ArrayList;
import java.util.List;

public class UnoDiscardPile {
    private final List<UnoCard> cards = new ArrayList<>();

    public UnoCard getTopCard() {
        int index = cards.size() - 1;
        return cards.get(index);
    }

    public void addCard(UnoCard card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public List<UnoCard> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}
