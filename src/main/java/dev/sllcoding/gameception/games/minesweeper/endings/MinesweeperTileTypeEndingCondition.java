package dev.sllcoding.gameception.games.minesweeper.endings;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.conditions.EndingCondition;
import dev.sllcoding.gameception.games.framework.endings.Ending;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntity;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;

import java.util.Optional;

public abstract class MinesweeperTileTypeEndingCondition implements EndingCondition {
    private MinesweeperTileEntity minesweeperTileEntity;

    public MinesweeperTileTypeEndingCondition(MinesweeperTileEntity minesweeperTileEntity) {
        this.minesweeperTileEntity = minesweeperTileEntity;
    }

    public abstract MineSweeperTileType getTileType();

    public abstract Ending getEnding(Game game);

    @Override
    public Optional<Ending> evaluate(Game game) {
        if (minesweeperTileEntity.getMineSweeperTileType() == getTileType() && minesweeperTileEntity.getRenderedTileType() != getTileType()) {
            return Optional.of(getEnding(game));
        }

        return Optional.empty();
    }
}
