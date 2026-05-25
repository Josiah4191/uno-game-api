package com.josiah.uno;

import com.josiah.uno.domain.card.*;
import com.josiah.uno.domain.player.PlayerType;
import com.josiah.uno.domain.player.UnoPlayer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnoTableTest {

    @Test
    public void drawPileStartsWith108Cards() {
        UnoTable table = new UnoTable();
        assertEquals(108, table.getDrawPile().size());
    }

    @Test
    public void reloadingDrawPileKeepsTopDiscardCard() {
        UnoTable table = new UnoTable();

        // draw all cards
        for (int i = 0; i < 108; i++) {
            UnoCard card = table.drawCard();
            table.addCardToDiscardPile(card);
        }

        // check the discard pile size
        assertEquals(108, table.getDiscardPile().size());

        // check the draw pile size
        assertEquals(0, table.getDrawPile().size());

        // get the top card color and value before reloading
        UnoCard topCardBefore = table.getTopCard();

        // since draw pile is empty, try to draw another card to test that it reloads
        UnoCard card = table.drawCard();

        //test the size of discard and draw pile
        assertEquals(1, table.getDiscardPile().size());
        assertEquals(106, table.getDrawPile().size());

        // test the top card color and value after reloading
        UnoCard topCardAfter = table.getTopCard();
        assertEquals(topCardBefore.getColor(), topCardAfter.getColor());
        assertEquals(topCardBefore.getValue(), topCardAfter.getValue());
    }

    @Test
    public void dealCardsAddsCardsToPlayerHand() {
        // create table
        UnoTable table = new UnoTable();

        // create a player
        UnoPlayer player = new UnoPlayer("Josiah", PlayerType.HUMAN);

        // draw a card and add it to the discard pile
        UnoCard card = table.drawCard();
        table.addCardToDiscardPile(card);

        // get the size of the player's hand
        assertEquals(0, player.getHandSize());

        // add a single card to player
        UnoCard playerCard = table.drawCard();
        player.addCard(playerCard);

        // check the player has 1 card
        assertEquals(1, player.getHandSize());
        // check the discard pile has 1 card
        assertEquals(1, table.getDiscardPile().size());
        // check the draw pile has 106 cards
        assertEquals(106, table.getDrawPile().size());

        // use dealCards to add 6 cards to the player's hand
        table.dealCards(6, List.of(player));

        // check the player has 7 cards
        assertEquals(7, player.getHandSize());
        // check the discard pile has 1 card
        assertEquals(1, table.getDiscardPile().size());
        // check the draw pile has 106 cards
        assertEquals(100, table.getDrawPile().size());

    }

    @Test
    public void playedCardBecomesTopDiscardCard() {
        UnoTable table = new UnoTable();

        // draw a card and add it to the discard pile
        UnoCard card = table.drawCard();
        table.addCardToDiscardPile(card);

        // Get the top card from the discard pile
        UnoCard topCard = table.getTopCard();

        UnoPlayer player = new UnoPlayer("Josiah", PlayerType.HUMAN);

        // check player hand size is 0
        assertEquals(0, player.getHandSize());

        // draw a card and add it to the player's hand
        UnoCard playedCard = table.drawCard();
        player.addCard(playedCard);

        // check player hand size is 1
        assertEquals(1, player.getHandSize());

        // play the card for the player and add it to the discard pile
        player.playCard(0);
        table.addCardToDiscardPile(playedCard);

        // get the top card from the discard pile
        UnoCard newTopCard = table.getTopCard();

        // compare the new top card with the played card
        assertEquals(playedCard, newTopCard);

        // check the player has 0 cards
        assertEquals(0, player.getHandSize());
        // check the discard pile has 2 cards
        assertEquals(2, table.getDiscardPile().size());
        // check the draw pile has 100 cards
        assertEquals(106, table.getDrawPile().size());

    }

}
