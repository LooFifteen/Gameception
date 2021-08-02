package dev.sllcoding.gameception.games.framework;

import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// TODO: refactor this or just delete it?
public abstract class AbstractGamePlayer {
    protected Player player;

    public AbstractGamePlayer(Player player) {
        this.player = player;
    }

    public abstract void initialize();
    public abstract void cleanup();
}
