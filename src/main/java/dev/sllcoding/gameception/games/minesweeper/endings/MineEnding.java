package dev.sllcoding.gameception.games.minesweeper.endings;

import dev.sllcoding.gameception.games.framework.endings.Ending;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;

public class MineEnding implements Ending {
    @Override
    public Title getTitle() {
        return Title.title(Component.text("Game over!", NamedTextColor.RED),
                Component.text("You did a thing i guess", NamedTextColor.RED));
    }

    @Override
    public void execute() {
        // TODO: do something with this.

    }
}
