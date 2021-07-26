package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.tictactoe.conditions.base.TicTacToeWinConditionBase;
import net.minestom.server.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class DiagonalConditionTicTacToe extends TicTacToeWinConditionBase {
    private final GameBoard gameBoard;

    public DiagonalConditionTicTacToe(GameBoard gameBoard, GameContainer gameContainer) {
        super(gameContainer);
        this.gameBoard = gameBoard;
    }

    @Override
    public Entity[][] getEntities() {
        Entity[][] boardEntities = gameBoard.getBoardEntities();

        List<Entity> entityList = new ArrayList<>();
        List<Entity> entityList1 = new ArrayList<>();

        for (int i = 0; i < boardEntities.length; i++) {
            entityList.add(boardEntities[i][i]);
        }

        for (int currentColumn = boardEntities.length - 1, currentRow = 0; currentColumn >= 0 && currentRow < boardEntities.length; currentColumn--, currentRow++) {
            entityList1.add(boardEntities[currentRow][currentColumn]);
        }

        return new Entity[][] { entityList.toArray(new Entity[0]), entityList1.toArray(new Entity[0]) };
    }

    @Override
    public int getPriority() {
        return 0;
    }
}