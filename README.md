# TIC-TAC-TOE

- Board can be of any NxN size.
- There can be two players.
- Each player will be allotted a symbol.
- The symbol can be one of O and X.
- The players can be of type either human or bot.
- Each human player will have a name, email and profile image.
- Each bot player will have a difficulty level.
- Any random player can start the game.
- Then the players will take turns to play alternatively.
- The player with any consecutive N symbols in a row, column or diagonal wins. (check Winner)
- If the board is full and no player has won, the game is a draw.

## Steps

1. Board , Player, Bot , Game are entities.
2. Game contains Board, Player, Bot entities
3. Game contains move, startGame, gameStatus

```mermaid

classDiagram
    class Game {
        - Board board
        - Player player
        - Bot bot

        + void startGame()
        + void makeMove()
        + void gameStatus()
    }

    class Board {
        - Integer width
        - Integer height
        - Cell[][] cells


    }

    class Player {
        - String name
        - String email
        - Byte[] profileImg
        - Symbol symbol

        + void makeMove()
    }

    class Bot {
        - Level level

        + void makeMove()
    }

    class Cell {
        - Integer x
        - Integer y
        - Symbol symbol

        + Boolean isCellFilled()
    }

    class Level {
        <<Enumeration>>
        EASY
        MEDIUM
        HARD
    }

    class Symbol {
        <<Enumeration>>
        X
        O
    }

    Board <|-- Game
    Player *-- Game
    Bot o-- Game
    Cell o-- Board

    Symbol o-- Player
    Symbol o-- Bot

    Symbol o-- Cell
    Level o-- Bot



```

### Problems

- Implementation of move method in Bot will be different based on level of game will lead to if-else will lead to SRP and OCP violation.

- We should be able to have match between two human players or one human and one bot. Here it is not happening

### Solutions

- To avoid Srp and Ocp violation we use subclasses.

- Here we should create parent Player class and this will be extended by Bot and Human.

```mermaid
classDiagram
    class Player {
        <<abstract>>
        - Integer playerId
        + void makeMove() *
    }

    class HumanPlayer {
        - String name
        - String email
        - Byte[] profileImg

        + void makeMove()

    }

    class BotPlayer {
        <<abstract>>
        - Level level


        + void makeMove() *
    }

    class Level {
        <<Enumeration>>
        MIN_MAX
        RANDOM
        WISE
    }

    class MM {

        + void makeMove()
    }

    class Random {

        + void makeMove()
    }

    class Wise {
        + void makeMove()
    }

    Player <|-- HumanPlayer
    Player <|-- BotPlayer

    BotPlayer <|-- MM
    BotPlayer <|-- Random
    BotPlayer <|-- Wise

    Level o-- BotPlayer

```

- It is always better to have algorithm hierarchy seperately.

```mermaid
classDiagram

    class Game {

        <<abstract>>
        - Board board
        - Player[] players

        + void startGame()
        + void gameStatus()
    }

    class Board {
        - Integer rows
        - Integer cols
        - Cell[][] cells

    }

    class Cell {
        - Integer x
        - Integer y
        - Symbol symbol
    }

    class Symbol {
        <<Enumeration>>
        X
        O
    }


    Board *-- Game
    Player *-- Game
    Symbol o-- Cell

    class Player {
        <<abstract>>
        - Integer playerId
        - Symbol symbol
        + void makeMove() *
    }

    class HumanPlayer {
        - String name
        - String email
        - Byte[] profileImg

        + void makeMove()

    }

    class BotPlayer {
        <<abstract>>
        - Level level
        - GameStrategy gameStrategy

        + void makeMove() *
    }

    class GameStrategy {
        <<interface>>
        + void makeMove()*
    }

    class Level {
        <<Enumeration>>
        MIN_MAX
        RANDOM
        WISE
    }

    class MM {

        + void makeMove()
    }

    class Random {

        + void makeMove()
    }

    class Wise {
        + void makeMove()
    }

    Player <|-- HumanPlayer
    Player <|-- BotPlayer

    GameStrategy <|-- MM
    GameStrategy <|-- Random
    GameStrategy <|-- Wise

    Level o-- BotPlayer

    GameStrategy o-- BotPlayer

```
