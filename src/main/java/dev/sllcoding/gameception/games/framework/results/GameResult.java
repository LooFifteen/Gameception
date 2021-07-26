package dev.sllcoding.gameception.games.framework.results;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.Team;

public interface GameResult {
    Team getWinningTeam();
    void execute(Game game);
}
