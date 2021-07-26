package dev.sllcoding.gameception.games.tictactoe;

import net.minestom.server.entity.Entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TicTacToeBoard {
    private final Entity[][] entities;
    private final HashMap<Entity, TicTacToeTeam> entitiesMap;

    public TicTacToeBoard(Entity[][] entities) {
        this.entities = entities;
        this.entitiesMap = new HashMap<>();
    }

    public Entity[][] getEntities() {
        return entities;
    }

    public boolean isEntityMarked(Entity entity) {
        return entitiesMap.containsKey(entity);
    }

    public void addMarkedEntity(Entity entity, TicTacToeTeam ticTacToeTeam) {
        if (isEntityApartOfBoard(entity)) {
            entitiesMap.putIfAbsent(entity, ticTacToeTeam);
        }
    }

    public boolean isEntityApartOfBoard(Entity entity) {
        for (int i = 0; i < entities.length; i++) {
            Entity[] rowEntities = entities[i];

            for (int j = 0; j < rowEntities.length; j++) {
                if (rowEntities[j].equals(entity)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Entity getBoardPart(int row, int column) {
        return entities[row][column];
    }

    public boolean isTie() {
        return (!didTeamWin(TicTacToeTeam.O) && !didTeamWin(TicTacToeTeam.X)) &&
                entitiesMap.size() == 9;
    }

    public boolean didTeamWin(TicTacToeTeam currentTeam) {
        // ...

        Entity[] horizontal1 = entities[0];
        Entity[] horizontal2 = entities[1];
        Entity[] horizontal3 = entities[2];

        boolean horizontal = won(horizontal1, currentTeam) || won(horizontal2, currentTeam) || won(horizontal3, currentTeam);

        if (horizontal) {
            return true;
        }

        boolean vertical = won(new Entity[] { horizontal1[0], horizontal2[0], horizontal3[0] }, currentTeam) ||
                won(new Entity[] { horizontal1[1], horizontal2[1], horizontal3[1] }, currentTeam) ||
                won(new Entity[] { horizontal1[2], horizontal2[2], horizontal3[2] }, currentTeam);

        if (vertical) {
            return true;
        }

        return won(new Entity[] { horizontal1[0], horizontal2[1], horizontal3[2] }, currentTeam) ||
                won(new Entity[] { horizontal1[2], horizontal2[1], horizontal3[0] }, currentTeam);
    }

    private boolean won(Entity[] entities, TicTacToeTeam ticTacToeTeam) {
        return Arrays.stream(entities).allMatch(entity -> {
            TicTacToeTeam teamForEntity = entitiesMap.get(entity);
            return teamForEntity == ticTacToeTeam;
        });
    }

    public void delete() {
        for (Entity[] entity : entities) {
            for (Entity entity1 : entity) {
                entity1.remove();
            }
        }
    }
}
