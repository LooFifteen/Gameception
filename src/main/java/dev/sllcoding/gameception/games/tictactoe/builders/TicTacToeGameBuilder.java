package dev.sllcoding.gameception.games.tictactoe.builders;

import dev.sllcoding.gameception.games.framework.GameObject;
import dev.sllcoding.gameception.games.framework.GamePlayer;
import dev.sllcoding.gameception.games.framework.Team;
import dev.sllcoding.gameception.games.framework.conditions.WinCondition;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeBoard;
import dev.sllcoding.gameception.games.tictactoe.TicTacToeGame;
import dev.sllcoding.gameception.games.tictactoe.TicTacToePlayer;
import dev.sllcoding.gameception.games.tictactoe.conditions.DiagonalConditionTicTacToe;
import dev.sllcoding.gameception.games.tictactoe.conditions.HorizontalConditionTicTacToe;
import dev.sllcoding.gameception.games.tictactoe.conditions.VerticalConditionTicTacToe;
import dev.sllcoding.gameception.games.tictactoe.teams.CircleTeam;
import dev.sllcoding.gameception.games.tictactoe.teams.CrossTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeGameBuilder {
    private TicTacToeBoard ticTacToeBoard;
    private final List<GamePlayer> ticTacToePlayerList;

    public TicTacToeGameBuilder() {
        this.ticTacToePlayerList = new ArrayList<>();
    }

    public TicTacToeGameBuilder withBoard(TicTacToeBoard ticTacToeBoard) {
        this.ticTacToeBoard = ticTacToeBoard;
        return this;
    }

    public TicTacToeGameBuilder addPlayer(TicTacToePlayer ticTacToePlayer) {
        ticTacToePlayerList.add(ticTacToePlayer);
        return this;
    }

    public TicTacToeGame build() {
        List<WinCondition> ticTacToeWinConditionBases = Arrays.asList(
            new DiagonalConditionTicTacToe(ticTacToeBoard),
            new HorizontalConditionTicTacToe(ticTacToeBoard),
            new VerticalConditionTicTacToe(ticTacToeBoard)
        );

        List<GameObject> gameObjects = Arrays.asList(

        );

        Team[] progression = new Team[] {
            new CrossTeam(),
            new CircleTeam()
        };

        return new TicTacToeGame(ticTacToeBoard,
                ticTacToePlayerList,
                ticTacToeWinConditionBases,
                gameObjects,
                progression);
    }
}
