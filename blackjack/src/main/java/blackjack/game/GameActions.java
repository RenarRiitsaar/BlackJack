package blackjack.game;

import blackjack.players.Dealer;
import blackjack.players.Player;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.swing.*;

@Getter
public class GameActions {

    private static long playerGameBalance = 0;
    private static int hitCount = 0;
    private static int hitCountDealer =0;
    @Getter
    private static int dealerWinCount = 0;
    @Getter
    private static int playerWinCount = 0;


    public static void withdraw(Player player, Session session){
        Transaction transaction = session.beginTransaction();

        Player user = session.get(Player.class, player.getName());
        int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to transfer to bank:"));

        if(amount<= playerGameBalance) {
            user.setBalance(user.getBalance() + amount);
            playerGameBalance -= amount;
            Frame.getPlayerBalance().setText("Balance: " + playerGameBalance);
            Frame.getPlayerBankBalance().setText("In bank: " + user.getBalance());


        }else if (playerGameBalance < amount || amount < 1){

            JOptionPane.showMessageDialog(null, "Invalid withdraw input!");
        }

        transaction.commit();

    }
    public static void reset(){

        Frame.resetCardImage();

        Frame.getDealer().getHand().clear();
        Frame.getPlayer().getHand().clear();
        DeckBuilder.getDeck().clear();

        Dealer.setGamePot(0);
        Frame.getPot().setText("Pot: " + Dealer.getGamePot());

        Frame.getDealer().setScore(0);
        Frame.getDealerScore().setText("Score:" + Frame.getDealer().getScore());

        Frame.getPlayer().setScore(0);
        Frame.getPlayerScore().setText("Score: " + Frame.getPlayer().getScore());

        Frame.getHit().setEnabled(false);
        Frame.getPlaceBet().setEnabled(true);
        Frame.getDealHand().setEnabled(false);
        Frame.getStand().setEnabled(false);


            hitCount = 0;
            hitCountDealer = 0;
        }

