package com.josiah.uno.controller;

import com.josiah.uno.dto.UnoCreateGameRequest;
import com.josiah.uno.dto.UnoCreateGameResponse;
import com.josiah.uno.dto.UnoGameStateResponse;
import com.josiah.uno.dto.UnoPlayCardRequest;
import com.josiah.uno.service.UnoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/uno/games")
public class UnoGameController {

    private final UnoGameService gameService;

    @Autowired
    public UnoGameController(UnoGameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("")
    public UnoCreateGameResponse createGame(@RequestBody UnoCreateGameRequest request) {
        return gameService.createGame(request);
    }

    @GetMapping("/{gameId}")
    public UnoGameStateResponse getGameState(@PathVariable UUID gameId) {
        return gameService.getGameState(gameId);
    }

    @PostMapping("/{gameId}/play-card")
    public UnoGameStateResponse playCard(@PathVariable UUID gameId, @RequestBody UnoPlayCardRequest request) {
        return gameService.playCard(request, gameId);
    }

    @PostMapping("/{gameId}/pass-turn")
    public UnoGameStateResponse passTurn(@PathVariable UUID gameId) {
        return gameService.passTurn(gameId);
    }

    @PostMapping("/{gameId}/draw-card")
    public UnoGameStateResponse drawCard(@PathVariable UUID gameId) {
        return gameService.drawCard(gameId);
    }



}
