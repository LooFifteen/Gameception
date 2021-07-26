package dev.sllcoding.gameception.games.tictactoe;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.other.ItemFrameMeta;
import net.minestom.server.item.ItemMeta;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.MapMeta;
import net.minestom.server.map.framebuffers.Graphics2DFramebuffer;
import net.minestom.server.network.packet.server.play.MapDataPacket;
import net.minestom.server.utils.PacketUtils;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.function.Consumer;

public class TicTacToeGame {
    private TicTacToeTeam currentTurn = TicTacToeTeam.X;

    private final TicTacToePlayer player1;
    private final TicTacToePlayer player2;

    private TicTacToeBoard ticTacToeBoard;

    public TicTacToeGame(Player firstPlayer, Player secondPlayer) {
        this.player1 = new TicTacToePlayer(firstPlayer, TicTacToeTeam.X);
        this.player2 = new TicTacToePlayer(secondPlayer, TicTacToeTeam.O);
    }

    public boolean isTurn(Player player) {
        TicTacToePlayer ticTacToePlayer = fromPlayer(player);
        return ticTacToePlayer != null && ticTacToePlayer.ticTacToeTeam() == currentTurn;
    }

    public TicTacToePlayer fromPlayer(Player player) {
        return player1.player().equals(player) ? player1 : (player2.player().equals(player) ? player2 : null);
    }

    public void setTicTacToeBoard(TicTacToeBoard ticTacToeBoard) {
        this.ticTacToeBoard = ticTacToeBoard;
    }

    public TicTacToeBoard getTicTacToeBoard() {
        return ticTacToeBoard;
    }

    public void doTurn(Player player, Entity entity) {
        if (!ticTacToeBoard.isEntityApartOfBoard(entity)) {
            return;
        }

        if (entity.getEntityType() != EntityType.ITEM_FRAME) {
            return;
        }

        if (!isTurn(player)) {
            return;
        }

        // TODO:
        // Move to Renderer.java
        // Use abstraction
        ItemFrameMeta itemFrameMeta = (ItemFrameMeta) entity.getEntityMeta();
        ItemStack entityItemStack = itemFrameMeta.getItem();

        if (entityItemStack.getMaterial() != Material.FILLED_MAP) {
            return;
        }

        TicTacToePlayer ticTacToePlayer = fromPlayer(player);


        if (ticTacToePlayer == null) {
            return;
        }

        applyToMap(entityItemStack, graphics2D -> {
            switch (ticTacToePlayer.ticTacToeTeam()) {
                case X -> {
                    drawX(64, 64, graphics2D);
                }
                case O -> {
                    drawCircle(64, 64, graphics2D);
                }
            }
        });

        currentTurn = ticTacToePlayer.ticTacToeTeam() == TicTacToeTeam.O ? TicTacToeTeam.X : TicTacToeTeam.O;
    }

    public void applyToMap(ItemStack map, Consumer<Graphics2D> consumer) {
        if (map.getMeta() instanceof MapMeta mapMeta) {
            MapDataPacket mapDataPacket = new MapDataPacket();
            mapDataPacket.mapId = mapMeta.getMapId();

            Graphics2DFramebuffer framebuffer = new Graphics2DFramebuffer();
            Graphics2D graphics2D = framebuffer.getRenderer();

            consumer.accept(graphics2D);

            framebuffer.preparePacket(mapDataPacket);
            PacketUtils.sendGroupedPacket(MinecraftServer.getConnectionManager().getOnlinePlayers(), mapDataPacket);
        }
    }

    public void drawEmptyRect(Graphics2D graphics2D) {
        graphics2D.drawRect(10, 10, 110, 110);
    }

    public void drawX(int x, int y, Graphics2D graphics2D) {
        drawEmptyRect(graphics2D);
        graphics2D.drawLine(x - 10, y - 10, x + 10, y + 10);
        graphics2D.drawLine(x - 10, y + 10, x + 10, y - 10);
    }

    public void drawCircle(int x, int y, Graphics2D graphics2D) {
        drawEmptyRect(graphics2D);

        int ovalWidth = 60;
        int ovalHeight = 60;
        graphics2D.drawOval(x - ovalWidth / 2, y - ovalHeight / 2, ovalWidth, ovalHeight);
    }

    public @Nullable TicTacToePlayer getWinner() {
        return null;
    }

    public TicTacToePlayer getPlayer1() {
        return player1;
    }

    public TicTacToePlayer getPlayer2() {
        return player2;
    }
}
