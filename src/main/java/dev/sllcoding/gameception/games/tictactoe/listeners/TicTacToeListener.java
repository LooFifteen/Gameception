package dev.sllcoding.gameception.games.tictactoe.listeners;

import com.github.christian162.annotations.Filter;
import com.github.christian162.annotations.Listen;
import com.github.christian162.annotations.Node;
import com.github.christian162.interfaces.Listener;
import dev.sllcoding.gameception.games.framework.v1.Game;
import dev.sllcoding.gameception.games.framework.v1.GameContainer;
import dev.sllcoding.gameception.games.framework.v1.GamePlayer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.event.trait.PlayerEvent;

import java.util.List;
import java.util.Optional;

@Node(name = "tic-tac-toe", event = PlayerEvent.class)
public class TicTacToeListener implements Listener {
    private GameContainer gameContainer;

    public TicTacToeListener(GameContainer gameContainer) {
        this.gameContainer = gameContainer;
    }

    @Filter
    public boolean check(PlayerEvent playerEvent) {
        return gameContainer.isPlayerInGame(playerEvent.getPlayer());
    }

    @Listen
    public void onPlayerEntityInteract(PlayerEntityInteractEvent playerEntityInteractEvent) {
        Player player = playerEntityInteractEvent.getPlayer();
        Entity entity = playerEntityInteractEvent.getTarget();

        Optional<Game> ongoingGame = gameContainer.getOngoingGame(player);

        if (ongoingGame.isEmpty()) {
            return;
        }

        Game game = ongoingGame.get();
        List<GamePlayer> players = game.getPlayers();

        Optional<GamePlayer> firstGamePlayer = players.stream().filter(x -> x.getPlayer().equals(player)).findFirst();

        if (firstGamePlayer.isEmpty()) {
            return;
        }

        GamePlayer gamePlayer = firstGamePlayer.get();

        if (!gamePlayer.getTeam().equals(game.getCurrentTurn())) {
            return;
        }

        game.update(entity, player);
    }
}
