package dev.sllcoding.gameception.games.framework.v2;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;

public class GameTileEntity extends Entity {
    public GameTileEntity() {
        super(EntityType.ITEM_FRAME);
    }

    public void test() {
        setTag();
    }
}
