package dev.sllcoding.gameception.games.tictactoe.results;

import dev.sllcoding.gameception.games.framework.Game;
import dev.sllcoding.gameception.games.framework.GameContainer;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.Team;
import dev.sllcoding.gameception.games.framework.results.GameResult;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.minestom.server.entity.Player;

import java.util.List;

public class TicTacToeGameResult implements GameResult {
    private final Team winningTeam;
    private final GameContainer gameContainer;

    public TicTacToeGameResult(Team winningTeam, GameContainer gameContainer) {
        this.winningTeam = winningTeam;
        this.gameContainer = gameContainer;
    }

    @Override
    public Team getWinningTeam() {
        return winningTeam;
    }

    @Override
    public void execute(Game game) {
        game.end();
        gameContainer.removeGame(game);

        Team winningTeam = getWinningTeam();
        List<GamePlayer> players = game.getPlayers();

        Title win = Title.title(Component.text("Congratulations!", NamedTextColor.GREEN),
                Component.text("You won in tic-tac-toe", NamedTextColor.GREEN));

        Title lose = Title.title(Component.text("Oopsie doopsie!", NamedTextColor.RED),
                Component.text("You lost in tic-tac-toe", NamedTextColor.RED));

        for (GamePlayer player : players) {
            if (player.getTeam().equals(winningTeam)) {
                player.getPlayer().showTitle(win);
            } else {
                player.getPlayer().showTitle(lose);
            }
        }
    }
}
