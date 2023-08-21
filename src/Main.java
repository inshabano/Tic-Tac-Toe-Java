import BotsInGame.BotPlaying;
import BotsInGame.EasyBot;
import Controller.GameController;
import Players.*;
import WinningRules.ColWinningRule;
import WinningRules.DiagWinningRule;
import WinningRules.RowWinningRule;
import WinningRules.Rules;
import models.Game;
import models.GameState;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimension");
        int dimension = sc.nextInt();
        //ADDING PLAYERS
        List<Player> players = new ArrayList<>();
        System.out.println("Player 1 enter your ID , Name and preferred symbol");
        Long p1id = sc.nextLong();
        String p1name = sc.next();
        Character p1symbol =sc.next().charAt(0);
        players.add(
                new Player(p1id,p1name,new Symbol(p1symbol), PlayerType.HUMAN));
//        System.out.println("NEXT player - Bot / Person");
//        if(nextPlyr.equals("Bot")){
//            players.add(
//                    new Bot(01L,"BOT",new Symbol('*'),PlayerType.BOT)
//                            );
//        }

            System.out.println("Player 2 enter your ID , Name and preferred symbol");
            Long p2id = sc.nextLong();
            String p2name = sc.next();
            Character p2symbol = sc.next().charAt(0);


            players.add(
                    new Player(p2id, p2name, new Symbol(p2symbol), PlayerType.HUMAN));



        //ADDING GAME RULES
        List<Rules> winningStrategy = new ArrayList<>();
        System.out.println("HOW TO WIN THE GAME-");
        System.out.println("1 BY Completing a row ");
        System.out.println("2 BY Completing a column");
        System.out.println("3 BY Completing a diagonal");
        System.out.println("Chose your own rules from above mentioned:");
        System.out.println("Please enter how many rules you would like to have");
        int n = sc.nextInt();
        System.out.println("now enter which are rules you would have 1 , 2 , 3");
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            if (t == 1) {
                winningStrategy.add(new RowWinningRule());
            } else if (t == 2) {
                winningStrategy.add(new ColWinningRule());

            } else if (t == 3) {
               winningStrategy.add(new DiagWinningRule());
            }
        }
        // START THE GAME
        Game game = null;
        try {
             game = gameController.StartGame(
                    dimension,
                    players,
                    winningStrategy
            );
            while(gameController.checkGameState(game).equals(GameState.IN_PROGRESS)){
                gameController.displayGame(game);
                gameController.makeMove(game);
                gameController.displayGame(game);
                System.out.println("do you want to undo your move yes/no");
                String ask = sc.next();
                if(ask.equals("yes")){
                    gameController.undo(game);

                }

            }
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
        gameController.displayGame(game);
        System.out.println("GAME OVER!!!");
        GameState gameState = gameController.checkGameState(game);
        if(gameState.equals(GameState.WINNER)){
            System.out.println(gameController.getWinner(game)+" is the winner");
        }
        else if(gameState.equals(GameState.DRAW)){
            System.out.println("GAME DRAW !");
        }

    }
}