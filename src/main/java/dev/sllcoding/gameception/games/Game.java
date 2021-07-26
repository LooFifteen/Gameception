package dev.sllcoding.gameception.games;

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

    public abstract void addPlayer(GamePlayer player);
    public abstract void removePlayer(GamePlayer player);

}
