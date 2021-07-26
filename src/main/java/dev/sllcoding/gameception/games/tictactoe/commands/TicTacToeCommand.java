package dev.sllcoding.gameception.games.tictactoe.commands;

import dev.sllcoding.gameception.games.tictactoe.TicTacToeGame;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeGameContainer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.other.ItemFrameMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.MapMeta;
import net.minestom.server.map.framebuffers.Graphics2DFramebuffer;
import net.minestom.server.network.packet.server.play.MapDataPacket;
import net.minestom.server.utils.PacketUtils;
import net.minestom.server.utils.entity.EntityFinder;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class TicTacToeCommand extends Command {
    public TicTacToeCommand(TicTacToeGameContainer ticTacToeGameContainer) {
        super("tictactoe", "ttt");

        setCondition(Conditions::playerOnly);
        ArgumentEntity player = ArgumentType.Entity("player");

        setDefaultExecutor(((commandSender, commandContext) -> {
            commandSender.sendMessage(Component.text("Usage: /tictactoe <opponent>"));
        }));

        addSyntax(((commandSender, commandContext) -> {
            Player sender = commandSender.asPlayer();

            if (sender == null) {
                return;
            }

            if (ticTacToeGameContainer.isPlayerInGame(sender)) {
                sender.sendMessage(Component.text("You are currently in a game!"));
                return;
            }

            EntityFinder entityFinder = commandContext.get(player);
            Entity firstEntity = entityFinder.findFirstEntity(commandSender);

            if (firstEntity instanceof Player otherPlayer) {
                if (ticTacToeGameContainer.isPlayerInGame(otherPlayer)) {
                    sender.sendMessage(Component.text(String.format("%s is currently in a game", otherPlayer.getName()), NamedTextColor.RED));
                    return;
                }

                TicTacToeGame ticTacToeGame = new TicTacToeGame(sender, otherPlayer);
                ticTacToeGameContainer.addGame(ticTacToeGame);

                int row = 0;

                for (int i = 0, j = 0; i < 9; i++) {
                    if (i > 0 && i % 3 == 0) {
                        row++;
                        j = 0;
                    }

                    ItemStack map = createMap(sender, ticTacToeGame);

                    Pos position = sender.getPosition();
                    Pos newPosition = position.add(j, row, 0).withYaw(position.yaw() + 180);
                    createItemFrame(sender.getInstance(), newPosition, map);
                    j++;
                }
            }
        }), player);
    }

    private Entity createItemFrame(Instance instance, Pos pos, ItemStack map) {
        Entity entity = new Entity(EntityType.ITEM_FRAME);
        ItemFrameMeta entityMeta = (ItemFrameMeta) entity.getEntityMeta();

        entityMeta.setNotifyAboutChanges(false);
        entityMeta.setOrientation(ItemFrameMeta.Orientation.NORTH);
        entityMeta.setItem(map);
        entityMeta.setNotifyAboutChanges(true);

        entity.setInstance(instance, pos);
        return entity;
    }

    private ItemStack createMap(Player player, TicTacToeGame ticTacToeGame) {
        int i = ThreadLocalRandom.current().nextInt(100);

        ItemStack map = ItemStack.of(Material.FILLED_MAP).withMeta(MapMeta.class, mapMeta -> {
            mapMeta.mapId(i);
        });

        ticTacToeGame.applyToMap(map, ticTacToeGame::drawEmptyRect);
        return map;
    }
}
