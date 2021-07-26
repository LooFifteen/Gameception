package dev.sllcoding.gameception.games.tictactoe;

import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.Team;
import net.minestom.server.entity.Player;

public class TicTacToePlayer implements GamePlayer {
    private final Player player;
    private final Team team;

    public TicTacToePlayer(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
