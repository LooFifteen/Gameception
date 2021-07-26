package dev.sllcoding.gameception.games.tictactoe.teams;

import dev.sllcoding.gameception.games.framework.v1.GameObject;
import dev.sllcoding.gameception.games.framework.v1.Team;
import dev.sllcoding.gameception.games.tictactoe.objects.CircleObject;

public class CircleTeam implements Team {
    @Override
    public String getName() {
        return "circle";
    }

    @Override
    public GameObject getObject() {
        return new CircleObject();
    }
}
