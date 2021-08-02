package dev.sllcoding.gameception.games.minesweeper.container;

import dev.sllcoding.gameception.games.minesweeper.MinesweeperGame;
import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MinesweeperGameContainer {
    private final Map<UUID, MinesweeperGame> playerGameMap = new HashMap<>();

    public MinesweeperGame getOngoingGame(Player player) {
        UUID uuid = player.getUuid();
        MinesweeperGame minesweeperGame = playerGameMap.get(uuid);

        if (minesweeperGame != null && !minesweeperGame.isGameOngoing()) {
            playerGameMap.remove(uuid);
            return null;
        }

        return minesweeperGame;
    }

    public void addOngoingGame(Player player, MinesweeperGame minesweeperGame) {
        MinesweeperGame ongoingGame = getOngoingGame(player);

        if (ongoingGame != null) {
            return;
        }

        playerGameMap.put(player.getUuid(), minesweeperGame);
    }
}