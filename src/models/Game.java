package models;

import Exceptions.BotMoreThanOne;
import Exceptions.PlayerCount;
import Exceptions.RepeatedSymbol;
import Players.Player;
import Players.PlayerType;
import Players.Symbol;
import WinningRules.Rules;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private List<Rules> strategy;
    private GameState gamestate;
    private Player winner;
    private int nextPlayerIndex;
    private int dimension;
    private Game(int dimension,List<Player> players,List<Rules> strategy) {
         this.dimension = dimension;
         this.players = players;
         this.strategy = strategy;
         this.board = new Board(dimension);
         this.moves = new ArrayList<>();
         this.nextPlayerIndex = 0;
         this.gamestate = GameState.IN_PROGRESS;

    }
    public static Builder getBuilder(){
        return new Builder();
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Rules> getStrategy() {
        return strategy;
    }

    public void setStrategy(List<Rules> strategy) {
        this.strategy = strategy;
    }

    public GameState getGamestate() {
        return gamestate;
    }

    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    private boolean checkWinner(Board board, Move move){
        for (Rules rules: strategy) {
           if(rules.checkWinner(board,move)){
                return  true;
           }
        }
        return  false;
    }



    public static class Builder{
       private List<Player> players;
       private int dimension;
       private List<Rules> strategy;


        // only the required things from the client should be here
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setStrategy(List<Rules> strategy) {
            this.strategy = strategy;
            return this;
        }

        //Validations
        public void validateBot() throws BotMoreThanOne {
            int botsize = 0;
            for (Player player : players) {

                if (PlayerType.BOT.equals(player.getPlayerType())) {
                    botsize++;
                    if (botsize > 1) {
                        throw new BotMoreThanOne("MORE THAN ONE BOT IS NOT ALLOWED");
                    }
                }
                // size should not be zero or negative
            }
        }
        public void validatePlayer() throws PlayerCount {
            if (players.size() != this.dimension - 1) {
                throw new PlayerCount("PLAYER COUNT IS NOT CORRECT");

            }
        }
        public void validateSymbol() throws RepeatedSymbol {
            Map<Character, Integer> Symbolcount = new HashMap<>();        //We can use SET also
            for (Player player : players) {
                if (Symbolcount.containsKey(player.getSymbol().getaChar()) == false) {
                    Symbolcount.put(player.getSymbol().getaChar(), 1);
                } else {
                    throw new RepeatedSymbol("SYMBOL ALREADY TAKEN");
                }

            }
        }
        public void validation() throws RepeatedSymbol, PlayerCount, BotMoreThanOne {
            validateSymbol();
            validatePlayer();
            validateBot();

        }
        public Game build() throws BotMoreThanOne, RepeatedSymbol, PlayerCount {
            validation();
            return new Game(this.dimension,this.players,this.strategy);
        }
    }
    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(row >= board.getSize() || col >= board.getSize()){
            return false;
        }
        if(board.getBoard().get(row).get(col).getCellstate().equals(CellState.FILLED)){
            return false;
        }
        return true;
    }
    public void makeMove(Game game){
        Player currPlayer = getPlayers().get(nextPlayerIndex);
        System.out.println(currPlayer.getName()+"'s make your move");
        Move currPlayerMove = currPlayer.makeMove();
        if(!validateMove(currPlayerMove)){
            System.out.println("Invalid Move. Please try again");
            return;
        }
        int row =currPlayerMove.getCell().getRow();
        int col = currPlayerMove.getCell().getCol();
        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellstate(CellState.FILLED);
        cellToChange.setPlayer(currPlayer);
        Move moveObject = new Move(cellToChange,currPlayer);
        moves.add(moveObject);
        nextPlayerIndex++;
        nextPlayerIndex %= players.size();
        if(checkWinner(board,moveObject)){
            gamestate = GameState.WINNER;
            winner = currPlayer;
        } else if (moves.size() == this.board.getSize()*this.board.getSize()) {
            gamestate = GameState.DRAW;

        }
    }
    public void displayBoard(){
        board.displayBoard();
    }

    public void undo(){
        if(moves.size()==0){
            System.out.println("NO MOVES TO UNDO");
            return;
        }
        Move lastmove = moves.get(moves.size()-1);
        moves.remove(lastmove);
        Cell lastcell = lastmove.getCell();
        lastcell.setPlayer(null);
        lastcell.setCellstate(CellState.EMPTY);

        for (Rules strategy: strategy) {
            strategy.handleUndo(board,lastmove);
        }
        nextPlayerIndex -=1;
        nextPlayerIndex = (nextPlayerIndex+players.size())%players.size();
    }



}
