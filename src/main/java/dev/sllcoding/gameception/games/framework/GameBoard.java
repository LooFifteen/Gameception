package dev.sllcoding.gameception.games.framework;

import net.minestom.server.entity.Entity;

import java.util.Map;

public interface GameBoard {
    Map<Entity, Team> getEntityMap();
    Entity[][] getBoardEntities();

    void initialize();
    void remove();

    void place(Entity entity, GamePlayer gamePlayer);
}