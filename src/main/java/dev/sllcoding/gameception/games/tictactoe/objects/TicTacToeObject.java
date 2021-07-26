package dev.sllcoding.gameception.games.tictactoe.objects;

import dev.sllcoding.gameception.games.framework.GameObject;
import net.minestom.server.entity.Entity;

import java.awt.*;

public abstract class TicTacToeObject implements GameObject {
    @Override
    public void render(Graphics2D graphics2D, int x, int y) {
        drawEmptyRect(graphics2D);
        graphics2D.drawRect(10, 10, 110, 110);
    }

    private void drawEmptyRect(Graphics2D graphics2D) {

    }
}
