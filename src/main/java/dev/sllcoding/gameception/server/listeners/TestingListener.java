package dev.sllcoding.gameception.server.listeners;

import com.github.christian162.annotations.Listen;
import com.github.christian162.annotations.Node;
import com.github.christian162.interfaces.Listener;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeGame;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.other.ItemFrameMeta;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.event.trait.PlayerEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.MapMeta;
import net.minestom.server.map.framebuffers.Graphics2DFramebuffer;
import net.minestom.server.network.packet.server.play.MapDataPacket;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

@Node(name = "testing", event = PlayerEvent.class, priority = 0)
public class TestingListener implements Listener {
//    @Listen
//    public void onSpawn(PlayerSpawnEvent playerSpawnEvent) {
//        Player player = playerSpawnEvent.getPlayer();
//        TicTacToeGame ticTacToeGame = new TicTacToeGame(player, player);
//
//        int row = 0;
//
//
//
//
//        for (int i = 0, j = 0; i < 9; i++) {
//
//
//            if (i > 0 && i % 3 == 0) {
//                row++;
//                j = 0;
//            }
//
//            ItemStack map = createMap(player, ticTacToeGame);
//
//            Entity entity = new Entity(EntityType.ITEM_FRAME);
//            ItemFrameMeta entityMeta = (ItemFrameMeta) entity.getEntityMeta();
//
//            entityMeta.setNotifyAboutChanges(false);
//            entityMeta.setOrientation(ItemFrameMeta.Orientation.NORTH);
//            entityMeta.setItem(map);
//            entityMeta.setNotifyAboutChanges(true);
//
//            Pos position = player.getPosition();
//
//            entity.setInstance(player.getInstance(), position.add(j, row, 0).withYaw(position.yaw() + 180));
//            //entity.teleport(new Pos(j, row, 0).withYaw(position.yaw() + 180));
//
//            j++;
//        }
//    }
//
//    private ItemStack createMap(Player player, TicTacToeGame ticTacToeGame) {
//        int i = ThreadLocalRandom.current().nextInt(100);
//
//        ItemStack map = ItemStack.of(Material.FILLED_MAP).withMeta(MapMeta.class, mapMeta -> {
//            mapMeta.mapId(i);
//        });
//
//        MapDataPacket mapDataPacket = new MapDataPacket();
//        mapDataPacket.mapId = i;
//
//        Graphics2DFramebuffer framebuffer = new Graphics2DFramebuffer();
//
//        Graphics2D graphics2D = framebuffer.getRenderer();
//        ticTacToeGame.doTurn(64, 64, player, graphics2D);
//
//        framebuffer.preparePacket(mapDataPacket);
//        player.getPlayerConnection().sendPacket(mapDataPacket);
//
//        return map;
//    }
}
