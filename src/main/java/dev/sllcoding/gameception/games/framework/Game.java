package dev.sllcoding.gameception.games.framework;

import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;

import java.util.List;

public interface Game {
    List<GamePlayer> getPlayers();
    List<WinCondition> getWinConditions();
    List<GameObject> getObjects();

    GameBoard getBoard();

    void start();
    void end();
    void update(Entity entity);

    Team getCurrentTurn();
}
