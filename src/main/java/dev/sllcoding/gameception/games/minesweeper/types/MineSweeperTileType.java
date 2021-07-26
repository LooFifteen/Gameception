package dev.sllcoding.gameception.games.minesweeper.types;

public enum MineSweeperTileType {
    Empty("empty.png"),
    One("1.png"),
    Two("2.png"),
    Three("3.png"),
    Mine("mine.png");

    private final String path;

    MineSweeperTileType(String filename) {
        this.path = "assets/minesweeper/" + filename;
    }

    public String getPath() {
        return path;
    }
}