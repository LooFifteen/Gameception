package dev.sllcoding.gameception.games.framework.v1;

import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameContainer {
    private List<Game> onGoingGames = new ArrayList<>();

    public boolean isPlayerInGame(Player player) {
        return getOngoingGame(player).isPresent();
    }

    public void addGame(Game game) {
        if (onGoingGames.contains(game)) {
            return;
        }

        onGoingGames.add(game);
    }

    public Optional<Game> getOngoingGame(Player player) {
        return onGoingGames.stream().filter(game -> game.getPlayer(player.getUuid()).isPresent()).findFirst();
    }

    public void removeGame(Game game) {
        onGoingGames.remove(game);
    }
}
