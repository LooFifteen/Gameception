package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.framework.results.GameResult;
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
        Entity[][] boardEntities = gameBoard.getBoardEntities();

        Entity[] horizontal1 = boardEntities[0];
        Entity[] horizontal2 = boardEntities[1];
        Entity[] horizontal3 = boardEntities[2];

        return new Entity[][] { horizontal1, horizontal2, horizontal3 };
    }
}