package dev.sllcoding.gameception.games.framework;

import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.results.GameResult;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Game {
    List<GamePlayer> getPlayers();
    List<WinCondition> getWinConditions();

    GameBoard getBoard();

    void start();
    void end();
    void update(Entity entity);

    Optional<GamePlayer> getPlayer(UUID identifier);

    Team getCurrentTurn();
}
