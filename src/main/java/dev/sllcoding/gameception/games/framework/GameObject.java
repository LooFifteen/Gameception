package dev.sllcoding.gameception.games.framework;

import net.minestom.server.entity.Entity;

import java.awt.*;

public interface GameObject {
    Team getTeam();
    void render(Graphics2D graphics2D, int x, int y);
}
