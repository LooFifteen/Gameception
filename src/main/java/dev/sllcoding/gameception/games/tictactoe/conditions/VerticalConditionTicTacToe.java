package dev.sllcoding.gameception.games.tictactoe.conditions;

import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.tictactoe.conditions.base.TicTacToeWinConditionBase;
import net.minestom.server.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class VerticalConditionTicTacToe extends TicTacToeWinConditionBase {
    private final GameBoard gameBoard;

    public VerticalConditionTicTacToe(GameBoard gameBoard, GameContainer gameContainer) {
        super(gameContainer);
        this.gameBoard = gameBoard;
    }

    @Override
    public Entity[][] getEntities() {
        Entity[][] boardEntities = gameBoard.getBoardEntities();
        List<Entity[]> entities = new ArrayList<>();

        int column = 0;

        for (int i = 0; i < boardEntities.length; i++) {
            List<Entity> entityList = new ArrayList<>();

            for (Entity[] boardEntity : boardEntities) {
                entityList.add(boardEntity[column]);
            }

            entities.add(entityList.toArray(new Entity[0]));
        }

        return entities.toArray(Entity[][]::new);
    }

    @Override
    public int getPriority() {
        return 2;
    }
}