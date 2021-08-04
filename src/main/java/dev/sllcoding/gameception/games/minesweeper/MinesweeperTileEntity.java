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
//    private final MinesweeperRenderable minesweeperRenderable;

    private final MineSweeperTileType mineSweeperTileType;
    private MineSweeperTileType renderedTileType;

    public MinesweeperTileEntity(Instance instance, Pos position, MineSweeperTileType mineSweeperTileType) {
        this.instance = instance;
        this.position = position;

//        this.minesweeperRenderable = new MinesweeperRenderable(mineSweeperTileType);

        this.mineSweeperTileType = mineSweeperTileType;
        this.renderedTileType = MineSweeperTileType.Unknown;
    }

    @Override
    public void initialize() {
        ItemFrameMeta itemFrameMeta = (ItemFrameMeta) getEntityMeta();
        itemFrameMeta.setOrientation(ItemFrameMeta.Orientation.UP);

        update();

        setInstance(instance, position);
    }

    @Override
    public void update() {
        draw(new MinesweeperRenderable(renderedTileType), 0, 0);
    }

    public MineSweeperTileType getMineSweeperTileType() {
        return mineSweeperTileType;
    }

    public MineSweeperTileType getRenderedTileType() {
        return renderedTileType;
    }

    public void setRenderedTileType(MineSweeperTileType renderedTileType) {
        this.renderedTileType = renderedTileType;
    }
}