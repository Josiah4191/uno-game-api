package com.josiah.uno.service;

import com.josiah.uno.domain.card.UnoColor;
import com.josiah.uno.domain.game.UnoGame;
import com.josiah.uno.dto.*;
import com.josiah.uno.mapper.UnoCardMapper;
import com.josiah.uno.mapper.UnoGameStateMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UnoGameService {

    private final Map<UUID, UnoGame> games = new HashMap<>();
    private final UnoGameStateMapper gameStateMapper = new UnoGameStateMapper();
    private final UnoCardMapper cardMapper = new UnoCardMapper();

    // create game
    public UnoCreateGameResponse createGame(UnoCreateGameRequest request) {
        UnoGame game = new UnoGame();
        UUID gameId = UUID.randomUUID();
        games.put(gameId, game);

        game.initializeGame(request.getPlayerName(), request.getComputerPlayerCount());
        UnoGameStateResponse gameState = gameStateMapper.toResponse(game.getState());
        UnoCreateGameResponse response = new UnoCreateGameResponse();
        response.setGameId(gameId);
        response.setGameState(gameState);

        return response;
    }

    // play card
    public UnoGameStateResponse playCard(UnoPlayCardRequest request, UUID gameId) {
        UnoGame game = games.get(gameId);
        int playerIndex = request.getPlayerIndex();
        int cardIndex = request.getCardIndex();
        UnoColor chosenColor = request.getChosenColor();
        game.playCard(playerIndex, cardIndex, chosenColor);

        return gameStateMapper.toResponse(game.getState());
    }

    // get game state
    public UnoGameStateResponse getGameState(UUID gameId) {
        UnoGame game = games.get(gameId);
        return gameStateMapper.toResponse(game.getState());
    }

    // draw card
    public UnoGameStateResponse drawCard(UUID gameId) {
        UnoGame game = games.get(gameId);
        game.drawCardForPlayer(game.getState().getCurrentPlayerIndex());
        return gameStateMapper.toResponse(game.getState());
    }

    // pass turn
    public UnoGameStateResponse passTurn(UUID gameId) {
        UnoGame game = games.get(gameId);
        game.passTurn(game.getState().getCurrentPlayerIndex());
        return gameStateMapper.toResponse(game.getState());
    }

}