    public static void checkWin(){


            if (Frame.getDealer().getScore() >= Frame.getPlayer().getScore() && Frame.getDealer().getScore() <=21) {
                JOptionPane.showMessageDialog(null, "Dealer wins!");
                Frame.getDealer().setBalance(Frame.getDealer().getBalance() + Dealer.getGamePot());
                Frame.getDealerBalance().setText("Balance: " + Frame.getDealer().getBalance());
                dealerWinCount++;
                Frame.getDealerWinCount().setText("Dealer wins: " + dealerWinCount);

                reset();

            } else {
                JOptionPane.showMessageDialog(null, "Player wins!");
                playerGameBalance += Dealer.getGamePot();
                Frame.getPlayerBalance().setText("Balance: " + playerGameBalance);
                playerWinCount++;
                Frame.getPlayerWinCount().setText("Player wins: " + playerWinCount);
                reset();
        }
    }
    public static void checkBust(){
        if(Frame.getPlayer().getScore() > 21){
            JOptionPane.showMessageDialog(null,"Bust!");
            Frame.getDealer().setBalance(Frame.getDealer().getBalance() + Dealer.getGamePot());
            Frame.getDealerBalance().setText("Balance: " + Frame.getDealer().getBalance());

            dealerWinCount++;
            Frame.getDealerWinCount().setText("Dealer wins: " + dealerWinCount);
            reset();
        }
        if(Frame.getDealer().getScore() > 21){
            JOptionPane.showMessageDialog(null, "Dealer bust!");
           playerGameBalance += Dealer.getGamePot();
            Frame.getPlayerBalance().setText("Balance: " + playerGameBalance);

            playerWinCount++;
            Frame.getPlayerWinCount().setText("Player wins: " + playerWinCount);

          reset();
        }
    }
    public static void stand() {
        Frame.getHit().setEnabled(false);


        Frame.getDealer().dealHand();
        Frame.displayCardImage(Frame.getDealerCard1(), Frame.getDealer().getHand().get(0).getCardTemplate());
        Frame.getDealerCard1().setBounds(375, 230, 75, 125);

        Frame.displayCardImage(Frame.getDealerCard2(), Frame.getDealer().getHand().get(1).getCardTemplate());
        Frame.getDealerCard2().setBounds(425, 230, 75, 125);

        Frame.getDealer().calculateScore();
        Frame.getDealerScore().setText("Score: " + Frame.getDealer().getScore());



        while (Frame.getPlayer().getScore() > Frame.getDealer().getScore()) {
            hitCountDealer++;
            Frame.getDealer().hitCard();



            switch (hitCountDealer) {
                case 1 -> {
                    Frame.displayCardImage(Frame.getDealerCard3(), Frame.getDealer().getHand().get(2).getCardTemplate());
                    Frame.getDealerCard3().setBounds(475, 230, 75, 125);
                    Frame.getDealerCard3().setVisible(true);
                }
                case 2 -> {
                    Frame.displayCardImage(Frame.getDealerCard4(), Frame.getDealer().getHand().get(3).getCardTemplate());
                    Frame.getDealerCard4().setBounds(525, 230, 75, 125);
                    Frame.getDealerCard4().setVisible(true);
                }
                case 3 -> {
                    Frame.displayCardImage(Frame.getDealerCard5(), Frame.getDealer().getHand().get(4).getCardTemplate());
                    Frame.getDealerCard5().setBounds(375, 360, 75, 125);
                    Frame.getDealerCard5().setVisible(true);
                }
                case 4 -> {
                    Frame.displayCardImage(Frame.getDealerCard6(), Frame.getDealer().getHand().get(5).getCardTemplate());
                    Frame.getDealerCard6().setBounds(425, 360, 75, 125);
                    Frame.getDealerCard6().setVisible(true);
                }
                case 5 -> {
                    Frame.displayCardImage(Frame.getDealerCard7(), Frame.getDealer().getHand().get(6).getCardTemplate());
                    Frame.getDealerCard7().setBounds(475, 360, 75, 125);
                    Frame.getDealerCard7().setVisible(true);
                }
                case 6 -> {
                    Frame.displayCardImage(Frame.getDealerCard8(), Frame.getDealer().getHand().get(7).getCardTemplate());
                    Frame.getDealerCard8().setBounds(525, 360, 75, 125);
                    Frame.getDealerCard8().setVisible(true);
                }
            }
            Frame.getDealer().calculateScore();

        }
        Frame.getDealerScore().setText("Score: " + Frame.getDealer().getScore());

        if(Frame.getDealer().getScore() <= 21){
            checkWin();
        } else{
            checkBust();
        }
    }

    public static void deposit(Player player, Session session){


        Transaction transaction = session.beginTransaction();

        Player user = session.get(Player.class,player.getName());



        long amount = Long.parseLong(JOptionPane.showInputDialog("Enter the amount to deposit:"));

         if(amount < 1){
            amount = 0;
            JOptionPane.showMessageDialog(null,"Amount must be bigger than 0!");
        }

          user.setBalance(user.getBalance() - amount);

         transaction.commit();
        Frame.getPlayerBankBalance().setText("In bank: " + user.getBalance());
        Frame.getPlayer().setBalance( playerGameBalance += amount);
        Frame.getPlayerBalance().setText("Balance: " + Frame.getPlayer().getBalance());

    }

    public static void dealHand(){

            DeckBuilder.buildDeck();

            Frame.getPlayer().dealHand();
            Frame.getPlayer().calculateScore();
            Frame.getPlayerScore().setText("Score: " + Frame.getPlayer().getScore());
            Frame.getDealHand().setEnabled(false);



            try{
                Thread.sleep(100);

                Frame.displayCardImage(Frame.getPlayerCard1(), Frame.getPlayer().getHand().get(0).getCardTemplate());
                Frame.getPlayerCard1().setBounds(375,650,75,125);


                Frame.displayCardImage(Frame.getPlayerCard2(), Frame.getPlayer().getHand().get(1).getCardTemplate());
                Frame.getPlayerCard2().setBounds(425,650,75,125);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Something wrong with thread sleep.");
            }

            Frame.getHit().setEnabled(true);
            Frame.getStand().setEnabled(true);

            checkBlackJack();

    }

