package models;

import Players.Player;

public class Cell {
    private CellState cellstate;
    private Player player;
    private int row;
    private int col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Cell(int row, int col) {
         this.row = row;
         this.col = col;
         this.cellstate = CellState.EMPTY;
    }

    public CellState getCellstate() {
        return cellstate;
    }

    public void setCellstate(CellState cellstate) {
        this.cellstate = cellstate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void display(){
        if(getCellstate() == CellState.EMPTY){
            System.out.print("|   |");
        }
        else{
            System.out.print("| "+ player.getSymbol().getaChar()+" |");
        }

    }
}
