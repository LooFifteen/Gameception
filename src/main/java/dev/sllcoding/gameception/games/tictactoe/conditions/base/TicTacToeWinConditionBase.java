package dev.sllcoding.gameception.games.tictactoe.conditions.base;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.Team;
import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.results.GameResult;
import dev.sllcoding.gameception.games.tictactoe.results.TicTacToeGameResult;
import net.minestom.server.entity.Entity;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public abstract class TicTacToeWinConditionBase implements WinCondition {
    public abstract Entity[][] getEntities();

    @Override
    public Optional<GameResult> getResultOptional(Game game, GamePlayer gamePlayer) {
        Entity[][] entities = getEntities();

        GameBoard board = game.getBoard();
        Map<Entity, Team> entityMap = board.getEntityMap();

        for (Entity[] gameEntities : entities) {
            boolean allEntitiesMatch = Arrays.stream(gameEntities).allMatch(gameEntity -> {
                Team team = entityMap.get(gameEntity);
                return team != null && team.equals(gamePlayer.getTeam());
            });

            if (allEntitiesMatch) {
                return Optional.of(new TicTacToeGameResult(gamePlayer.getTeam()));
            }
        }

        return Optional.empty();
    }
}
