package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.v1.Game;
import dev.sllcoding.gameception.games.framework.v1.GameBoard;
import dev.sllcoding.gameception.games.framework.v1.GameContainer;
import dev.sllcoding.gameception.games.framework.v1.GamePlayer;
import dev.sllcoding.gameception.games.framework.v1.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.v1.results.GameResult;
import dev.sllcoding.gameception.games.tictactoe.results.TicTacToeTieResult;

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
