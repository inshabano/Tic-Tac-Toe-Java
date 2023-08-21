package WinningRules;

import Players.Symbol;
import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagWinningRule implements Rules{
    private Map<Symbol,Integer> rightdiag = new HashMap<>();
    private Map<Symbol,Integer> leftdiag = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row == col) {
            if (!leftdiag.containsKey(symbol)) {
                leftdiag.put(symbol, 0);
            }
            leftdiag.put(symbol, leftdiag.get(symbol) + 1);
            if (leftdiag.get(symbol) == board.getSize()) {
                return true;
            }
        }
        if(row == (board.getSize()-row-1)){
            if(!rightdiag.containsKey(symbol)){
                rightdiag.put(symbol,0);
            }
            rightdiag.put(symbol,rightdiag.get(symbol)+1);
            if(rightdiag.get(symbol) == board.getSize()){
                return true;
            }
        }
        return false;
    }
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row == col) {
            leftdiag.put(symbol,
                    leftdiag.get(symbol) - 1);
        }
        else if(row + col == board.getSize()-1){
            rightdiag.put(symbol,
                    rightdiag.get(symbol) - 1);
        }
    }
}
