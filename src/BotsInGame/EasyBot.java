package BotsInGame;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class EasyBot implements BotPlaying{
    @Override
    public Move makeMove(Board board) {
        for (List<Cell> cells: board.getBoard()) {
            for (Cell cell : cells) {
                if(cell.getCellstate().equals(CellState.EMPTY)){
                    return new Move(cell, null);
                }
                
            }
            
        }

        return null;
    }
}
