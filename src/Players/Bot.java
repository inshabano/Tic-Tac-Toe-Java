package Players;

import BotsInGame.BotPlaying;
import BotsInGame.BotPlayingFactory;
import BotsInGame.EasyBot;
import models.Board;
import models.Move;

import java.util.Map;

public class Bot extends Player{
    private BotLevel level;
    private BotPlaying botPlaying;

    public Bot(Long ID, String name, Symbol symbol, PlayerType playerType) {
        super(ID, name, symbol, playerType);
        this.level = BotLevel.EASY;
        this.botPlaying = BotPlayingFactory
                .botplayingstatergyfordifficultylevel(BotLevel.EASY);
    }

    public BotPlaying getBotPlaying() {
        return botPlaying;
    }

    public void setBotPlaying(BotPlaying botPlaying) {
        this.botPlaying = botPlaying;
    }

    public BotLevel getLevel() {
        return level;
    }

    public void setLevel(BotLevel level) {
        this.level = level;
    }
    public Move makeMove(Board board){
        return botPlaying.makeMove(board);

    }
}
