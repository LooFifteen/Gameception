package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

// TODO: delete this class.
public class MinesweeperTileEntityFactory {
    private final int ROWS = 16,
                      COLUMNS = 16,
                      MINES = 40;

    public List<AbstractTileEntity> createTileEntities(Player spawningPlayer) {
        MineSweeperTileType[][] mineSweeperTileTypes = new MineSweeperTileType[ROWS][COLUMNS];
        fillMines(mineSweeperTileTypes);
        fillVoid(mineSweeperTileTypes);

        return createBoardEntities(spawningPlayer, mineSweeperTileTypes);
    }

    private List<AbstractTileEntity> createBoardEntities(Player spawningPlayer, MineSweeperTileType[][] mineSweeperTileTypes) {
        int currentRow = 0, currentColumn = 0;
        List<AbstractTileEntity> minesweeperTileEntities = new ArrayList<>();

        for (MineSweeperTileType[] mineSweeperTileType : mineSweeperTileTypes) {

            for (MineSweeperTileType sweeperTileType : mineSweeperTileType) {
                minesweeperTileEntities.add(createTileEntity(spawningPlayer, currentRow, currentColumn,
                        Objects.requireNonNullElse(sweeperTileType, MineSweeperTileType.Empty)));

                currentColumn++;
            }

            currentRow++;
            currentColumn = 0;
        }

        return minesweeperTileEntities;
    }

    private void fillMines(MineSweeperTileType[][] mineSweeperTileTypes) {
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
    }

    private void fillVoid(MineSweeperTileType[][] mineSweeperTileTypes) {
        for (int x = 0; x < mineSweeperTileTypes.length; x++)
            for (int y = 0; y < mineSweeperTileTypes[x].length; y++) {
                if (mineSweeperTileTypes[x][y] == MineSweeperTileType.Mine) continue;
                int mines = 0;
                for (int r = x - 1; r <= x + 1; r++) if (r >= 0 && r < COLUMNS) for (int c = y - 1; c <= y + 1; c++) if (c >= 0 && c < ROWS) if (mineSweeperTileTypes[r][c] == MineSweeperTileType.Mine) mines++;
                mineSweeperTileTypes[x][y] = switch (mines) {
                    case 1 -> MineSweeperTileType.One;
                    case 2 -> MineSweeperTileType.Two;
                    case 3 -> MineSweeperTileType.Three;
                    case 4 -> MineSweeperTileType.Four;
                    case 5 -> MineSweeperTileType.Five;
                    case 6 -> MineSweeperTileType.Six;
                    case 7 -> MineSweeperTileType.Seven;
                    case 8 -> MineSweeperTileType.Eight;
                    default -> MineSweeperTileType.Empty;
                };
            }
    }

    private AbstractTileEntity createTileEntity(Player spawningPlayer, int currentRow, int currentColumn, MineSweeperTileType mineSweeperTileType) {
        MinesweeperTileEntity tileEntity = new MinesweeperTileEntity(spawningPlayer.getInstance(),
                spawningPlayer.getPosition().add(currentColumn, 0, currentRow).withYaw(180).withPitch(90),
                mineSweeperTileType);

        tileEntity.addBlankMap(ThreadLocalRandom.current().nextInt(100000));
        tileEntity.initialize();

        return tileEntity;
    }
}
