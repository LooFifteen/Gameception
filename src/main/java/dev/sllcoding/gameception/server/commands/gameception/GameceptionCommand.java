package dev.sllcoding.gameception.server.commands.gameception;

import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;

public class GameceptionCommand extends Command {

    public GameceptionCommand() {
        super("gameception");

        setDefaultExecutor(this::defaultExecutor);
        addSubcommand(new GamesCommand());
    }

    private void defaultExecutor(CommandSender sender, CommandContext context) {
        sender.sendMessage(MiniMessage.get().parse("<gradient:#ff6c32:#ff76b6>https://github.com/SLLCoding/Gameception</gradient>")
                .clickEvent(ClickEvent.openUrl("https://github.com/SLLCoding/Gameception")));
    }

}
