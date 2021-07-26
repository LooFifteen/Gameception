package dev.sllcoding.gameception.games.minesweeper.listeners;

import com.github.christian162.annotations.Listen;
import com.github.christian162.annotations.Node;
import com.github.christian162.interfaces.Listener;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntity;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.event.trait.PlayerEvent;

@Node(name = "minesweeper", event = PlayerEvent.class, priority = 0)
public class MinesweeperListener implements Listener {
    @Listen
    public void onPlayerInteractWithEntity(PlayerEntityInteractEvent playerEntityInteractEvent) {
        Player player = playerEntityInteractEvent.getPlayer();
        Entity entity = playerEntityInteractEvent.getTarget();

        if (!(entity instanceof MinesweeperTileEntity minesweeperTileEntity)) {
            return;
        }

        minesweeperTileEntity.update();
    }
}
