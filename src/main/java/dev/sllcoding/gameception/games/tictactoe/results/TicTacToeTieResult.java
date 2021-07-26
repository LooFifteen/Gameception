package dev.sllcoding.gameception.games.tictactoe.results;

import dev.sllcoding.gameception.games.framework.v1.Game;
import dev.sllcoding.gameception.games.framework.v1.GameContainer;
import dev.sllcoding.gameception.games.framework.v1.GamePlayer;
import dev.sllcoding.gameception.games.framework.v1.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;

import java.util.List;

public class TicTacToeTieResult extends TicTacToeBaseResult {
    public TicTacToeTieResult(GameContainer gameContainer) {
        super(gameContainer);
    }

    @Override
    public Team getWinningTeam() {
        return null;
    }

    @Override
    public void execute(Game game) {
        super.execute(game);

        List<GamePlayer> players = game.getPlayers();

        Title title = Title.title(Component.text("Wowzers!", NamedTextColor.YELLOW),
                Component.text("You tied in tic-tac-toe", NamedTextColor.YELLOW));

        for (GamePlayer player : players) {
            player.getPlayer().showTitle(title);
        }
    }
}

