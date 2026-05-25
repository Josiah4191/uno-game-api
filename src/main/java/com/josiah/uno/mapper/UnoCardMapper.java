package com.josiah.uno.mapper;

import com.josiah.uno.domain.card.UnoCard;
import com.josiah.uno.dto.UnoCardResponse;

public class UnoCardMapper {
    public UnoCardResponse toResponse(UnoCard card) {
        UnoCardResponse response = new UnoCardResponse();
        response.setColor(card.getColor());
        response.setValue(card.getValue());
        return response;
    }
}
