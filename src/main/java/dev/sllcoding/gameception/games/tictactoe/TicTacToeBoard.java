package dev.sllcoding.gameception.games.tictactoe;

import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GameObject;
import dev.sllcoding.gameception.games.framework.Team;
import net.minestom.server.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicTacToeBoard implements GameBoard {
    private final Entity[][] entities;
    private final List<Entity> renderedEntities;
    private final Map<Entity, Team> entityMap;

    public TicTacToeBoard(Entity[][] entities) {
        this.entities = entities;
        this.renderedEntities = new ArrayList<>();
        this.entityMap = new HashMap<>();
    }

    @Override
    public Map<Entity, Team> getEntityMap() {
        return entityMap;
    }

    @Override
    public Entity[][] getBoardEntities() {
        return entities;
    }

    @Override
    public void place(Entity entity, GameObject gameObject) {
        if (renderedEntities.contains(entity)) {
            return;
        }

        gameObject.render(entity, 64, 64);
        entityMap.putIfAbsent(entity, gameObject.getTeam());
    }
}
