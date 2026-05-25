package com.josiah.uno;

import com.josiah.uno.domain.card.UnoCard;
import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.card.UnoValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnoCardTest {

    @Test
    public void testWildCard() {
        UnoCard wild = new UnoCard(UnoColor.WILD, UnoValue.WILD);
        UnoCard wildDrawFour = new UnoCard(UnoColor.WILD, UnoValue.WILD_DRAW_FOUR);

        // wild should be a wild card
        assertEquals(UnoColor.WILD, wild.getColor());
        assertEquals(UnoValue.WILD, wild.getValue());

        // wildDrawFour should be a wild draw four card
        assertEquals(UnoColor.WILD, wildDrawFour.getColor());
        assertEquals(UnoValue.WILD_DRAW_FOUR, wildDrawFour.getValue());

        // wild cards return true if they're wild, and false if they're not
        assertTrue(wild.getValue().isWild());
        assertTrue(wildDrawFour.getValue().isWild());

        assertFalse(wild.getValue().isAction());
        assertFalse(wild.getValue().isNumber());

        assertFalse(wildDrawFour.getValue().isAction());
        assertFalse(wildDrawFour.getValue().isNumber());

    }

    @Test
    public void testNumberCard() {
        UnoCard redFour = new UnoCard(UnoColor.RED, UnoValue.FOUR);
        UnoCard yellowThree = new UnoCard(UnoColor.YELLOW, UnoValue.THREE);

        // redFour should be red and four
        assertEquals(UnoColor.RED, redFour.getColor());
        assertEquals(UnoValue.FOUR, redFour.getValue());

        // yellowThree should be yellow and three
        assertEquals(UnoColor.YELLOW, yellowThree.getColor());
        assertEquals(UnoValue.THREE, yellowThree.getValue());

        // number cards return true if they're a number, and false if they're not
        assertTrue(redFour.getValue().isNumber());
        assertTrue(yellowThree.getValue().isNumber());

        assertFalse(redFour.getValue().isAction());
        assertFalse(yellowThree.getValue().isAction());

        assertFalse(redFour.getValue().isWild());
        assertFalse(yellowThree.getValue().isWild());
    }

    @Test
    public void testActionCard() {
        UnoCard blueSkip = new UnoCard(UnoColor.BLUE, UnoValue.SKIP);
        UnoCard greenReverse = new UnoCard(UnoColor.GREEN, UnoValue.REVERSE);
        UnoCard yellowDrawTwo = new UnoCard(UnoColor.YELLOW, UnoValue.DRAW_TWO);

        // blueSkip should be blue and skip
        assertEquals(UnoColor.BLUE, blueSkip.getColor());
        assertEquals(UnoValue.SKIP, blueSkip.getValue());

        // greenReverse should be green and reverse
        assertEquals(UnoColor.GREEN, greenReverse.getColor());
        assertEquals(UnoValue.REVERSE, greenReverse.getValue());

        // yellowDrawTwo should be yellow and draw two
        assertEquals(UnoColor.YELLOW, yellowDrawTwo.getColor());
        assertEquals(UnoValue.DRAW_TWO, yellowDrawTwo.getValue());

        // number cards return true if they're a number, and false if they're not
        assertTrue(blueSkip.getValue().isAction());
        assertTrue(greenReverse.getValue().isAction());
        assertTrue(yellowDrawTwo.getValue().isAction());

        assertFalse(blueSkip.getValue().isWild());
        assertFalse(greenReverse.getValue().isWild());
        assertFalse(yellowDrawTwo.getValue().isWild());

        assertFalse(blueSkip.getValue().isNumber());
        assertFalse(greenReverse.getValue().isNumber());
        assertFalse(yellowDrawTwo.getValue().isNumber());
    }

}
