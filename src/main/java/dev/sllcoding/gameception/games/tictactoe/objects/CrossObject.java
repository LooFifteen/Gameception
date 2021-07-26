package dev.sllcoding.gameception.games.tictactoe.objects;

import dev.sllcoding.gameception.games.framework.v1.GameObject;

import java.awt.*;

public class CrossObject implements GameObject {
    @Override
    public void render(Graphics2D graphics2D, int x, int y) {
        int length = 30;

        graphics2D.drawLine(x - length, y - length, x + length, y + length);
        graphics2D.drawLine(x - length, y + length, x + length, y - length);
    }
}
