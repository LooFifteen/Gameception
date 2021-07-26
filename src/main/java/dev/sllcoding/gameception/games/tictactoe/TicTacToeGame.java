package dev.sllcoding.gameception.games.tictactoe;

import dev.sllcoding.gameception.games.framework.*;
import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.results.GameResult;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.timer.SchedulerManager;
import net.minestom.server.timer.TaskBuilder;
import net.minestom.server.utils.time.TimeUnit;

import java.util.List;
import java.util.Optional;

public class TicTacToeGame implements Game {
    private final GameBoard ticTacToeBoard;
    private final List<GamePlayer> gamePlayers;
    private final List<WinCondition> winConditions;
    private final Team[] turnProgression;

    private int currentTurnIndex = 0;

    public TicTacToeGame(TicTacToeBoard ticTacToeBoard,
                         List<GamePlayer> gamePlayers,
                         List<WinCondition> winConditions,
                         Team[] turnProgression) {
        this.ticTacToeBoard = ticTacToeBoard;
        this.gamePlayers = gamePlayers;
        this.winConditions = winConditions;
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
    public GameBoard getBoard() {
        return ticTacToeBoard;
    }

    @Override
    public void start() {
        ticTacToeBoard.initialize();
    }

    @Override
    public void end() {
        SchedulerManager schedulerManager = MinecraftServer.getSchedulerManager();

        TaskBuilder taskBuilder = schedulerManager.buildTask(() -> {
            GameBoard board = getBoard();
            board.remove();
        });

        taskBuilder.delay(5, TimeUnit.SECOND).schedule();
    }

    @Override
    public void update(Entity entity) {
        Team currentTurn = getCurrentTurn();

        Optional<GamePlayer> gamePlayerOptional = getPlayers().stream()
                .filter(gamePlayer -> gamePlayer.getTeam().equals(currentTurn))
                .findFirst();

        if (gamePlayerOptional.isEmpty()) {
            return;
        }

        GamePlayer gamePlayer = gamePlayerOptional.get();

        GameBoard board = getBoard();
        board.place(entity, gamePlayer);

        for (WinCondition winCondition : winConditions) {
            Optional<GameResult> resultOptional = winCondition.getResultOptional(this, gamePlayer);

            if (resultOptional.isEmpty()) {
                continue;
            }

            GameResult gameResult = resultOptional.get();
            gameResult.execute(this);
            return;
        }

        currentTurnIndex++;

        if (currentTurnIndex >= turnProgression.length) {
            currentTurnIndex = 0;
        }
    }

    @Override
    public Team getCurrentTurn() {
        return turnProgression[currentTurnIndex];
    }
}
