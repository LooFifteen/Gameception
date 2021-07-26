package dev.sllcoding.gameception.games.tictactoe;

import dev.sllcoding.gameception.games.framework.GameBoard;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.Team;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.metadata.other.ItemFrameMeta;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.metadata.MapMeta;
import net.minestom.server.map.framebuffers.Graphics2DFramebuffer;
import net.minestom.server.network.packet.server.play.MapDataPacket;
import net.minestom.server.utils.PacketUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TicTacToeBoard implements GameBoard {
    private final Entity[][] entities;
    private final List<Entity> renderedEntities;
    private final Map<Entity, Team> entityMap;

    public TicTacToeBoard(Entity[][] entities) {
        this.entities = entities;
        this.renderedEntities = new ArrayList<>();
        this.entityMap = new HashMap<>();
    }

    @Override
    public Map<Entity, Team> getEntityMap() {
        return entityMap;
    }

    @Override
    public Entity[][] getBoardEntities() {
        return entities;
    }

    @Override
    public void initialize() {
        Entity[][] entities = this.entities;

        for (Entity[] entity : entities) {
            for (Entity entity1 : entity) {
                placeOnEntity(entity1, graphics2D -> {
                    graphics2D.drawRect(10, 10, 110, 110);
                });
            }
        }
    }

    @Override
    public void remove() {
        for (Entity[] entity : entities) {
            for (Entity entity1 : entity) {
                entity1.remove();
            }
        }
    }

    @Override
    public boolean allPresent() {
        for (Entity[] entity : entities) {
            for (Entity entity1 : entity) {
                if (!renderedEntities.contains(entity1)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean canPlace(Entity entity, GamePlayer gamePlayer) {
        return !renderedEntities.contains(entity);
    }

    @Override
    public void place(Entity entity, GamePlayer gamePlayer) {
        if (!canPlace(entity, gamePlayer)) {
            return;
        }

        placeOnEntity(entity, graphics2D -> {
            gamePlayer.getTeam().getObject().render(graphics2D, 64, 64);
            entityMap.putIfAbsent(entity, gamePlayer.getTeam());

            renderedEntities.add(entity);
        });
    }

    private void placeOnEntity(Entity entity, Consumer<Graphics2D> consumer) {
        ItemFrameMeta boardPartMeta = (ItemFrameMeta) entity.getEntityMeta();

        ItemStack boardStack = boardPartMeta.getItem();
        MapMeta mapMeta = (MapMeta) boardStack.getMeta();

        MapDataPacket mapDataPacket = new MapDataPacket();
        mapDataPacket.mapId = mapMeta.getMapId();

        Graphics2DFramebuffer framebuffer = new Graphics2DFramebuffer();
        Graphics2D graphics2D = framebuffer.getRenderer();

        consumer.accept(graphics2D);

        framebuffer.preparePacket(mapDataPacket);
        PacketUtils.sendGroupedPacket(MinecraftServer.getConnectionManager().getOnlinePlayers(), mapDataPacket);
    }
}
