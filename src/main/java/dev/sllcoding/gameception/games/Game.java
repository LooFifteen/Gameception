package dev.sllcoding.gameception.games;

import net.minestom.server.entity.Player;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Game {

    private final int id;
    protected GameState state = GameState.WAITING;

    public Game() {
        this(ThreadLocalRandom.current().nextInt(10000));
    }

    public Game(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }

    public abstract boolean addPlayer(GamePlayer player);
    public abstract boolean removePlayer(GamePlayer player);

    abstract void startGame();
    abstract void endGame();

    abstract List<Player> getPlayers();
}
