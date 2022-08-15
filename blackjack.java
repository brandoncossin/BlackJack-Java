/*
  Brandon Cossin, Shayne Kanner
  8/11/2022
  Card Game For PWC JAVA Training
*/
import java.util.*;

public class blackjack {
    public static void main(String[] args) {
        boolean playing = false;
        Scanner play = new Scanner(System.in);
        System.out.println("Do you want to play 'y'/'n'");
        String answer = play.nextLine();
        if (answer.equals("y")) {
            playing = true;
        }
        while (playing) {
            playBlackjack();
            Scanner again = new Scanner(System.in);
            System.out.println("Play Again?");
            String nextAnswer = again.nextLine();
            if (nextAnswer.equals("n")) {
                playing = false;
                System.out.println("done");
            }
        }
    }
public static void playBlackjack(){
        boolean playing = false;
        Deck deck = new Deck();
        Player player = new Player("Player");
        Player dealer = new Player("Dealer");
        System.out.println("Welcome to blackjack player");
        player.addCard(deck.arr[0]);
        player.addCard(deck.arr[1]);
        dealer.addCard(deck.arr[2]);
        dealer.addCard(deck.arr[3]);
        int i = 4;
        System.out.println("player has: " + player.drawnCards + "with a value of: "+ player.value);
        System.out.println("dealer value: " + dealer.value);
        Scanner play = new Scanner(System.in);
        System.out.println("Draw again?");
        String answer = play.nextLine();
        if (answer.equals("y")) {
            playing = true;
        }
        else{
            while (dealer.value < 17) {
                        dealer.addCard(deck.arr[i]);
                        ++i;
                        System.out.println("dealer value: " + dealer.value);
                     }
            if(dealer.value > player.value && dealer.value < 22){
                System.out.println("dealer won");
            }
            else if (player.value > dealer.value && player.value < 22) {
                System.out.println("player won");
            } else {
                System.out.println("Draw");
            }
            System.out.println();
        }
        while (playing) {
            player.addCard(deck.arr[i]);
            ++i;
            if (player.getBusted()) {
                playing = false;
                System.out.println("Busted");
                System.out.println("player has: " + player.drawnCards + "with a value of: "+ player.value);
                System.out.println("Game over");
            }
        if(playing){
                System.out.println("player has: " + player.drawnCards + "with a value of: "+ player.value);
                System.out.println("dealer value: " + dealer.value);
                Scanner again = new Scanner(System.in);
                System.out.println("Draw Again?");
                String nextAnswer = again.nextLine();
                if (nextAnswer.equals("n")) {
                    while (dealer.value < 17) {
                        dealer.addCard(deck.arr[i]);
                        ++i;
                        System.out.println("dealer value: " + dealer.value);
                     }
                    playing = false;
                }
            }
        if(!playing){
            if(dealer.value > player.value && dealer.value < 22){
                System.out.println("dealer won");
            }
            else if (player.value > dealer.value && player.value < 22) {
                System.out.println("player won");
            } else {
                System.out.println("Draw");
            }
            System.out.println();
            }
        }

    }
}
// Card class for individual cards
class Card {
    int value;
    String suit;
    String strValue;
    public Card(int x, String y, String z){
        value = x;
        suit = y;
        strValue = z;
    }
    public String getSuit(){
        return suit;
    }
    public String getStrvalue(){
        return strValue;
    }
    public String toString(){
        return strValue + " of " + suit;
    }
}
// Deck class for list of cards
class Deck {
    int size;
    Card[] arr = new Card[52];
    public Deck() {  
        for(int i = 0; i< 52; i++){
            String suit = "";
            String strValue ="";
            int suitInt = i % 4;
            switch(suitInt){
            case 0:
                suit = "Hearts";
                break;
            case 1:
                suit = "Diamonds";
                break;
            case 2:
                suit = "Clubs";
                break;
            case 3:
                suit = "Spades";
                break;
            }
            int rank = (i % 12) + 1;
            switch(rank){
            case 1:
                strValue = "Ace";
                break;
            case 2:
                strValue = "2";
                break;
            case 3:
                strValue = "3";
                break;
            case 4:
                strValue = "4";
                break;
            case 5:
                strValue = "5";
                break;
            case 6:
                strValue = "6";
                break;
            case 7:
                strValue = "7";
                break;
            case 8:
                strValue = "8";
                break;
            case 9:
                strValue = "9";
                break;
            case 10:
                strValue = "10";
                break;
            case 11:
                strValue = "Joker";
                break;
            case 12:
                strValue = "Queen";
                break;
            case 13:
                strValue = "King";
                break;
            }
            arr[i] = new Card(rank, suit, strValue);
            
        }
        
        Collections.shuffle(Arrays.asList(arr));
    }
}
class Player {
    String playername = "";
    public Player(String x) {
        playername = x;
    } 
    public Boolean getBusted(){
        return busted;
    }
    int value;
    Boolean busted = false;
    ArrayList<Card> drawnCards = new ArrayList<>();
    void addCard(Card x){
        drawnCards.add(x);
        switch (x.value){
        case 1:
            if(value <= 10){
                x.value = 11;
            }
            else{
                x.value = 1;
            }
            break;
        case 11:
            x.value = 10;
            break;
        case 12:
            x.value = 10;
            break;
        case 13:
            x.value = 10;
            break;
        }
        for (int i = 0; i < drawnCards.size(); i++) {
            //System.out.println((drawnCards.get(i)).value);
            // forces Ace to be 1 and no longer an 11 ace
            if((drawnCards.get(i)).getStrvalue() == "Ace"){
                if((value + x.value) > 21){
                        value = value - 10;
                        drawnCards.get(i).strValue = "1";
                    }
            }
        }
        System.out.println(playername + " drew " + x.strValue + " of " + x.suit);
        value += x.value;
        if(value >21){
            busted = true;
        }
    }
}
