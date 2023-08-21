package models;

import Players.Player;
import Players.Symbol;

public class Move {
    private Player player;
    private Cell cell;
    private CellState cellstate;

    public Move( Cell cell,Player player) {
        this.player = player;
        this.cell = cell;
        this.cellstate = CellState.FILLED;


    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public CellState getCellstate() {
        return cellstate;
    }

    public void setCellstate(CellState cellstate) {
        this.cellstate = cellstate;
    }
}
//Move played by players
