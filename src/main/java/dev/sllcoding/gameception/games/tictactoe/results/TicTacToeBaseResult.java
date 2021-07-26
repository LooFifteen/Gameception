package dev.sllcoding.gameception.games.tictactoe.results;

import dev.sllcoding.gameception.games.framework.v1.Game;
import dev.sllcoding.gameception.games.framework.v1.GameContainer;
import dev.sllcoding.gameception.games.framework.v1.results.GameResult;

public abstract class TicTacToeBaseResult implements GameResult {
    private final GameContainer gameContainer;

    public TicTacToeBaseResult(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }

    @Override
    public void execute(Game game) {
        game.end();
        gameContainer.removeGame(game);
    }
}
