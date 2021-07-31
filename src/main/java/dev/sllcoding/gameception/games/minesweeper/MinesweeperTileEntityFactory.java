package dev.sllcoding.gameception.games.minesweeper;

import com.extollit.linalg.mutable.Vec2d;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;
import net.minestom.server.entity.Player;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MinesweeperTileEntityFactory {
    private final int ROWS = 16,
                      COLUMNS = 16,
                      MINES = 40;

    private final List<MineSweeperTileType> mineSweeperTileTypes;

    public MinesweeperTileEntityFactory() {
        this.mineSweeperTileTypes = Arrays.stream(MineSweeperTileType.values())
                .filter(mineSweeperTileType -> mineSweeperTileType != MineSweeperTileType.Unknown)
                .collect(Collectors.toList());
    }

    public List<MinesweeperTileEntity> createTileEntities(Player spawningPlayer) {
        MineSweeperTileType[][] mineSweeperTileTypes = new MineSweeperTileType[ROWS][COLUMNS];
        int currentAmountOfMines = 0;

        while (currentAmountOfMines < MINES) {
            ThreadLocalRandom current = ThreadLocalRandom.current();
            int index = current.nextInt(COLUMNS * ROWS);

            int rowIndex = index / ROWS;
            int columnIndex = index % COLUMNS;

            MineSweeperTileType mineSweeperTileType = mineSweeperTileTypes[rowIndex][columnIndex];

            if (mineSweeperTileType == MineSweeperTileType.Mine) {
                continue;
            }

            mineSweeperTileTypes[rowIndex][columnIndex] = MineSweeperTileType.Mine;
            currentAmountOfMines++;
        }

        int currentRow = 0, currentColumn = 0;
        List<MinesweeperTileEntity> minesweeperTileEntities = new ArrayList<>();

        for (MineSweeperTileType[] mineSweeperTileType : mineSweeperTileTypes) {

            for (MineSweeperTileType sweeperTileType : mineSweeperTileType) {

                if (sweeperTileType != null) {
                    minesweeperTileEntities.add(createTileEntity(spawningPlayer, currentRow, currentColumn, sweeperTileType));
                } else {
                    minesweeperTileEntities.add(createTileEntity(spawningPlayer, currentRow, currentColumn, MineSweeperTileType.Empty));
                }

                currentColumn++;
            }

            currentRow++;
            currentColumn = 0;
        }

        return minesweeperTileEntities;
    }

    private MinesweeperTileEntity createTileEntity(Player spawningPlayer, int currentRow, int currentColumn, MineSweeperTileType mineSweeperTileType) {
        MinesweeperTileEntity tileEntity = new MinesweeperTileEntity(spawningPlayer.getInstance(),
                spawningPlayer.getPosition().add(currentColumn, 0, currentRow).withYaw(180).withPitch(90),
                mineSweeperTileType);

        tileEntity.addBlankMap(ThreadLocalRandom.current().nextInt(100000));
        tileEntity.initialize();

        return tileEntity;
    }
}
