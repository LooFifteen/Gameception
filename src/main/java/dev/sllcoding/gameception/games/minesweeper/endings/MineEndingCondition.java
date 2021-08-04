package dev.sllcoding.gameception.games.minesweeper.endings;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.endings.Ending;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntity;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;

import java.util.Optional;

public class MineEndingCondition extends MinesweeperTileTypeEndingCondition {

    public MineEndingCondition(MinesweeperTileEntity minesweeperTileEntity) {
        super(minesweeperTileEntity);
    }

    @Override
    public MineSweeperTileType getTileType() {
        return MineSweeperTileType.Mine;
    }

    @Override
    public Ending getEnding(Game game) {
        return new MineEnding(game);
    }
}
