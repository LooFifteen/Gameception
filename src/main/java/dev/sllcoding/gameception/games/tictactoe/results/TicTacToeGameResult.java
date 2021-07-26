package dev.sllcoding.gameception.games.tictactoe.results;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.framework.Team;
import dev.sllcoding.gameception.games.framework.results.GameResult;

public class TicTacToeGameResult implements GameResult {
    private final Team team;
    private final GameContainer gameContainer;

    public TicTacToeGameResult(Team team, GameContainer gameContainer) {
        this.team = team;
        this.gameContainer = gameContainer;
    }

    @Override
    public Team getWinningTeam() {
        return team;
    }

    @Override
    public void execute(Game game) {
        game.end();
        gameContainer.removeGame(game);
    }
}
