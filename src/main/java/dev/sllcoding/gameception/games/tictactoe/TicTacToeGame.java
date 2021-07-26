package dev.sllcoding.gameception.games.tictactoe;

import net.kyori.adventure.text.Component;
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
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.function.Consumer;

// TODO: Don't override entity with existing X or O mark
// TODO: After every move, check for a victory.
// TODO: If the player leaves while game is active, the game ends.

public class TicTacToeGame {
    private TicTacToeTeam currentTurn = TicTacToeTeam.X;

    private final TicTacToePlayer player1;
    private final TicTacToePlayer player2;

    private TicTacToeBoard ticTacToeBoard;

    private boolean isGameOver = false;
    private Consumer<TicTacToeGame> gameOverCallback;

    public TicTacToeGame(Player firstPlayer, Player secondPlayer) {
        this.player1 = new TicTacToePlayer(firstPlayer, TicTacToeTeam.X);
        this.player2 = new TicTacToePlayer(secondPlayer, TicTacToeTeam.O);
    }

    public boolean isTurn(Player player) {
        TicTacToePlayer ticTacToePlayer = fromPlayer(player);
        return ticTacToePlayer != null && ticTacToePlayer.ticTacToeTeam() == currentTurn;
    }

    public TicTacToePlayer fromPlayer(Player player) {
        return player1.player().getUuid().equals(player.getUuid()) ? player1 : (player2.player().getUuid().equals(player.getUuid()) ? player2 : null);
    }

    public void setTicTacToeBoard(TicTacToeBoard ticTacToeBoard) {
        this.ticTacToeBoard = ticTacToeBoard;
    }

    public TicTacToeBoard getTicTacToeBoard() {
        return ticTacToeBoard;
    }

    public void doTurn(Player player, Entity entity) {
        if (isGameOver) {
            return;
        }

        if (ticTacToeBoard.isEntityMarked(entity)) {
            return;
        }

        if (!ticTacToeBoard.isEntityApartOfBoard(entity)) {
            player.sendMessage(Component.text("wowowowowowow"));
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

        ticTacToeBoard.addMarkedEntity(entity, currentTurn);

        if (ticTacToeBoard.didTeamWin(currentTurn)) {
            // TODO: center text
            clearAndDraw(String.format("%s HAS WON", player.getUsername()));
            isGameOver = true;
            gameOverCallback.accept(this);
        } else if (ticTacToeBoard.isTie()) {
            clearAndDraw("TIE!");
            gameOverCallback.accept(this);
        }

        currentTurn = ticTacToePlayer.ticTacToeTeam() == TicTacToeTeam.O ? TicTacToeTeam.X : TicTacToeTeam.O;
    }

    public void setGameOverCallback(Consumer<TicTacToeGame> gameOverCallback) {
        this.gameOverCallback = gameOverCallback;
    }

    private void clearAndDraw(String text) {
        Entity boardPart = ticTacToeBoard.getBoardPart(1, 1);
        ItemFrameMeta boardPartMeta = (ItemFrameMeta) boardPart.getEntityMeta();
        ItemStack boardStack = boardPartMeta.getItem();

        clearBoard();

        applyToMap(boardStack, graphics2D -> {
            graphics2D.drawString(text, 64 - text.length(), 64);
        });
    }

    private void clearBoard() {
        Entity[][] entities = ticTacToeBoard.getEntities();

        for (Entity[] entity : entities) {
            for (Entity entity1 : entity) {
                ItemFrameMeta boardPartMeta = (ItemFrameMeta) entity1.getEntityMeta();
                ItemStack boardStack = boardPartMeta.getItem();

                applyToMap(boardStack, graphics2D -> {
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.fillRect(0, 0, 128, 128);
                });
            }
        }
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

        int length = 30;

        graphics2D.drawLine(x - length, y - length, x + length, y + length);
        graphics2D.drawLine(x - length, y + length, x + length, y - length);
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
