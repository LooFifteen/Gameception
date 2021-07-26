package dev.sllcoding.gameception.games.tictactoe.objects;

import dev.sllcoding.gameception.games.framework.GameObject;
import dev.sllcoding.gameception.games.framework.Team;

import java.awt.*;

public class CrossObject implements GameObject {
    @Override
    public Team getTeam() {
        return null;
    }

    @Override
    public void render(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawRect(10, 10, 110, 110);

        int length = 30;

        graphics2D.drawLine(x - length, y - length, x + length, y + length);
        graphics2D.drawLine(x - length, y + length, x + length, y - length);
    }
}
