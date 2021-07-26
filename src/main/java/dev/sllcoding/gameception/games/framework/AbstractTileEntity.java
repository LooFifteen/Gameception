package dev.sllcoding.gameception.games.framework;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.other.ItemFrameMeta;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.metadata.MapMeta;
import net.minestom.server.map.framebuffers.Graphics2DFramebuffer;
import net.minestom.server.network.packet.server.play.MapDataPacket;
import net.minestom.server.utils.PacketUtils;

import java.awt.*;

public abstract class AbstractTileEntity extends Entity {
    public AbstractTileEntity() {
        super(EntityType.ITEM_FRAME);
    }

    public void draw(Renderable renderable, int x, int y) {
        ItemFrameMeta itemFrameMeta = (ItemFrameMeta) getEntityMeta();
        ItemStack boardStack = itemFrameMeta.getItem();

        if (boardStack.getMeta() instanceof MapMeta mapMeta) {
            MapDataPacket mapDataPacket = new MapDataPacket();
            mapDataPacket.mapId = mapMeta.getMapId();

            Graphics2DFramebuffer framebuffer = new Graphics2DFramebuffer();
            Graphics2D graphics2D = framebuffer.getRenderer();

            renderable.render(graphics2D, x, y);

            framebuffer.preparePacket(mapDataPacket);
            PacketUtils.sendGroupedPacket(MinecraftServer.getConnectionManager().getOnlinePlayers(), mapDataPacket);
        }
    }

    abstract void initialize();
}