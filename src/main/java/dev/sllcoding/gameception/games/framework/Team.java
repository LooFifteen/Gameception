package dev.sllcoding.gameception.games.framework;

public interface Team {
    String getName();
    GameObject getObject();

    default boolean equals(Team other) {
        return getName().equals(other.getName());
    }
}
