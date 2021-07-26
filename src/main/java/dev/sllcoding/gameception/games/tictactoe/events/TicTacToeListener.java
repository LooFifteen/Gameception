package dev.sllcoding.gameception.games.tictactoe.events;

import com.github.christian162.annotations.Filter;
import com.github.christian162.annotations.Listen;
import com.github.christian162.annotations.Node;
import com.github.christian162.interfaces.Listener;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeGame;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeGameContainer;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.event.trait.PlayerEvent;

import java.util.Optional;

@Node(name = "tic-tac-toe", event = PlayerEvent.class, priority = 0)
public class TicTacToeListener implements Listener {
    private TicTacToeGameContainer ticTacToeGameContainer;

    public TicTacToeListener(TicTacToeGameContainer ticTacToeGameContainer) {
        this.ticTacToeGameContainer = ticTacToeGameContainer;
    }

    @Filter
    public boolean check(PlayerEvent playerEvent) {
        return ticTacToeGameContainer.isPlayerInGame(playerEvent.getPlayer());
    }

    // TODO: Entity hover??

    @Listen
    public void onPlayerEntityInteract(PlayerEntityInteractEvent playerEntityInteractEvent) {
        Player player = playerEntityInteractEvent.getPlayer();
        Entity entity = playerEntityInteractEvent.getTarget();

        Optional<TicTacToeGame> ongoingGame = ticTacToeGameContainer.getOngoingGame(player);

        if (ongoingGame.isEmpty()) {
            return;
        }

        TicTacToeGame ticTacToeGame = ongoingGame.get();

        if (!ticTacToeGame.isTurn(player)) {
            return;
        }

        ticTacToeGame.doTurn(player, entity);
    }
}
