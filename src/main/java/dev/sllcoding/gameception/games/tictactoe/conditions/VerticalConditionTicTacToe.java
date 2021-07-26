package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.tictactoe.conditions.base.TicTacToeWinConditionBase;
import net.minestom.server.entity.Entity;

public class VerticalConditionTicTacToe extends TicTacToeWinConditionBase {
    private final GameBoard gameBoard;

    public VerticalConditionTicTacToe(GameBoard gameBoard, GameContainer gameContainer) {
        super(gameContainer);
        this.gameBoard = gameBoard;
    }

    @Override
    public Entity[][] getEntities() {
        Entity[][] boardEntities = gameBoard.getBoardEntities();

        Entity[] horizontal1 = boardEntities[0];
        Entity[] horizontal2 = boardEntities[1];
        Entity[] horizontal3 = boardEntities[2];

        return new Entity[][] {
            new Entity[] { horizontal1[0], horizontal2[0], horizontal3[0] },
            new Entity[] { horizontal1[1], horizontal2[1], horizontal3[1] },
            new Entity[] { horizontal1[2], horizontal2[2], horizontal3[2] },
        };
    }

    @Override
    public int getPriority() {
        return 2;
    }
}