package dev.sllcoding.gameception.games.tictactoe;

import net.minestom.server.entity.Entity;

import java.util.List;

public class TicTacToeBoard {
    private List<Entity> entityList;

    public TicTacToeBoard(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public boolean isEntityApartOfBoard(Entity entity) {
        return entityList.contains(entity);
    }
}
