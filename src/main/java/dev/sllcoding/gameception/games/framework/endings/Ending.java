package dev.sllcoding.gameception.games.framework.endings;

import net.kyori.adventure.title.Title;

// This only really works for singleplayer
// TODO: use a different ending interface for multiplayer.
public interface Ending {
    Title getTitle();
    void execute();
}
