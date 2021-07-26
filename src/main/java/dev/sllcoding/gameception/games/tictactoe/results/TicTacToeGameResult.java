package dev.sllcoding.gameception.games.tictactoe.results;

import dev.sllcoding.gameception.games.framework.v1.Game;
import dev.sllcoding.gameception.games.framework.v1.GameContainer;
import dev.sllcoding.gameception.games.framework.v1.GamePlayer;
import dev.sllcoding.gameception.games.framework.v1.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;

import java.util.List;

public class TicTacToeGameResult extends TicTacToeBaseResult {
    private final Team winningTeam;

    public TicTacToeGameResult(Team winningTeam, GameContainer gameContainer) {
        super(gameContainer);
        this.winningTeam = winningTeam;
    }

    @Override
    public Team getWinningTeam() {
        return winningTeam;
    }

    @Override
    public void execute(Game game) {
        super.execute(game);

        Team winningTeam = getWinningTeam();
        List<GamePlayer> players = game.getPlayers();

        Title win = Title.title(Component.text("Congratulations!", NamedTextColor.GREEN),
                Component.text("You won in tic-tac-toe", NamedTextColor.GREEN));

        Title lose = Title.title(Component.text("Oopsie doopsie!", NamedTextColor.RED),
                Component.text("You lost in tic-tac-toe. Imagine sucking :(", NamedTextColor.RED));

        for (GamePlayer player : players) {
            if (player.getTeam().equals(winningTeam)) {
                player.getPlayer().showTitle(win);
            } else {
                player.getPlayer().showTitle(lose);
            }
        }
    }
}
