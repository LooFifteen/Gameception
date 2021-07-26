package dev.sllcoding.gameception.games.tictactoe.commands;

import dev.sllcoding.gameception.games.GameBoardCreator;
import dev.sllcoding.gameception.games.framework.GameContainer;
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
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

public class TicTacToeCommand extends Command {
    public TicTacToeCommand(GameContainer gameContainer) {
        super("tictactoe");
        setCondition(Conditions::playerOnly);

        ArgumentEntity argumentEntity = ArgumentType.Entity("player");
        ArgumentInteger rows = ArgumentType.Integer("rows");

        setDefaultExecutor(((commandSender, commandContext) -> {
            commandSender.sendMessage(Component.text("/tictactoe <opponent>", NamedTextColor.RED));
        }));

        addSyntax(((commandSender, commandContext) -> {
            Player player = commandSender.asPlayer();

            EntityFinder entityFinder = commandContext.get(argumentEntity);
            Integer integer = commandContext.get(rows);

            Entity firstEntity = entityFinder.findFirstEntity(commandSender);

            if (!(firstEntity instanceof Player target)) {
                return;
            }

            if (player.equals(target)) {
                player.sendMessage(Component.text("Hey..... wtf?", NamedTextColor.GRAY));
                return;
            }

            if (gameContainer.isPlayerInGame(player) || gameContainer.isPlayerInGame(target)) {
                return;
            }

            GameBoardCreator gameBoardCreator = new GameBoardCreator();

            Entity[][] entities = gameBoardCreator.createEntities(integer, integer, player.getPosition(), player.getInstance());

            TicTacToeGameBuilder ticTacToeGameBuilder = new TicTacToeGameBuilder(gameContainer)
                    .addPlayer(new TicTacToePlayer(player, new CrossTeam()))
                    .addPlayer(new TicTacToePlayer(target, new CircleTeam()))
                    .withBoard(new TicTacToeBoard(entities));

            TicTacToeGame ticTacToeGame = ticTacToeGameBuilder.build();
            ticTacToeGame.start();

            gameContainer.addGame(ticTacToeGame);

        }), argumentEntity, rows);
    }
}
