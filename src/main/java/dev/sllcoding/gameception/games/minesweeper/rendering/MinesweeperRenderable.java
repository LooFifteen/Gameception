package dev.sllcoding.gameception.games.minesweeper.rendering;

import dev.sllcoding.gameception.games.framework.Renderable;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MinesweeperRenderable implements Renderable {
    private BufferedImage bufferedImage = null;

    public MinesweeperRenderable(MineSweeperTileType mineSweeperTileType) {
        String path = mineSweeperTileType.getPath();
        URL resource = getClass().getClassLoader().getResource(path);

        if (resource == null) {
            throw new IllegalArgumentException("The path " + mineSweeperTileType.getPath() + " does not exist!");
        }

        try {
            this.bufferedImage = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics2D graphics2D, int x, int y) {
        if (bufferedImage == null) {
            return;
        }

        graphics2D.drawImage(bufferedImage, x, y, 128, 128, null);
        graphics2D.dispose();
    }
}
