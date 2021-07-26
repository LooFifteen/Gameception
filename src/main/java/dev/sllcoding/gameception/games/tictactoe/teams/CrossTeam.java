package dev.sllcoding.gameception.games.tictactoe.teams;

import dev.sllcoding.gameception.games.framework.v1.GameObject;
import dev.sllcoding.gameception.games.framework.v1.Team;
import dev.sllcoding.gameception.games.tictactoe.objects.CrossObject;

public class CrossTeam implements Team {
    @Override
    public String getName() {
        return "cross";
    }

    @Override
    public GameObject getObject() {
        return new CrossObject();
    }
}
