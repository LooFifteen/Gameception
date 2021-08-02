package dev.sllcoding.gameception.games.minesweeper.endings;

import dev.sllcoding.gameception.games.framework.endings.Ending;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;

import java.util.Optional;

public class MineEndingCondition extends MinesweeperTileTypeEndingCondition {
    public MineEndingCondition(MineSweeperTileType mineSweeperTileType) {
        super(mineSweeperTileType);
    }

    @Override
    public MineSweeperTileType getTileType() {
        return MineSweeperTileType.Mine;
    }

    @Override
    public Ending getEnding() {
        return new MineEnding();
    }
}
