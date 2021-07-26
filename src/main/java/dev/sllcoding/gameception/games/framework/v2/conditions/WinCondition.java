package dev.sllcoding.gameception.games.framework.v2.conditions;

import dev.sllcoding.gameception.games.framework.v2.Game;
import dev.sllcoding.gameception.games.framework.v2.endings.Ending;

import java.util.Optional;

public interface WinCondition {
    Optional<Ending> evaluate(Game game);
}