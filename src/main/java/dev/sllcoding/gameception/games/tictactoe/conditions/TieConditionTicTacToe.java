package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.results.GameResult;

import java.util.Optional;

public class TieConditionTicTacToe implements WinCondition {
    @Override
    public Optional<GameResult> getResultOptional(Game game, GamePlayer gamePlayer) {
        GameBoard board = game.getBoard();

        return Optional.empty();
    }
}
