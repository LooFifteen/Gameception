package dev.sllcoding.gameception.games.framework;

import dev.sllcoding.gameception.games.framework.endings.Ending;
import net.minestom.server.entity.Player;

public interface Game {
    int getId();

    void startGame();
    void endGame(Ending ending);

    void onTileClick(Player player, AbstractTileEntity abstractTileEntity);
}
