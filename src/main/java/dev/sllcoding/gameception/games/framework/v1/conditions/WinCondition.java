package dev.sllcoding.gameception.games.framework.v1.conditions;

import dev.sllcoding.gameception.games.framework.v1.Game;
import dev.sllcoding.gameception.games.framework.v1.GamePlayer;
import dev.sllcoding.gameception.games.framework.v1.results.GameResult;

import java.util.Optional;

public interface WinCondition {
    int getPriority();
    Optional<GameResult> getResultOptional(Game game, GamePlayer gamePlayer);
}
