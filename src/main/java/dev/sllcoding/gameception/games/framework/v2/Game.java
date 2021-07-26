package dev.sllcoding.gameception.games.framework.v2;

import net.minestom.server.entity.Player;

public interface Game {
    int getId();

    void startGame();
    void endGame();

    void onTileClick(Player player, AbstractTileEntity abstractTileEntity);
}
