package dev.sllcoding.gameception.games;

import net.minestom.server.entity.Player;

import java.util.List;

public abstract class Game {

    // TODO: Do something with this class.

    abstract void startGame();
    abstract void endGame();

    abstract List<Player> getPlayers();

}
