package dev.sllcoding.gameception.games.framework.endings;

import net.kyori.adventure.title.Title;

public interface Ending {
    Title getTitle();
    void execute();
}