    public static void hitCard(){
        hitCount++;
        Frame.getPlayer().hitCard();
        Frame.getPlayer().calculateScore();
        Frame.getPlayerScore().setText("Score: " + Frame.getPlayer().getScore());

        switch (hitCount) {
            case 1 -> {
                Frame.displayCardImage(Frame.getPlayerCard3(), Frame.getPlayer().getHand().get(2).getCardTemplate());
                Frame.getPlayerCard3().setBounds(475, 650, 75, 125);
                Frame.getPlayerCard3().setVisible(true);
            }
            case 2 -> {
                Frame.displayCardImage(Frame.getPlayerCard4(), Frame.getPlayer().getHand().get(3).getCardTemplate());
                Frame.getPlayerCard4().setBounds(525, 650, 75, 125);
                Frame.getPlayerCard4().setVisible(true);
            }
            case 3 -> {
                Frame.displayCardImage(Frame.getPlayerCard5(), Frame.getPlayer().getHand().get(4).getCardTemplate());
                Frame.getPlayerCard5().setBounds(375, 780, 75, 125);
                Frame.getPlayerCard5().setVisible(true);
            }
            case 4 -> {
                Frame.displayCardImage(Frame.getPlayerCard6(), Frame.getPlayer().getHand().get(5).getCardTemplate());
                Frame.getPlayerCard6().setBounds(425, 780, 75, 125);
                Frame.getPlayerCard6().setVisible(true);
            }
            case 5 -> {
                Frame.displayCardImage(Frame.getPlayerCard7(), Frame.getPlayer().getHand().get(6).getCardTemplate());
                Frame.getPlayerCard7().setBounds(475, 780, 75, 125);
                Frame.getPlayerCard7().setVisible(true);
            }
            case 6 -> {
                Frame.displayCardImage(Frame.getPlayerCard8(), Frame.getPlayer().getHand().get(7).getCardTemplate());
                Frame.getPlayerCard8().setBounds(525, 780, 75, 125);
                Frame.getPlayerCard8().setVisible(true);
            }
        }
      checkBust();
    }
    private static void checkBlackJack(){
        if(Frame.getPlayer().getHand().size() ==2 && Frame.getPlayer().getScore() == 21){
            JOptionPane.showMessageDialog(null, "BLACKJACK!\nPot multiplied by 2x");

            Frame.getDealer().setBalance(Frame.getDealer().getBalance() - Dealer.getGamePot());
            Frame.getDealerBalance().setText("Balance: " + Frame.getDealer().getBalance());

            Dealer.setGamePot(Dealer.getGamePot() * 2);

            playerGameBalance +=Dealer.getGamePot();
            Frame.getPlayerBalance().setText("Balance: " +playerGameBalance);

            playerWinCount++;
            Frame.getPlayerWinCount().setText("Player wins: " + playerWinCount);


            reset();


        }
    }
    public static void placeBet(){



        if(playerGameBalance != 0){


             long amount = Long.parseLong(JOptionPane.showInputDialog("Enter the bet amount: "));
             if(playerGameBalance < amount){

                 JOptionPane.showMessageDialog(null, "You don't have enough funds!");


             }else if (amount <= 0){
                 JOptionPane.showMessageDialog(null, "Amount must be greater than 0");

             } else if (amount > Frame.getDealer().getBalance()) {
                 JOptionPane.showMessageDialog(null, "Dealer doesn't have enough funds to match the bet!");

             } else if (playerGameBalance >= amount) {

                 Dealer.setGamePot(amount * 2);

                 Frame.getDealer().setBalance(Frame.getDealer().getBalance() - amount);
                 Frame.getDealerBalance().setText("Balance: " + Frame.getDealer().getBalance());

                 playerGameBalance -= amount;
                 Frame.getPlayerBalance().setText("Balance: " + playerGameBalance);

                 Frame.getPot().setText("Pot: " + Dealer.getGamePot());
                 Frame.getPlaceBet().setEnabled(false);

                 Frame.getHit().setEnabled(false);
                 Frame.getDealHand().setEnabled(true);

             }

        }else{
            JOptionPane.showMessageDialog(null, "You don't have enough funds to place bet!");
        }
    }

}
