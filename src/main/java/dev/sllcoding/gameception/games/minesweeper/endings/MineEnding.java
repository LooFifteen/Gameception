package dev.sllcoding.gameception.games.minesweeper.endings;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.endings.Ending;
import dev.sllcoding.gameception.games.framework.singleplayer.SingleplayerGameBase;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.ExplosionPacket;

public class MineEnding implements Ending {
    private Game game;

    public MineEnding(Game game) {
        this.game = game;
    }

    @Override
    public Title getTitle() {
        return Title.title(Component.text("Game over!", NamedTextColor.RED),
                Component.text("You found a mine! HAHA", NamedTextColor.RED));
    }

    @Override
    public void execute() {
        // TODO: refactor the framework... lol
        if (game instanceof SingleplayerGameBase singleplayerGameBase) {
            Player player = singleplayerGameBase.getGamePlayer().getPlayer();
            Pos position = player.getPosition();

            ExplosionPacket explosionPacket = new ExplosionPacket();
            explosionPacket.x = (float) position.x();
            explosionPacket.y = (float) position.y();
            explosionPacket.z = (float) position.z();
            explosionPacket.playerMotionX = 1.0f;
            explosionPacket.playerMotionY = 1.0f;
            explosionPacket.playerMotionZ = 1.0f;
            explosionPacket.radius = 3.0f;

            player.getPlayerConnection().sendPacket(explosionPacket);
        }
    }
}
