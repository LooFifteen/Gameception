package dev.sllcoding.gameception.games.minesweeper.container;

import dev.sllcoding.gameception.games.minesweeper.MinesweeperGame;
import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MinesweeperGameContainer {
    private final Map<UUID, MinesweeperGame> playerGameMap = new HashMap<>();

    public MinesweeperGame getOngoingGame(Player player) {
        return playerGameMap.get(player.getUuid());
    }

    public void addOngoingGame(Player player, MinesweeperGame minesweeperGame) {
        playerGameMap.put(player.getUuid(), minesweeperGame);
    }
}