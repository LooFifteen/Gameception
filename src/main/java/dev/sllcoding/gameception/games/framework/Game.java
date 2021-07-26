package dev.sllcoding.gameception.games.framework;

import net.minestom.server.entity.Player;

public interface Game {
    int getId();

    void startGame();
    void endGame();

    void onTileClick(Player player, AbstractTileEntity abstractTileEntity);
}
