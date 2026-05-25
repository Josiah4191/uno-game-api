package com.josiah.uno.domain.game;

import com.josiah.uno.domain.card.UnoCard;
import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.player.UnoPlayer;

public class UnoRules {
    public boolean isPlayable(UnoCard card, UnoGameState state) {
        UnoCard topCard = state.getTable().getTopCard();
        UnoColor currentColor = state.getCurrentColor();

        if (card.getColor() == UnoColor.WILD) {
            return true;
        }

        if (card.getColor() == currentColor) {
            return true;
        }

        return card.getValue() == topCard.getValue();
    }

    public boolean shouldApplyUnoPenalty(UnoPlayer player) {
        return true;
    }
}
