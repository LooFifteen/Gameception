package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MinesweeperTileEntityFactory {
    private final List<MineSweeperTileType> mineSweeperTileTypes;

    public MinesweeperTileEntityFactory() {
        this.mineSweeperTileTypes = Arrays.stream(MineSweeperTileType.values())
                .filter(mineSweeperTileType -> mineSweeperTileType != MineSweeperTileType.Unknown)
                .collect(Collectors.toList());
    }

    public List<MinesweeperTileEntity> createTileEntities(Player spawningPlayer) {
        int rows = 10;
        int columns = 10;

        List<MinesweeperTileEntity> tileEntities = new ArrayList<>();

        for (int currentRow = 0, currentColumn = 0; currentRow <= rows; currentColumn++) {
            MinesweeperTileEntity tileEntity = createTileEntity(spawningPlayer, currentRow, currentColumn);
            tileEntities.add(tileEntity);

            if (currentColumn == columns) {
                currentColumn = -1;
                currentRow++;
            }
        }

        return tileEntities;
    }

    private MinesweeperTileEntity createTileEntity(Player spawningPlayer, int currentRow, int currentColumn) {
        int index = ThreadLocalRandom.current().nextInt(mineSweeperTileTypes.size());
        MineSweeperTileType mineSweeperTileType = mineSweeperTileTypes.get(index);

        MinesweeperTileEntity tileEntity = new MinesweeperTileEntity(spawningPlayer.getInstance(),
                spawningPlayer.getPosition().add(currentColumn, 0, currentRow).withYaw(180).withPitch(90),
                mineSweeperTileType);

        tileEntity.addBlankMap(ThreadLocalRandom.current().nextInt(100000));
        tileEntity.initialize();

        return tileEntity;
    }
}
