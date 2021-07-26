package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.framework.singleplayer.SingleplayerGameBase;
import net.minestom.server.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class MinesweeperGame extends SingleplayerGameBase {
    private final int gameId;
    private final AbstractGamePlayer abstractGamePlayer;

    public MinesweeperGame(Player player) {
        this.gameId = ThreadLocalRandom.current().nextInt(100000);
        this.abstractGamePlayer = (MinesweeperPlayer) player;
    }

    @Override
    public int getId() {
        return gameId;
    }

    @Override
    public void onTileClick(Player player, AbstractTileEntity abstractTileEntity) {
        abstractTileEntity.update();
    }

    @Override
    public AbstractGamePlayer getPlayer() {
        return abstractGamePlayer;
    }
}