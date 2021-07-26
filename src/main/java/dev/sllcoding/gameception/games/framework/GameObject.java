package dev.sllcoding.gameception.games.framework;

import net.minestom.server.entity.Entity;

public interface GameObject {
    Team getTeam();
    void render(Entity entity, int x, int y);
}
