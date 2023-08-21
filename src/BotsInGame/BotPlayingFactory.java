package BotsInGame;

import Players.BotLevel;

public class BotPlayingFactory {
    public static BotPlaying botplayingstatergyfordifficultylevel(BotLevel botLevel){
        return new EasyBot();
    }
}
