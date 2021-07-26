package dev.sllcoding.gameception.server.commands.gameception;

import net.minestom.server.command.builder.Command;

public class GamesCommand extends Command {

    public GamesCommand() {
        super("games");
        addSubcommand(new StopCommand());
    }

}
