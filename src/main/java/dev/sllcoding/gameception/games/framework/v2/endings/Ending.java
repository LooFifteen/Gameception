package dev.sllcoding.gameception.games.framework.v2.endings;

import net.kyori.adventure.title.Title;

public interface Ending {
    Title getTitle();
    void execute();
}
