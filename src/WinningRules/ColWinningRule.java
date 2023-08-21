package WinningRules;

import Players.Symbol;
import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColWinningRule implements Rules{
    private Map<Integer,Map<Symbol,Integer>> countmap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!countmap.containsKey(col)){
            countmap.put(col,new HashMap<>());
        }
        Map<Symbol,Integer> colMap = countmap.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol,0);
        }
        colMap.put(symbol, colMap.get(symbol)+1);
        if(colMap.get(symbol) == board.getSize() ){
            return true;
        }
        return false;
    }
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol , Integer> remove = countmap.get(col);
        remove.put(symbol,remove.get(symbol)-1);
    }
}
