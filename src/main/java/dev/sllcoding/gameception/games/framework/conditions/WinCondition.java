package dev.sllcoding.gameception.games.framework.conditions;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.results.GameResult;

import java.util.Optional;

public interface WinCondition {
    /**
     * Determines the result of the specified game
     * @param game The specified game
     * @return If present, the game result. If absent, empty.
     */
    Optional<GameResult> getResultOptional(Game game, GamePlayer gamePlayer);
}
