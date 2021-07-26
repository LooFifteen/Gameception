package dev.sllcoding.gameception.games.framework.singleplayer;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.Game;

public abstract class SingleplayerGameBase implements Game {
    private boolean gameOngoing = false;
    public abstract AbstractGamePlayer getPlayer();

    @Override
    public void startGame() {
        gameOngoing = true;
        getPlayer().initialize();
    }

    @Override
    public void endGame() {
        gameOngoing = false;
        getPlayer().cleanup();
    }

    public boolean isGameOngoing() {
        return gameOngoing;
    }
}
