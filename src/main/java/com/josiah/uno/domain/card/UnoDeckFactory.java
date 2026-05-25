package com.josiah.uno.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/*
Classic Uno Deck: 108 cards 0(x1), 1-9 (x2), SKIP(x2), REVERSE(x2), DRAW TWO(x2), WILD(x4), WILD DRAW FOUR(x4)
*/

public class UnoDeckFactory {
    public static List<UnoCard> createDeck() {
        ArrayList<UnoCard> deck = new ArrayList<>();
        deck.addAll(createColorCards(UnoColor.RED));
        deck.addAll(createColorCards(UnoColor.BLUE));
        deck.addAll(createColorCards(UnoColor.YELLOW));
        deck.addAll(createColorCards(UnoColor.GREEN));
        deck.addAll(createColorCards(UnoColor.WILD));
        Collections.shuffle(deck);
        return deck;
    }

    private static List<UnoCard> createColorCards(UnoColor color) {
        List<UnoValue> values = createValuesForColor(color);
        List<UnoCard> cards = new ArrayList<>();

        for (UnoValue value : values) {
            cards.add(new UnoCard(color, value));
        }

        return cards;
    }

    private static List<UnoValue> createValuesForColor(UnoColor color) {
        if (color == UnoColor.WILD) {
            return createValuesMatching(UnoValue::isWild);
        }

        List<UnoValue> values = new ArrayList<>();
        values.addAll(createValuesMatching(UnoValue::isNumber));
        values.addAll(createValuesMatching(UnoValue::isAction));
        return values;
    }

    private static List<UnoValue> createValuesMatching(Predicate<UnoValue> condition) {
        ArrayList<UnoValue> values = new ArrayList<>();

        for (UnoValue value : UnoValue.values()) {
            if (condition.test(value)) {
                for (int i = 0; i < value.getCount(); i++) {
                    values.add(value);
                }
            }
        }
        return values;
    }
}

