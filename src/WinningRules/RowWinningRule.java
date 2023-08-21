package WinningRules;

import Players.Player;
import Players.Symbol;
import models.Board;
import models.Cell;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningRule implements Rules{
    private Map<Integer,Map<Symbol,Integer>> countmap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
       int row = move.getCell().getRow();
       Symbol symbol = move.getPlayer().getSymbol();
        if(!countmap.containsKey(row)){
            countmap.put(row,new HashMap<>());
        }
        Map<Symbol,Integer> rowMap = countmap.get(row);
        if(!rowMap.containsKey(symbol)){
            rowMap.put(symbol,0);
        }
        rowMap.put(symbol, rowMap.get(symbol)+1);
        if(rowMap.get(symbol) == board.getSize() ){
        return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol , Integer> remove = countmap.get(row);
        remove.put(symbol,remove.get(symbol)-1);
    }
}
