package dev.sllcoding.gameception.games.minesweeper;

import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.minesweeper.rendering.MinesweeperRenderable;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.metadata.other.ItemFrameMeta;
import net.minestom.server.instance.Instance;

public class MinesweeperTileEntity extends AbstractTileEntity {
    private final Instance instance;
    private final Pos position;
    private final MinesweeperRenderable minesweeperRenderable;

    public MinesweeperTileEntity(Instance instance, Pos position, MineSweeperTileType mineSweeperTileType) {
        this.instance = instance;
        this.position = position;
        this.minesweeperRenderable = new MinesweeperRenderable(mineSweeperTileType);
    }

    @Override
    public void initialize() {
        ItemFrameMeta itemFrameMeta = (ItemFrameMeta) getEntityMeta();
        itemFrameMeta.setOrientation(ItemFrameMeta.Orientation.UP);

        draw(new MinesweeperRenderable(MineSweeperTileType.Unknown), 0, 0);

        setInstance(instance, position);
    }

    @Override
    public void update() {
        draw(minesweeperRenderable, 0, 0);
    }
}