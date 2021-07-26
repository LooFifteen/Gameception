package dev.sllcoding.gameception.games.framework.v1;

public interface Team {
    String getName();
    GameObject getObject();

    default boolean equals(Team other) {
        return getName().equals(other.getName());
    }
}
