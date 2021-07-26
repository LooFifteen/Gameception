package dev.sllcoding.gameception.games;

import net.minestom.server.entity.Player;
import net.minestom.server.map.framebuffers.DirectFramebuffer;
import net.minestom.server.map.framebuffers.Graphics2DFramebuffer;
import net.minestom.server.network.packet.server.play.MapDataPacket;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GamePlayer {

    private static final Map<Player, GamePlayer> gamePlayers = new HashMap<>();
    public static GamePlayer getGamePlayer(Player player) {
        if (gamePlayers.containsKey(player)) return gamePlayers.get(player);
        else {
            GamePlayer gamePlayer = new GamePlayer(player);
            gamePlayers.put(player, gamePlayer);
            return gamePlayer;
        }
    }
    public static void removeGamePlayer(Player player) {
        gamePlayers.remove(player);
    }

    private final Player player;
    private final Map<Integer, Graphics2D> maps = new HashMap<>();

    private GamePlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getNextAvailableMapId() {
        for (int i = 0; i <= maps.size(); i++) {
            if (!maps.containsKey(i)) return i;
        }
        return maps.size() + 1;
    }

    public int renderMap(Consumer<Graphics2D> graphics) {
        int id = getNextAvailableMapId();
        MapDataPacket map = new MapDataPacket();
        map.mapId = id;
        Graphics2DFramebuffer fb = new Graphics2DFramebuffer();
        Graphics2D renderer = fb.getRenderer();
        maps.put(id, renderer);
        graphics.accept(renderer);
        fb.preparePacket(map);
        player.getPlayerConnection().sendPacket(map);
        return id;
    }

    public void removeMap(int id) {
        MapDataPacket map = new MapDataPacket();
        map.mapId = id;
        DirectFramebuffer fb = new DirectFramebuffer();
        fb.preparePacket(map);
        player.getPlayerConnection().sendPacket(map);
        maps.remove(id);
    }

}
