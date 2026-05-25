package com.josiah.uno.mapper;

import com.josiah.uno.domain.player.UnoPlayer;
import com.josiah.uno.dto.UnoCardResponse;
import com.josiah.uno.dto.UnoPlayerPrivateResponse;
import com.josiah.uno.dto.UnoPlayerPublicResponse;

import java.util.List;

public class UnoPlayerMapper {

    private final UnoCardMapper cardMapper = new UnoCardMapper();

    public UnoPlayerPrivateResponse toPrivateResponse(UnoPlayer player) {
        UnoPlayerPrivateResponse response = new UnoPlayerPrivateResponse();
        List<UnoCardResponse> cards = player.getHand().stream().map(cardMapper::toResponse).toList();
        response.setId(player.getPlayerID());
        response.setName(player.getName());
        response.setType(player.getType());
        response.setHandSize(player.getHandSize());
        response.setHand(cards);
        return response;
    }

    public UnoPlayerPublicResponse toPublicResponse(UnoPlayer player) {
        UnoPlayerPublicResponse response = new UnoPlayerPublicResponse();
        response.setId(player.getPlayerID());
        response.setName(player.getName());
        response.setType(player.getType());
        response.setHandSize(player.getHandSize());
        return response;
    }
}

/*
    private int id;
    private String name;
    private PlayerType type;
    private int handSize;
    private List<UnoCardResponse> hand;
 */
