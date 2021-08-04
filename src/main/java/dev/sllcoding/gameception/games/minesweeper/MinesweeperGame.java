package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.framework.conditions.EndingCondition;
import dev.sllcoding.gameception.games.framework.endings.Ending;
import dev.sllcoding.gameception.games.framework.singleplayer.SingleplayerGameBase;
import dev.sllcoding.gameception.games.minesweeper.endings.MineEndingCondition;
import dev.sllcoding.gameception.games.minesweeper.endings.PlayerWonEndingCondition;
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

        // These ending conditions need to be changed as well lol.

        // optimize this obviously
        // after the refactor
        // probably use small numbers for states (like shorts)

        if (abstractTileEntity instanceof MinesweeperTileEntity minesweeperTileEntity) {
            checkCondition(new PlayerWonEndingCondition());
            checkCondition(new MineEndingCondition(minesweeperTileEntity));
        }
    }

    private void checkCondition(EndingCondition endingCondition) {
        Optional<Ending> endingOptional = endingCondition.evaluate(this);

        if (endingOptional.isEmpty()) {
            return;
        }

        Ending ending = endingOptional.get();
        endGame(ending);
    }

    private boolean hasWon() {
        return false;
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