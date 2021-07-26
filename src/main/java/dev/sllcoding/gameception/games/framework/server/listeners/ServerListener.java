package dev.sllcoding.gameception.games.framework.server.listeners;

import com.github.christian162.annotations.Listen;
import com.github.christian162.annotations.Node;
import com.github.christian162.interfaces.Listener;
import dev.sllcoding.gameception.games.framework.server.GameceptionServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.Event;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.ping.ResponseData;

@Node(name = "gameception", event = Event.class)
public class ServerListener implements Listener {

    @Listen
    public void onPlayerLogin(PlayerLoginEvent event) {
        event.setSpawningInstance(GameceptionServer.getInstance().getMainInstance());
        event.getPlayer().setRespawnPoint(new Pos(0, 40, 0));

        event.getPlayer().sendPlayerListHeaderAndFooter(
                MiniMessage.get().parse("                      \n                      <bold><gradient:#ff6c32:#ff76b6>Gameception</gradient></bold>                      \n                      "),
                MiniMessage.get().parse("                      \n                      Bottom Text                      \n                      ")
        );
    }

    @Listen
    public void onServerListPing(ServerListPingEvent event) {
        ResponseData data = new ResponseData();

        data.setDescription(Component.text("Games in a game :flushed:"));
        data.setMaxPlayer(-1);

        event.setResponseData(data);
    }

}