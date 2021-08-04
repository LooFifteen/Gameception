package dev.sllcoding.gameception.games.minesweeper.listeners;

import com.github.christian162.annotations.Listen;
import com.github.christian162.annotations.Node;
import com.github.christian162.interfaces.Listener;
import dev.sllcoding.gameception.games.framework.AbstractTileEntity;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperGame;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntity;
import dev.sllcoding.gameception.games.minesweeper.container.MinesweeperGameContainer;
import dev.sllcoding.gameception.games.minesweeper.types.MineSweeperTileType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.event.trait.EntityEvent;
import net.minestom.server.event.trait.PlayerEvent;

@Node(name = "minesweeper", event = EntityEvent.class, priority = 0)
public class MinesweeperListener implements Listener {
    private final MinesweeperGameContainer minesweeperGameContainer;

    public MinesweeperListener(MinesweeperGameContainer minesweeperGameContainer) {
        this.minesweeperGameContainer = minesweeperGameContainer;
    }

    @Listen
    public void onEntityAttack(EntityAttackEvent entityAttackEvent) {
        Entity entity = entityAttackEvent.getEntity();
        Entity target = entityAttackEvent.getTarget();

        if (entity instanceof Player player && target instanceof MinesweeperTileEntity minesweeperTileEntity) {
            MineSweeperTileType renderedTileType = minesweeperTileEntity.getRenderedTileType();

            if (renderedTileType != MineSweeperTileType.Unknown && renderedTileType != MineSweeperTileType.Flagged) {
                player.sendMessage(Component.text("Hey, wtf?!?", NamedTextColor.RED));
                return;
            }

            if (renderedTileType == MineSweeperTileType.Flagged) {
                renderedTileType = MineSweeperTileType.Unknown;
            } else {
                renderedTileType = MineSweeperTileType.Flagged;
            }

            minesweeperTileEntity.setRenderedTileType(renderedTileType);
            minesweeperTileEntity.update();
        }
    }

    @Listen
    public void onPlayerInteractWithEntity(PlayerEntityInteractEvent playerEntityInteractEvent) {
        Player player = playerEntityInteractEvent.getPlayer();
        Entity entity = playerEntityInteractEvent.getTarget();

        if (!(entity instanceof MinesweeperTileEntity minesweeperTileEntity)) {
            return;
        }

        if (minesweeperTileEntity.getRenderedTileType() == MineSweeperTileType.Flagged) {
            return;
        }

        MinesweeperGame ongoingGame = minesweeperGameContainer.getOngoingGame(player);

        if (ongoingGame == null) {
            return;
        }

        minesweeperTileEntity.setRenderedTileType(minesweeperTileEntity.getMineSweeperTileType());
        ongoingGame.onTileClick(player, minesweeperTileEntity);
    }
}
