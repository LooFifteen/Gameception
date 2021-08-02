package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MinesweeperPlayer extends AbstractGamePlayer {
    public MinesweeperPlayer(Player player) {
        super(player);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void cleanup() {

    }
}