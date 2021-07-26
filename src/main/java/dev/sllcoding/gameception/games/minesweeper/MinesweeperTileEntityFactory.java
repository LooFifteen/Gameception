package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MinesweeperTileEntityFactory {
    public List<MinesweeperTileEntity> createTileEntities(Player spawningPlayer) {
        int rows = 10;
        int columns = 10;

        List<MinesweeperTileEntity> tileEntities = new ArrayList<>();
        MineSweeperTileType[] values = MineSweeperTileType.values();

        int currentRow = 0;
        int currentColumn = 0;

        while (currentRow < rows) {
            int index = ThreadLocalRandom.current().nextInt(values.length);
            MineSweeperTileType mineSweeperTileType = values[index];

            MinesweeperTileEntity tileEntity = new MinesweeperTileEntity(spawningPlayer.getInstance(),
                    spawningPlayer.getPosition().add(currentColumn, 0, currentRow).withYaw(180).withPitch(90),
                    mineSweeperTileType);

            tileEntity.addBlankMap(ThreadLocalRandom.current().nextInt(100000));
            tileEntity.initialize();

            currentColumn++;

            if (currentColumn == columns) {
                currentColumn = 0;
                currentRow++;
            }
        }

        return tileEntities;
    }
}
