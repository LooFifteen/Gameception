package dev.sllcoding.gameception.games.minesweeper.commands;

import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntity;
import dev.sllcoding.gameception.games.minesweeper.MinesweeperTileEntityFactory;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.condition.Conditions;

import java.util.List;

public class MinesweeperCommand extends Command {
    public MinesweeperCommand() {
        super("minesweeper");
        setCondition(Conditions::playerOnly);

        setDefaultExecutor(((commandSender, commandContext) -> {
            MinesweeperTileEntityFactory minesweeperTileEntityFactory = new MinesweeperTileEntityFactory();
            List<MinesweeperTileEntity> tileEntities = minesweeperTileEntityFactory.createTileEntities(commandSender.asPlayer());

            for (MinesweeperTileEntity tileEntity : tileEntities) {
                tileEntity.initialize();
            }
        }));
    }
}
