package dev.sllcoding.gameception.games.framework.conditions;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.endings.Ending;

import java.util.Optional;

public interface WinCondition {
    Optional<Ending> evaluate(Game game);
}