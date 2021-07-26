package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.tictactoe.conditions.base.TicTacToeWinConditionBase;
import net.minestom.server.entity.Entity;

public class HorizontalConditionTicTacToe extends TicTacToeWinConditionBase {
    private final GameBoard gameBoard;

    public HorizontalConditionTicTacToe(GameBoard gameBoard, GameContainer gameContainer) {
        super(gameContainer);
        this.gameBoard = gameBoard;
    }

    @Override
    public Entity[][] getEntities() {
        return gameBoard.getBoardEntities();
    }

    @Override
    public int getPriority() {
        return 1;
    }
}