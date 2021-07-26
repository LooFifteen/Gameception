package dev.sllcoding.gameception.games.tictactoe.objects;

import dev.sllcoding.gameception.games.framework.GameObject;

import java.awt.*;

public class CircleObject implements GameObject {
    @Override
    public void render(Graphics2D graphics2D, int x, int y) {
        int size = 60;
        graphics2D.drawOval(x - size / 2, y - size / 2, size, size);
    }
}
