package Players;

import models.Board;
import models.Cell;
import models.Move;

import java.util.Scanner;

public class Player {
    private Long ID;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long ID, String name, Symbol symbol, PlayerType playerType) {
        this.ID = ID;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(){
        System.out.println("Enter the row");
        int row = scanner.nextInt();
        System.out.println("Enter the column");
        int col = scanner.nextInt();
        return new Move(new Cell(row,col),this);
    }
}
