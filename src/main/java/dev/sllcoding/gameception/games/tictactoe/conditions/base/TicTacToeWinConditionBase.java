package dev.sllcoding.gameception.games.tictactoe.conditions.base;

import dev.sllcoding.gameception.games.framework.v1.*;
import dev.sllcoding.gameception.games.framework.v1.conditions.WinCondition;
import dev.sllcoding.gameception.games.framework.v1.results.GameResult;
import dev.sllcoding.gameception.games.tictactoe.results.TicTacToeGameResult;
import net.minestom.server.entity.Entity;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public abstract class TicTacToeWinConditionBase implements WinCondition {
    private final GameContainer gameContainer;

    public TicTacToeWinConditionBase(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }

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
                TicTacToeGameResult gameResult = new TicTacToeGameResult(gamePlayer.getTeam(), gameContainer);
                return Optional.of(gameResult);
            }
        }

        return Optional.empty();
    }
}
