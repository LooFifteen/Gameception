package dev.sllcoding.gameception.games.tictactoe;

import dev.sllcoding.gameception.games.framework.*;
import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.results.GameResult;
import dev.sllcoding.gameception.games.tictactoe.conditions.DiagonalConditionTicTacToe;
import dev.sllcoding.gameception.games.tictactoe.conditions.HorizontalConditionTicTacToe;
import dev.sllcoding.gameception.games.tictactoe.conditions.VerticalConditionTicTacToe;
import dev.sllcoding.gameception.games.tictactoe.teams.CircleTeam;
import dev.sllcoding.gameception.games.tictactoe.teams.CrossTeam;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TicTacToeGame implements Game {
    private final GameBoard ticTacToeBoard;
    private final List<GamePlayer> gamePlayers;
    private final List<WinCondition> winConditions;

    private final Team[] turnProgression;
    private int currentTurnIndex = 0;

    public TicTacToeGame(Player playerOne, Player playerTwo, TicTacToeBoard ticTacToeBoard) {
        this.ticTacToeBoard = ticTacToeBoard;

        this.gamePlayers = Arrays.asList(
            new TicTacToePlayer(playerOne, new CircleTeam()),
            new TicTacToePlayer(playerTwo, new CrossTeam())
        );

        this.winConditions = Arrays.asList(
            new DiagonalConditionTicTacToe(ticTacToeBoard),
            new HorizontalConditionTicTacToe(ticTacToeBoard),
            new VerticalConditionTicTacToe(ticTacToeBoard)
        );

        this.turnProgression = new Team[] {
            new CrossTeam(),
            new CircleTeam()
        };
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
        return null;
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
