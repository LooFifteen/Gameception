package dev.sllcoding.gameception.games;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.other.ItemFrameMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.MapMeta;

import java.util.concurrent.ThreadLocalRandom;

public class GameBoardCreator {
    public Entity[][] createEntities(int rows, int columns, Pos position, Instance instance) {
        int row = 0, j = 0;
        Entity[][] entities = new Entity[rows][columns];

        for (int i = 0; i < rows * columns; i++) {
            if (i > 0 && i % columns == 0) {
                row++;
                j = 0;
            }

            ItemStack map = createMap();
            Pos newPosition = position.withYaw(position.yaw() + 180);
            Entity itemFrame = createItemFrame(instance, newPosition, map);
            entities[row][j] = itemFrame;

            j++;
        }

        return entities;
    }

    private Entity createItemFrame(Instance instance, Pos pos, ItemStack map) {
        Entity entity = new Entity(EntityType.ITEM_FRAME);
        ItemFrameMeta entityMeta = (ItemFrameMeta) entity.getEntityMeta();

        entityMeta.setNotifyAboutChanges(false);
        entityMeta.setOrientation(ItemFrameMeta.Orientation.NORTH);
        entityMeta.setItem(map);
        entityMeta.setNotifyAboutChanges(true);

        entity.setInstance(instance, pos);
        return entity;
    }

    private ItemStack createMap() {
        int randomMapId = ThreadLocalRandom.current().nextInt(100);

        return ItemStack.of(Material.FILLED_MAP).withMeta(MapMeta.class, mapMeta -> {
            mapMeta.mapId(randomMapId);
        });
    }
}
