package dev.sllcoding.gameception.games.tictactoe.commands;

import dev.sllcoding.gameception.games.GameBoardCreator;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeBoard;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeGame;
import dev.sllcoding.gameception.games.tictactoe.TicTacToePlayer;
import dev.sllcoding.gameception.games.tictactoe.builders.TicTacToeGameBuilder;
import dev.sllcoding.gameception.games.tictactoe.teams.CircleTeam;
import dev.sllcoding.gameception.games.tictactoe.teams.CrossTeam;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

public class TicTacToeCommands extends Command {
    public TicTacToeCommands() {
        super("tictactoe");
        setCondition(Conditions::playerOnly);

        ArgumentEntity argumentEntity = ArgumentType.Entity("player");

        setDefaultExecutor(((commandSender, commandContext) -> {
            commandSender.sendMessage(Component.text("/tictactoe <opponent>", NamedTextColor.RED));
        }));

        addSyntax(((commandSender, commandContext) -> {
            Player player = commandSender.asPlayer();

            EntityFinder entityFinder = commandContext.get(argumentEntity);
            Entity firstEntity = entityFinder.findFirstEntity(commandSender);

            if (!(firstEntity instanceof Player target)) {
                return;
            }

            GameBoardCreator gameBoardCreator = new GameBoardCreator();
            Entity[][] entities = gameBoardCreator.createEntities(3, 3, player.getPosition(), player.getInstance());

            TicTacToeGameBuilder ticTacToeGameBuilder = new TicTacToeGameBuilder()
                    .addPlayer(new TicTacToePlayer(player, new CrossTeam()))
                    .addPlayer(new TicTacToePlayer(target, new CircleTeam()))
                    .withBoard(new TicTacToeBoard(entities));

            TicTacToeGame ticTacToeGame = ticTacToeGameBuilder.build();
            ticTacToeGame.start();

        }), argumentEntity);
    }
}
