package dev.sllcoding.gameception.server.commands.gameception;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;

public class StopCommand extends Command {

    public StopCommand() {
        super("stop");
        ArgumentInteger argumentId = ArgumentType.Integer("id");

        addSyntax((sender, context) -> {
            int id = context.get(argumentId);
            // TODO: Stop game with ID `id`. Reason: Tie/Draw
        }, argumentId);
    }

}
