package Controller;

import Exceptions.BotMoreThanOne;
import Exceptions.PlayerCount;
import Exceptions.RepeatedSymbol;
import Players.Player;
import WinningRules.Rules;
import models.Game;
import models.GameState;

import java.util.List;

public class GameController {
    public Game StartGame(int dimension, List<Player> players, List<Rules> strategy) throws BotMoreThanOne, RepeatedSymbol, PlayerCount {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setStrategy(strategy)
                .build();
    }

    public GameState checkGameState(Game game){
        return game.getGamestate();
    }
    public void displayGame(Game game){
        game.displayBoard();
    }
     public void makeMove(Game game){
        game.makeMove(game);

     }
     public  void undo(Game game){
        game.undo();
     }
     public Player getWinner(Game game){
        return game.getWinner();
     }
}

// Game controller working as service provider
