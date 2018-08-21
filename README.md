Backbase Coding Challenge
------
What is Kalah game?
------
**Kalah**, also called Kalaha or Mancala, is a game in the mancala family imported in the United States.

The game provides a Kalah board and a number of seeds or counters. The board has 6 small pits, called houses, on each side; and a big pit, called an end zone, at each end. The objective of the game is to capture more seeds than one's opponent.

* At the beginning of the game, four[six] seeds are placed in each house. This is the traditional method.
* Each player controls the six houses and their seeds on the player's side of the board. The player's score is the number of seeds in the store to their right.
* Players take turns sowing their seeds. On a turn, the player removes all seeds from one of the houses under their control. Moving counter-clockwise, the player drops one seed in each house in turn, including the player's own store but not their opponent's.
* If the last sown seed lands in an empty house owned by the player, and the opposite house contains seeds, both the last seed and the opposite seeds are captured and placed into the player's store.
* If the last sown seed lands in the player's store, the player gets an additional move. There is no limit on the number of moves a player can make in their turn.
* When one player no longer has any seeds in any of their houses, the game ends. The other player moves all remaining seeds to their store, and the player with the most seeds in their store wins.
It is possible for the game to end in a draw.

[Link to wikipedia](https://en.wikipedia.org/wiki/Kalah)

RESTful Web Service
------
***Kalah-sixpits*** provides REST services to create a Kalah board and play the game.

Technologies:

* java 8
* maven
* spring-boot 2.0.3
* spring-mvc
* MockMVC
* springJUnit4
* spring-boot-actuator
* swagger 2.5.0
* Log4j

Web Service API
------
- POST    http://localhost:8080/games
>Create a new game board. \
--header "Content-Type: application/json" \
--request POST 

- PUT     http://localhost:8080/games/{gameid}/pits/{pitid}
> Move and distribute stones of a pit. \
--header "Content-Type: application/json" \
--request PUT
   
Sample Creation API Response Body:
------
```
{
     "id": "c40cdf8f-663e-455b-bb72-df1291d54257",
     "url": "http://localhost:8080/c40cdf8f-663e-455b-bb72-df1291d54257"
}
   ```

Sample Move API Response Body:
------
```
{
    "id": "c40cdf8f-663e-455b-bb72-df1291d54257",
    "url": "http://localhost:8080/c40cdf8f-663e-455b-bb72-df1291d54257",
    "status": {
        "1": "6",
        "2": "0",
        "3": "7",
        "4": "7",
        "5": "7",
        "6": "7",
        "7": "1",
        "8": "7",
        "9": "6",
        "10": "6",
        "11": "6",
        "12": "6",
        "13": "6",
        "14": "0"
    }
}
```

## API Documentation - Swagger 2
Kalah-sixpits API's documentation is accessible through below URL:
* http://localhost:8080/swagger-ui.html

## Logging - Log4j
- Log file relative path:       Kalah-sixpits/Logs/kalah.log
- Only information level logs are available

## Check application status - actuator

- INFO       - http://localhost:8080/actuator/info 
- HEALTH     - http://localhost:8080/actuator/health
