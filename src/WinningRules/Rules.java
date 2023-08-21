package WinningRules;

import models.Board;
import models.Move;

public interface Rules {
    public boolean checkWinner(Board board , Move move);
    public void handleUndo(Board board , Move move);
}
