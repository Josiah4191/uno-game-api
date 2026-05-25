# UNO Game API

Spring Boot backend API for an UNO card game.

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


