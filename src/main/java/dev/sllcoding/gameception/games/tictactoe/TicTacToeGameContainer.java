package dev.sllcoding.gameception.games.tictactoe;

import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicTacToeGameContainer {
    private List<TicTacToeGame> onGoingGames = new ArrayList<>();

    public boolean isPlayerInGame(Player player) {
        return getOngoingGame(player).isPresent();
    }

    public void addGame(TicTacToeGame ticTacToeGame) {
        onGoingGames.add(ticTacToeGame);
    }

    public Optional<TicTacToeGame> getOngoingGame(Player player) {
        return onGoingGames.stream().filter(ticTacToeGame -> ticTacToeGame.fromPlayer(player) != null).findFirst();
    }

    public void removeGame(TicTacToeGame ticTacToeGame) {
        onGoingGames.remove(ticTacToeGame);
    }
}
