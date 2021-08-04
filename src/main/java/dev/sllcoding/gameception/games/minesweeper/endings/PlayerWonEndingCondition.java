package dev.sllcoding.gameception.games.minesweeper.endings;

import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.conditions.EndingCondition;
import dev.sllcoding.gameception.games.framework.endings.Ending;
import dev.sllcoding.gameception.games.framework.singleplayer.SingleplayerGameBase;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntity;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerWonEndingCondition implements EndingCondition {
    @Override
    public Optional<Ending> evaluate(Game game) {
        // TODO: Refactor framework, for the X (i forgot) time.

        if (game instanceof SingleplayerGameBase singleplayerGameBase) {
            List<MinesweeperTileEntity> entities = singleplayerGameBase.getEntities().stream()
                    .map(x -> (MinesweeperTileEntity) x)
                    .collect(Collectors.toList());

            boolean allEntitiesInCorrectState = entities.stream().allMatch(x -> x.getRenderedTileType() == x.getMineSweeperTileType() ||
                    (x.getRenderedTileType() == MineSweeperTileType.Flagged && x.getMineSweeperTileType() == MineSweeperTileType.Mine) &&
                    x.getMineSweeperTileType() != MineSweeperTileType.Mine);

            if (allEntitiesInCorrectState) {
                return Optional.of(new PlayerWonEnding());
            }
        }

        return Optional.empty();
    }
}
