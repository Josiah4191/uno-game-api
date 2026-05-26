# UNO Game API

An in-progress Spring Boot backend API for a rebuilt UNO game platform.

This project is a modern rebuild and expansion of an older UNO game project. The current focus is on modular game-state management, REST API design, multiplayer-oriented architecture concepts, and frontend/backend separation for future browser-based gameplay.

## Features

- Create and manage UNO games
- Play cards
- Draw cards
- Pass turns
- DTO-based REST API
- UUID-based game sessions

## Tech Stack

- Java
- Spring Boot
- Maven

## API Endpoints

```text
POST /api/uno/games
GET  /api/uno/games/{gameId}
POST /api/uno/games/{gameId}/play-card
POST /api/uno/games/{gameId}/draw-card
POST /api/uno/games/{gameId}/pass-turn
```


