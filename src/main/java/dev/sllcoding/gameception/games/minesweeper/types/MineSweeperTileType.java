package dev.sllcoding.gameception.games.minesweeper.types;

public enum MineSweeperTileType {
    Unknown("unknown.png"),
    Empty("empty.png"),
    One("1.png"),
    Two("2.png"),
    Three("3.png"),
    Four("4.png"),
    Five("5.png"),
    Six("6.png"),
    Seven("7.png"),
    Eight("8.png"),
    Mine("mine.png"),
    Flagged("flagged.png");

    private final String path;

    MineSweeperTileType(String filename) {
        this.path = "assets/minesweeper/" + filename;
    }

    public String getPath() {
        return path;
    }
}