package dev.sllcoding.gameception.games.tictactoe;

import dev.sllcoding.gameception.games.framework.*;
import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.results.GameResult;
import net.minestom.server.entity.Entity;

import java.util.List;
import java.util.Optional;

public class TicTacToeGame implements Game {
    private final GameBoard ticTacToeBoard;
    private final List<GamePlayer> gamePlayers;
    private final List<WinCondition> winConditions;
    private final List<GameObject> gameObjects;
    private final Team[] turnProgression;

    private int currentTurnIndex = 0;

    public TicTacToeGame(TicTacToeBoard ticTacToeBoard,
                         List<GamePlayer> gamePlayers,
                         List<WinCondition> winConditions,
                         List<GameObject> gameObjects,
                         Team[] turnProgression) {
        this.ticTacToeBoard = ticTacToeBoard;
        this.gamePlayers = gamePlayers;
        this.winConditions = winConditions;
        this.gameObjects = gameObjects;
        this.turnProgression = turnProgression;
    }

    @Override
    public List<GamePlayer> getPlayers() {
        return gamePlayers;
    }

    @Override
    public List<WinCondition> getWinConditions() {
        return winConditions;
    }

    @Override
    public List<GameObject> getObjects() {
        return gameObjects;
    }

    @Override
    public GameBoard getBoard() {
        return ticTacToeBoard;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public void update(Entity entity) {
        Team currentTurn = getCurrentTurn();
        List<GameObject> objects = getObjects();

        Optional<GamePlayer> gamePlayerOptional = getPlayers().stream()
                .filter(gamePlayer -> gamePlayer.getTeam().equals(currentTurn))
                .findFirst();

        if (gamePlayerOptional.isEmpty()) {
            return;
        }

        GamePlayer gamePlayer = gamePlayerOptional.get();

        Optional<GameObject> gameObjectOptional = objects.stream()
                .filter(gameObject -> gameObject.getTeam().equals(currentTurn))
                .findFirst();

        if (gameObjectOptional.isEmpty()) {
            return;
        }

        GameObject gameObject = gameObjectOptional.get();

        GameBoard board = getBoard();
        board.place(entity, gameObject);

        for (WinCondition winCondition : winConditions) {
            Optional<GameResult> resultOptional = winCondition.getResultOptional(this, gamePlayer);

            if (resultOptional.isEmpty()) {
                continue;
            }

            GameResult gameResult = resultOptional.get();
            gameResult.execute(this);
            return;
        }
    }

    @Override
    public Team getCurrentTurn() {
        if (currentTurnIndex >= turnProgression.length) {
            currentTurnIndex = 0;
        }

        int turn = currentTurnIndex++;
        return turnProgression[turn];
    }
}
