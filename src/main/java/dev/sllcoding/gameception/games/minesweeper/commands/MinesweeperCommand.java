package dev.sllcoding.gameception.games.minesweeper.commands;

import dev.sllcoding.gameception.games.minesweeper.MinesweeperGame;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntity;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntityFactory;
import dev.sllcoding.gameception.games.minesweeper.container.MinesweeperGameContainer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.Player;

import java.util.List;

public class MinesweeperCommand extends Command {
    private final MinesweeperGameContainer minesweeperGameContainer;

    public MinesweeperCommand(MinesweeperGameContainer minesweeperGameContainer) {
        super("minesweeper");
        this.minesweeperGameContainer = minesweeperGameContainer;

        setCondition(Conditions::playerOnly);
        setDefaultExecutor(this::handleCommand);
    }

    private void handleCommand(CommandSender commandSender, CommandContext commandContext) {
        Player player = commandSender.asPlayer();

        MinesweeperGame ongoingGame = minesweeperGameContainer.getOngoingGame(player);

        if (ongoingGame != null) {
            player.sendMessage(Component.text("You are already in a game!", NamedTextColor.RED));
            return;
        }

        MinesweeperTileEntityFactory minesweeperTileEntityFactory = new MinesweeperTileEntityFactory();
        List<MinesweeperTileEntity> tileEntities = minesweeperTileEntityFactory.createTileEntities(player);

        for (MinesweeperTileEntity tileEntity : tileEntities) {
            tileEntity.initialize();
        }

        MinesweeperGame minesweeperGame = new MinesweeperGame(player);
        minesweeperGameContainer.addOngoingGame(player, minesweeperGame);
    }
}
