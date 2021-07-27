package dev.sllcoding.gameception.games.minesweeper.rendering;

import dev.sllcoding.gameception.games.framework.Renderable;

import java.awt.*;

@Deprecated(forRemoval = true)
public class MinesweeperEmptyRenderable implements Renderable {
    @Override
    public void render(Graphics2D graphics2D, int x, int y) {
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillRect(x, y, 128, 128);

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(x + 10, y - 10, 128 - 10, 128 - 10);
    }
}
