package dev.sllcoding.gameception.games.framework.v2.singleplayer;

import dev.sllcoding.gameception.games.framework.v2.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.v2.Game;

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
