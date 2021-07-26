package dev.sllcoding.gameception.games.framework.multiplayer;

import dev.sllcoding.gameception.games.framework.AbstractGamePlayer;
import dev.sllcoding.gameception.games.framework.Game;

import java.util.List;

public abstract class MultiplayerGameBase implements Game {
    abstract List<AbstractGamePlayer> getPlayers();

    @Override
    public void startGame() {
        for (AbstractGamePlayer player : getPlayers()) {
            player.initialize();
        }
    }

    @Override
    public void endGame() {
        for (AbstractGamePlayer player : getPlayers()) {
            player.cleanup();
        }
    }
}
