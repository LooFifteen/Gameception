package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.results.GameResult;
import dev.sllcoding.gameception.games.tictactoe.results.TicTacToeTieResult;
import net.minestom.server.entity.Entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class TieConditionTicTacToe implements WinCondition {
    private final GameContainer gameContainer;

    public TieConditionTicTacToe(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public Optional<GameResult> getResultOptional(Game game, GamePlayer gamePlayer) {
        GameBoard board = game.getBoard();

        if (board.allPresent()) {
            return Optional.of(new TicTacToeTieResult(gameContainer));
        }

        return Optional.empty();
    }
}
