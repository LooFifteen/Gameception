package dev.sllcoding.gameception.games.framework.conditions;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.results.GameResult;

import java.util.Optional;

public interface WinCondition {
    int getPriority();
    Optional<GameResult> getResultOptional(Game game, GamePlayer gamePlayer);
}
