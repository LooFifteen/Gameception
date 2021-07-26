package dev.sllcoding.gameception.server.listeners;

import com.github.christian162.annotations.Listen;
import com.github.christian162.annotations.Node;
import com.github.christian162.interfaces.Listener;
import dev.sllcoding.gameception.games.GamePlayer;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.trait.PlayerEvent;

@Node(name = "game-listener", event = PlayerEvent.class)
public class GameListener implements Listener {

    @Listen
    public void onPlayerJoin(PlayerLoginEvent event) {
        GamePlayer.getGamePlayer(event.getPlayer());
    }

    @Listen
    public void onPlayerLeave(PlayerDisconnectEvent event) {
        GamePlayer gamePlayer = GamePlayer.getGamePlayer(event.getPlayer());
        if (gamePlayer.getCurrentGame() != null) gamePlayer.getCurrentGame().removePlayer(gamePlayer);
        GamePlayer.removeGamePlayer(event.getPlayer());
    }

}
