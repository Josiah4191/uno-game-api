package com.josiah.uno.mapper;

import com.josiah.uno.domain.game.UnoGameState;
import com.josiah.uno.dto.UnoGameStateResponse;

public class UnoGameStateMapper {

    private final UnoCardMapper cardMapper = new UnoCardMapper();

    public UnoGameStateResponse toResponse(UnoGameState state) {
        UnoGameStateResponse response = new UnoGameStateResponse();
        response.setCurrentColor(state.getCurrentColor());
        response.setPlayDirection(state.getPlayDirection());
        response.setCurrentPlayerIndex(state.getCurrentPlayerIndex());
        response.setTopCard(cardMapper.toResponse(state.getTable().getTopCard()));
        response.setOpponents(
                state.getPlayers()
                        .stream()
                        .map(player -> new UnoPlayerMapper().toPublicResponse(player))
                        .toList());
        return response;
    }
}
