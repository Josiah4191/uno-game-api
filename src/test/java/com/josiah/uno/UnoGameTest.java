package com.josiah.uno;

import com.josiah.uno.domain.card.UnoCard;
import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.card.UnoValue;
import com.josiah.uno.domain.game.PlayDirection;
import com.josiah.uno.domain.game.UnoGame;
import com.josiah.uno.domain.game.UnoGameState;
import com.josiah.uno.domain.player.PlayerType;
import com.josiah.uno.domain.player.UnoPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnoGameTest {

    @Test
    public void testUnoGame() {}

    @Test
    public void testDirectionChange() {
        UnoGame game = new UnoGame();
        UnoGameState state = game.getState();

        PlayDirection direction = state.getPlayDirection();

        assertSame(PlayDirection.FORWARD, direction);

        state.setPlayDirection(PlayDirection.REVERSE);

        assertSame(PlayDirection.REVERSE, state.getPlayDirection());

    }

    @Test
    public void testSetupPlayers() {
        UnoGame game = new UnoGame();
        UnoGameState state = game.getState();

        game.setupPlayers("Josiah", 2);

        // check that the game has 3 players
        assertEquals(3, state.getPlayers().size());

        // check the player name
        assertEquals("Josiah", state.getPlayers().get(0).getName());

        // check the player type
        assertEquals(PlayerType.HUMAN, state.getPlayers().get(0).getType());

        // check the other player name
        assertEquals("Bot 1", state.getPlayers().get(1).getName());

        // check the other player type
        assertEquals(PlayerType.COMPUTER, state.getPlayers().get(1).getType());

    }

    @Test
    public void testStartGame() {
        UnoGame game = new UnoGame();
        UnoGameState state = game.getState();

        game.setupPlayers("Josiah", 3);

        game.startGame();

        // check if every player has 7 cards
        state.getPlayers().forEach(player -> assertEquals(7, player.getHand().size()));

        // check the top card and make sure it isn't wild draw four
        UnoCard topCard = state.getTable().getTopCard();
        assertNotEquals(UnoValue.WILD_DRAW_FOUR, topCard.getValue());

        // check that there is a top card
        assertNotNull(topCard);

        // check that there are a total of 108 cards in draw pile, discard pile, and player hands
        int drawCount = state.getTable().getDrawPile().size();
        int discardCount = state.getTable().getDiscardPile().size();
        int handCount = state.getPlayers().stream().mapToInt(player -> player.getHand().size()).sum();
        assertEquals(108, drawCount + discardCount + handCount);

    }

    @Test
    public void openingSkipSkipsFirstPlayer() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);
        game.getState().getTable().dealCards(7, game.getState().getPlayers());
        game.getState().setCurrentPlayerIndex(0);

        UnoCard skip = new UnoCard(UnoColor.RED, UnoValue.SKIP);
        playSpecificOpeningCard(game, skip);

        // check the current player index is 1
        assertEquals(1, game.getState().getCurrentPlayerIndex());
    }

    @Test
    public void openingReverseChangesDirection() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);
        game.getState().getTable().dealCards(7, game.getState().getPlayers());
        game.getState().setCurrentPlayerIndex(0);

        UnoCard reverse = new UnoCard(UnoColor.RED, UnoValue.REVERSE);
        playSpecificOpeningCard(game, reverse);

        // check the new play direction is reverse
        assertEquals(PlayDirection.REVERSE, game.getState().getPlayDirection());

        // check the current player index is 3 (4 players total)
        assertEquals(3, game.getState().getCurrentPlayerIndex());
    }

    @Test
    public void openingDrawTwoDrawsTwoCards() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);
        game.getState().getTable().dealCards(7, game.getState().getPlayers());
        game.getState().setCurrentPlayerIndex(0);

        UnoCard drawTwo = new UnoCard(UnoColor.RED, UnoValue.DRAW_TWO);
        playSpecificOpeningCard(game, drawTwo);

        // check the current player index is 1
        assertEquals(1, game.getState().getCurrentPlayerIndex());
        // check the player hand size is 9
        assertEquals(9, game.getState().getPlayers().get(0).getHand().size());
    }

    private void playSpecificOpeningCard(UnoGame game, UnoCard card) {
        game.getState().getTable().addCardToDiscardPile(card);
        game.getState().setCurrentColor(card.getColor());
        game.applyOpeningCardEffect(card);
    }

    @Test
    public void skipCardSkipsPlayer() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);

        // set current player index to 0
        game.getState().setCurrentPlayerIndex(0);

        // create red top card
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.FOUR);
        game.setTopCard(topCard);

        UnoPlayer player = game.getState().getPlayer(0);
        // give the player a playable red skip card
        player.addCard(new UnoCard(UnoColor.RED, UnoValue.SKIP));

        assertEquals(1, player.getHand().size());

        // play the skip card
        game.playCard(0, 0);

        // check the current player index is 2
        assertEquals(2, game.getState().getCurrentPlayerIndex());
    }

    @Test
    public void reverseCardChangesDirection() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);

        // set current player index to 0
        game.getState().setCurrentPlayerIndex(0);

        // create red top card
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.ONE);
        game.setTopCard(topCard);

        UnoPlayer player = game.getState().getPlayer(0);
        // give the player a playable red reverse card
        player.addCard(new UnoCard(UnoColor.RED, UnoValue.REVERSE));

        assertEquals(1, player.getHand().size());

        // play the reverse card
        game.playCard(0, 0);

        // check the current player index is 3 (4 total players)
        assertEquals(3, game.getState().getCurrentPlayerIndex());
    }

    @Test
    public void drawTwoCardDrawsTwoCards() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);

        // set current player index to 0
        game.getState().setCurrentPlayerIndex(0);

        // create red top card
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.ONE);
        game.setTopCard(topCard);

        UnoPlayer player = game.getState().getPlayer(0);
        // give the player a playable red draw two card
        player.addCard(new UnoCard(UnoColor.RED, UnoValue.DRAW_TWO));

        assertEquals(1, player.getHand().size());

        // play the draw two card
        game.playCard(0, 0);

        // check the current player index is 2
        assertEquals(2, game.getState().getCurrentPlayerIndex());
        // check hand size is 2 for player at index 2
        assertEquals(2, game.getState().getPlayers().get(1).getHand().size());
    }

    @Test
    public void wildDrawFourCardDrawsFourCards() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);

        // set current player index to 0
        game.getState().setCurrentPlayerIndex(0);

        // create red top card
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.ONE);
        game.setTopCard(topCard);

        UnoPlayer player = game.getState().getPlayer(0);
        // give the player a playable red draw two card
        player.addCard(new UnoCard(UnoColor.WILD, UnoValue.WILD_DRAW_FOUR));

        assertEquals(1, player.getHand().size());

        // play the draw four card
        game.playCard(0, 0, UnoColor.YELLOW);

        // check the current player index is 2
        assertEquals(2, game.getState().getCurrentPlayerIndex());
        // check hand size is 2 for player at index 2
        assertEquals(4, game.getState().getPlayers().get(1).getHand().size());

        // check the current color is yellow
        assertEquals(UnoColor.YELLOW, game.getState().getCurrentColor());
    }

    @Test
    public void playCardRemovesCardFromHand() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);

        // set current player index to 0
        game.getState().setCurrentPlayerIndex(0);

        // create red top card
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.ONE);
        game.setTopCard(topCard);

        UnoPlayer player = game.getState().getPlayer(0);
        // give the player a playable red draw two card
        player.addCard(new UnoCard(UnoColor.WILD, UnoValue.WILD_DRAW_FOUR));

        // check that the player has 1 card in their hand
        assertEquals(1, player.getHand().size());

        // play the draw four card
        game.playCard(0, 0, UnoColor.YELLOW);

        // check that the player has 0 cards in their hand
        assertEquals(0, player.getHand().size());
    }

    @Test
    public void playedCardBecomesTopCard() {
        UnoGame game = new UnoGame();
        game.setupPlayers("Josiah", 3);

        // set current player index to 0
        game.getState().setCurrentPlayerIndex(0);

        // create red top card
        UnoCard topCard = new UnoCard(UnoColor.RED, UnoValue.ONE);
        game.setTopCard(topCard);

        UnoPlayer player = game.getState().getPlayer(0);
        // give the player a playable red card 4
        UnoCard playedCard = new UnoCard(UnoColor.RED, UnoValue.FOUR);
        player.addCard(playedCard);

        // play red card 4
        game.playCard(0, 0);

        // check that the top card is red 4
        assertEquals(playedCard, game.getState().getTable().getTopCard());
    }

}
