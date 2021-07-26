package dev.sllcoding.gameception.games.framework.v1.results;

import dev.sllcoding.gameception.games.framework.v1.Game;
import dev.sllcoding.gameception.games.framework.v1.Team;

public interface GameResult {
    Team getWinningTeam();
    void execute(Game game);
}
