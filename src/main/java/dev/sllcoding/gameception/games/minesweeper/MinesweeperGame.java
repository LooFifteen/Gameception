package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.framework.conditions.EndingCondition;
import dev.sllcoding.gameception.games.framework.endings.Ending;
import dev.sllcoding.gameception.games.framework.singleplayer.SingleplayerGameBase;
import dev.sllcoding.gameception.games.minesweeper.endings.MineEndingCondition;
import net.minestom.server.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class MinesweeperGame extends SingleplayerGameBase {
    private final int gameId;
    private final AbstractGamePlayer abstractGamePlayer;
    private final List<AbstractTileEntity> abstractTileEntities;

    public MinesweeperGame(Player player, List<AbstractTileEntity> abstractTileEntities) {
        this.abstractTileEntities = abstractTileEntities;
        this.gameId = ThreadLocalRandom.current().nextInt(100000);
        this.abstractGamePlayer = new MinesweeperPlayer(player);
    }

    @Override
    public int getId() {
        return gameId;
    }

    @Override
    public void onTileClick(Player player, AbstractTileEntity abstractTileEntity) {
        abstractTileEntity.update();

        // TODO: Knowing about the concrete implementation is a really bad idea.
        // Heavily refactor the framework moving forward...

        if (abstractTileEntity instanceof MinesweeperTileEntity minesweeperTileEntity) {
            MineEndingCondition mineEndingCondition = new MineEndingCondition(minesweeperTileEntity.getMineSweeperTileType());
            Optional<Ending> endingOptional = mineEndingCondition.evaluate(this);

            if (endingOptional.isEmpty()) {
                return;
            }

            Ending ending = endingOptional.get();
            endGame(ending);
        }
    }

    @Override
    public AbstractGamePlayer getGamePlayer() {
        return abstractGamePlayer;
    }

    @Override
    public List<AbstractTileEntity> getEntities() {
        return abstractTileEntities;
    }
}