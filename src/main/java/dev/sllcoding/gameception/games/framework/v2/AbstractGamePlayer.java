package dev.sllcoding.gameception.games.framework.v2;

import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractGamePlayer extends Player {
    public AbstractGamePlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }

    public abstract void initialize();
    public abstract void cleanup();
}
