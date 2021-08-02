package dev.sllcoding.gameception.games.framework.singleplayer;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.conditions.EndingCondition;
import dev.sllcoding.gameception.games.framework.endings.Ending;
import net.minestom.server.entity.Player;

import java.util.List;
import java.util.Optional;

public abstract class SingleplayerGameBase implements Game {
    private boolean gameOngoing = false;
    public abstract AbstractGamePlayer getGamePlayer();

    @Override
    public void startGame() {
        gameOngoing = true;
        getGamePlayer().initialize();
    }

    @Override
    public void endGame(Ending ending) {
        gameOngoing = false;

        AbstractGamePlayer gamePlayer = getGamePlayer();

        Player player = gamePlayer.getPlayer();
        player.showTitle(ending.getTitle());

        ending.execute();
        gamePlayer.cleanup();

        for (AbstractTileEntity entity : getEntities()) {
            entity.remove();
        }
    }

    public abstract List<AbstractTileEntity> getEntities();

    public boolean isGameOngoing() {
        return gameOngoing;
    }
}
