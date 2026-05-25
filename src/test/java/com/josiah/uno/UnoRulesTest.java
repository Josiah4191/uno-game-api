package com.josiah.uno;

import com.josiah.uno.domain.card.UnoCard;
import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.card.UnoTable;
import com.josiah.uno.domain.card.UnoValue;
import com.josiah.uno.domain.game.UnoGameState;
import com.josiah.uno.domain.game.UnoRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnoRulesTest {

    @Test
    public void cardPlayableByWild() {
        UnoGameState state = new UnoGameState();
        UnoRules rules = new UnoRules();

        // draw a card and add it to the discard pile
        UnoCard topCard = state.getTable().drawCard();
        state.getTable().addCardToDiscardPile(topCard);
        state.setCurrentColor(topCard.getColor());

        // create a wild card
        UnoCard card = new UnoCard(UnoColor.WILD, UnoValue.WILD);

        // check if the card is playable
        boolean isPlayable = rules.isPlayable(card, state);

        assertTrue(isPlayable);
    }

    @Test
    public void cardPlayableByValue() {
        UnoGameState state = new UnoGameState();
        UnoRules rules = new UnoRules();

        // create a card and add it to the discard pile
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.FOUR);
        state.getTable().addCardToDiscardPile(topCard);
        state.setCurrentColor(topCard.getColor());

        // create the same card with a different value
        UnoCard card = new UnoCard(UnoColor.BLUE, UnoValue.FOUR);

        // check if the card is playable
        boolean isPlayable = rules.isPlayable(card, state);

        // card should be playable
        assertTrue(isPlayable);

    }

    @Test
    public void cardPlayableByColor() {
        UnoGameState state = new UnoGameState();
        UnoRules rules = new UnoRules();

        // create a card and add it to the discard pile
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.FOUR);
        state.getTable().addCardToDiscardPile(topCard);
        state.setCurrentColor(topCard.getColor());

        // create the same card with a different color
        UnoCard card = new UnoCard(UnoColor.RED, UnoValue.FIVE);

        // check if the card is playable
        boolean isPlayable = rules.isPlayable(card, state);

        // card should be playable
        assertTrue(isPlayable);
    }

    @Test
    public void invalidCardPlay() {
        UnoGameState state = new UnoGameState();
        UnoRules rules = new UnoRules();

        // create a card and add it to the discard pile
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.FOUR);
        state.getTable().addCardToDiscardPile(topCard);
        state.setCurrentColor(topCard.getColor());

        // create the same card with a different color
        UnoCard card = new UnoCard(UnoColor.BLUE, UnoValue.FIVE);

        // check if the card is playable
        boolean isPlayable = rules.isPlayable(card, state);

        // card should be playable
        assertFalse(isPlayable);
    }




}
