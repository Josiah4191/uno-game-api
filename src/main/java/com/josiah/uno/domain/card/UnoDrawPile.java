package com.josiah.uno.domain.card;
import java.util.List;

public class UnoDrawPile {

    private final List<UnoCard> cards;

    public UnoDrawPile(List<UnoCard> cards) {
        this.cards = cards;
    }

    public void fill(List<UnoCard> cards) {
        this.cards.addAll(cards);
    }

    public List<UnoCard> getCards() {
        return cards;
    }

    public void addCard(UnoCard card) {
        cards.add(card);
    }

    public UnoCard drawCard() {
        return cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }

}
