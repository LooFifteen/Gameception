package dev.sllcoding.gameception.games.minesweeper.endings;

import dev.sllcoding.gameception.games.framework.endings.Ending;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;

public class PlayerWonEnding implements Ending {
    @Override
    public Title getTitle() {
        return Title.title(Component.text("You have won the game!", NamedTextColor.GREEN), Component.empty());
    }

    @Override
    public void execute() {
    }
}
