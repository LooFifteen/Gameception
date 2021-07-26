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
    public Entity[][] createEntities(int columns, int rows, Pos position, Instance instance) {
        Entity[][] entities = new Entity[rows][columns];

        int currentRow = 0;
        int currentColumn = 0;

        while (currentRow < rows) {
            ItemStack map = createMap();
            Pos newPosition = position.add(currentColumn, 0, currentRow).withYaw(180).withPitch(90);
            Entity itemFrame = createItemFrame(instance, newPosition, map);
            entities[currentRow][currentColumn] = itemFrame;

            currentColumn++;

            if (currentColumn == columns) {
                currentColumn = 0;
                currentRow++;
            }
        }

        return entities;
    }

    private Entity createItemFrame(Instance instance, Pos pos, ItemStack map) {
        Entity entity = new Entity(EntityType.ITEM_FRAME);
        ItemFrameMeta entityMeta = (ItemFrameMeta) entity.getEntityMeta();

        entityMeta.setNotifyAboutChanges(false);
        entityMeta.setOrientation(ItemFrameMeta.Orientation.UP);
        entityMeta.setItem(map);
        entityMeta.setNotifyAboutChanges(true);

        entity.setInstance(instance, pos);
        return entity;
    }

    private ItemStack createMap() {
        int randomMapId = ThreadLocalRandom.current().nextInt(10000);

        return ItemStack.of(Material.FILLED_MAP).withMeta(MapMeta.class, mapMeta -> {
            mapMeta.mapId(randomMapId);
        });
    }
}
