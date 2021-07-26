package dev.sllcoding.gameception.games;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static final List<Game> games = new ArrayList<>();

    public static List<Game> getGames() {
        return games;
    }

}
