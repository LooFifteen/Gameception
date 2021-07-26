package dev.sllcoding.gameception.games.framework.singleplayer;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.Game;

public abstract class SingleplayerGameBase implements Game {
    abstract AbstractGamePlayer getPlayer();

    @Override
    public void startGame() {
        getPlayer().initialize();
    }

    @Override
    public void endGame() {
        getPlayer().cleanup();
    }
}
