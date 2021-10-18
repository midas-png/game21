package game21;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game21 {
    public static void main(String[] args) {
        Game g = new Game();
        g.initGame();
    }

    static class Game{
        int pTotal = 0;
        int p2Total = 0;
        int passingCount = 0;
        int moveNum = 0;
        ArrayList<Integer> deck = new ArrayList<Integer>();

        ArrayList<Integer> initGame(){
            for(int i=1; i<12; i++){
                deck.add(i);
            }

            startGame(true);
            return deck;
        }

        void getStartCards(int playerNum){
            int playerCard1 = deck.get(new Random().nextInt(10));
            int playerCard2 = deck.get(new Random().nextInt(10));

            System.out.println("Player " + playerNum + " gets first card - " + playerCard1 + " and second - " + playerCard2);
            this.moveNum++;

            if(playerNum == 1) {
                pTotal += playerCard1 + playerCard2;
            }
            else if(playerNum == 2) {
                p2Total += playerCard1 + playerCard2;
            }
            else
                throw new RuntimeException("Wrong player number");
        }

        Object startGame(boolean p1Move){
            Scanner sc = new Scanner(System.in);
            if(this.moveNum == 0) {
                getStartCards(1);
                getStartCards(2);
            }
            System.out.println("PRESS 1 TO GET A CARD\nPRESS 2 TO PASS\n");

            int number = sc.nextInt();

            if(this.moveNum == 10) {
                System.out.println(resultGame());
                return null;
            }

            if(number == 2) {
                this.passingCount++;
                if(this.passingCount == 2)
                    System.out.println(resultGame());
                else
                    startGame(true);

            }
            else if(number == 1) {
                this.passingCount = 0;

                if(p1Move) {
                    int card = deck.get(new Random().nextInt(10));
                    this.pTotal += card;
                    System.out.println(this.pTotal);
                    this.moveNum++;
                    startGame(false);
                }
                else if(!p1Move) {
                    this.p2Total += deck.get(new Random().nextInt(10));
                    System.out.println(this.p2Total);
                    this.moveNum++;
                    startGame(true);
                }
            }
            return new RuntimeException("Unknown error");
        }

        String resultGame(){
            if (this.pTotal > 21 && this.p2Total > 21)
                if(this.pTotal > this.p2Total)
                    return "Player 2 won " + this.pTotal + ":" + this.p2Total;
                else
                    return "Player 1 won " + this.pTotal + ":" + this.p2Total;
            else if (this.pTotal > this.p2Total)
                if(this.pTotal == 21)
                    return "Player 1 won " + this.pTotal + ":" + this.p2Total;
                else if(this.pTotal > 21 && this.p2Total <= 21)
                    return "Player 2 won " + this.pTotal + ":" + this.p2Total;
                else
                    return "Player 1 won " + this.pTotal + ":" + this.p2Total;
            else if (this.pTotal < this.p2Total)
                if(this.p2Total == 21)
                    return "Player 2 won " + this.pTotal + ":" + this.p2Total;
                else if(this.p2Total > 21 && this.pTotal <= 21)
                    return "Player 1 won " + this.pTotal + ":" + this.p2Total;
                else
                    return "Player 2 won " + this.pTotal + ":" + this.p2Total;
            else if (this.pTotal == this.p2Total)
                return "It's a tie " + this.pTotal + ":" + this.p2Total;

            return null;
        }

    }
}