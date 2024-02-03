package blackjack.game;

import blackjack.players.Dealer;
import blackjack.players.Participants;
import blackjack.players.Player;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame implements ActionListener {


    private static long userBankBalance;
    private final JButton deposit;
    @Getter
    private static Player player = new Player("Player",0);
    @Getter
    private static JLabel playerBalance;
    @Getter
    private static JLabel playerBankBalance;
    @Getter
    private static final JLabel playerScore = new JLabel();
    @Getter
    private static final JButton dealHand = new JButton();
    @Getter
    private static final JPanel playerCard1 = new JPanel();
    @Getter
    private static final JPanel playerCard2 = new JPanel();
    @Getter
    private static final JPanel playerCard3 = new JPanel();
    @Getter
    private static final JPanel playerCard4 = new JPanel();
    @Getter
    private static final JPanel playerCard5 = new JPanel();
    @Getter
    private static final JPanel playerCard6 = new JPanel();
    @Getter
    private static final JPanel playerCard7 = new JPanel();
    @Getter
    private static final JPanel playerCard8 = new JPanel();
    @Getter
    private static final JButton hit = new JButton();
    @Getter
    private static final JButton placeBet = new JButton();
    @Getter
    private static final Dealer dealer = new Dealer("Dealer",1000000);
    @Getter
    private static final JLabel dealerBalance = new JLabel();
    @Getter
    private static final JLabel pot = new JLabel();
    @Getter
    private static final JButton stand = new JButton();
    @Getter
    private static final JLabel dealerScore = new JLabel();
    @Getter
    private static final JPanel dealerCard8 = new JPanel();
    @Getter
    private static final JPanel dealerCard7 = new JPanel();
    @Getter
    private static final JPanel dealerCard6 = new JPanel();
    @Getter
    private static final JPanel dealerCard5 = new JPanel();
    @Getter
    private static final JPanel dealerCard4 = new JPanel();
    @Getter
    private static final JPanel dealerCard3 = new JPanel();
    @Getter
    private static final JPanel dealerCard2 = new JPanel();
    @Getter
    private static final JPanel dealerCard1 = new JPanel();
    @Getter
    private static final JButton withdraw = new JButton();
    @Getter
    private static JLabel dealerWinCount = new JLabel();
    @Getter
    private static JLabel playerWinCount = new JLabel();

    Frame() {

        getBankBalance(player);

        JLabel label = new JLabel();
        label.setText("BlackJack");
        label.setFont(new Font("Old English Text MT",Font.BOLD,50));
        label.setBounds(376,50,300,50);

        JLabel dealerLabel = new JLabel();
        dealerLabel.setText("Dealer");
        dealerLabel.setFont(new Font("Old English Text MT",Font.BOLD,30));
        dealerLabel.setBounds(450,175,300,50);

        dealerScore.setText("Score: " + dealer.getScore());
        dealerScore.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        dealerScore.setBounds(650, 250, 300,50);

        dealerBalance.setText("Balance: " + dealer.getBalance());
        dealerBalance.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        dealerBalance.setBounds(650, 300, 300,50);

        dealerWinCount.setText("Dealer wins: " + GameActions.getDealerWinCount());
        dealerWinCount.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        dealerWinCount.setBounds(75, 250, 300,50);

        pot.setText("Pot: " + Dealer.getGamePot());
        pot.setBounds(400,530,300,50);
        pot.setFont(new Font("Old English Text MT",Font.BOLD,30));

        JLabel playerLabel = new JLabel();
        playerLabel.setText("Player");
        playerLabel.setFont(new Font("Old English Text MT",Font.BOLD,30));
        playerLabel.setBounds(450,595,300,50);

        playerScore.setText("Score: " + player.getScore());
        playerScore.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        playerScore.setBounds(650, 670, 300,50);

        playerBankBalance = new JLabel();
        playerBankBalance.setText("In bank: " + userBankBalance);
        playerBankBalance.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        playerBankBalance.setBounds(650, 770, 300,50);

        playerBalance = new JLabel();
        playerBalance.setText("Balance: " + player.getBalance());
        playerBalance.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        playerBalance.setBounds(650, 720, 300,50);

        playerWinCount.setText("Player wins: " + GameActions.getPlayerWinCount());
        playerWinCount.setFont(new Font("Old English Text MT", Font.BOLD, 30));
        playerWinCount.setBounds(75, 300, 300,50);


        dealHand.setBounds(250,700,100,50);
        dealHand.setText("Deal hand");
        dealHand.addActionListener(this);
        dealHand.setEnabled(false);


        hit.setBounds(250,755,100,50);
        hit.setText("Hit");
        hit.setEnabled(false);
        hit.addActionListener(this);


        stand.setBounds(250,810,100,50);
        stand.setText("Stand");
        stand.setEnabled(false);
        stand.addActionListener(this);

        deposit = new JButton();
        deposit.setBounds(755,810,100,50);
        deposit.setText("Deposit");
        deposit.addActionListener(this);


        placeBet.setBounds(650,810,100,50);
        placeBet.setText("Place bet");
        placeBet.addActionListener(this);


        withdraw.setBounds(860, 810, 100, 50);
        withdraw.setText("Withdraw");
        withdraw.addActionListener(this);

        JFrame frame = new JFrame();

        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);


        frame.add(playerWinCount);
        frame.add(dealerWinCount);
        frame.add(playerBankBalance);
        frame.add(placeBet);
        frame.add(withdraw);
        frame.add(pot);
        frame.add(deposit);
        frame.add(stand);
        frame.add(hit);
        frame.add(dealHand);
        frame.add(label);

        frame.add(playerBalance);
        frame.add(playerScore);
        frame.add(playerLabel);
        frame.add(dealerLabel);
        frame.add(playerCard8);
        frame.add(playerCard7);
        frame.add(playerCard6);
        frame.add(playerCard5);
        frame.add(playerCard4);
        frame.add(playerCard3);
        frame.add(playerCard2);
        frame.add(playerCard1);

        frame.add(dealerBalance);
        frame.add(dealerScore);
        frame.add(dealerCard8);
        frame.add(dealerCard7);
        frame.add(dealerCard6);
        frame.add(dealerCard5);
        frame.add(dealerCard4);
        frame.add(dealerCard3);
        frame.add(dealerCard2);
        frame.add(dealerCard1);


    }
    public static void getBankBalance(Player player){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Participants.class).buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Player user = session.get(Player.class, player.getName());

            userBankBalance = user.getBalance();



        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Something went wrong with bank balance retrieval");
        }
    }

    public static void displayCardImage(JPanel jp, ImageIcon imageIcon) {
       JLabel jl = new JLabel();
        jl.setIcon(imageIcon);
        imageIcon.getImage().getScaledInstance(75,125,java.awt.Image.SCALE_SMOOTH);
        jp.add(jl);
    }
    public static void resetCardImage(){


        playerCard1.removeAll();
        playerCard1.revalidate();
        playerCard1.repaint();

        playerCard2.removeAll();
        playerCard2.revalidate();
        playerCard2.repaint();

        playerCard3.removeAll();
        playerCard3.revalidate();
        playerCard3.repaint();

        playerCard4.removeAll();
        playerCard4.revalidate();
        playerCard4.repaint();

        playerCard5.removeAll();
        playerCard5.revalidate();
        playerCard5.repaint();

        playerCard6.removeAll();
        playerCard6.revalidate();
        playerCard6.repaint();

        playerCard7.removeAll();
        playerCard7.revalidate();
        playerCard7.repaint();

        playerCard8.removeAll();
        playerCard8.revalidate();
        playerCard8.repaint();



        dealerCard1.removeAll();
        dealerCard1.revalidate();
        dealerCard1.repaint();

        dealerCard2.removeAll();
        dealerCard2.revalidate();
        dealerCard2.repaint();

        dealerCard3.removeAll();
        dealerCard3.revalidate();
        dealerCard3.repaint();

        dealerCard4.removeAll();
        dealerCard4.revalidate();
        dealerCard4.repaint();

        dealerCard5.removeAll();
        dealerCard5.revalidate();
        dealerCard5.repaint();

        dealerCard6.removeAll();
        dealerCard6.revalidate();
        dealerCard6.repaint();

        dealerCard7.removeAll();
        dealerCard7.revalidate();
        dealerCard7.repaint();

        dealerCard8.removeAll();
        dealerCard8.revalidate();
        dealerCard8.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Participants.class).buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {


            if (e.getSource() == deposit) {
                GameActions.deposit(player, session);
            }

            if (e.getSource() == dealHand) {
                GameActions.dealHand();
            }
            if (e.getSource() == hit) {

                GameActions.hitCard();

            }
            if (e.getSource() == placeBet) {
                GameActions.placeBet();
            }
            if (e.getSource() == stand) {
                GameActions.stand();

            }
            if (e.getSource() == withdraw) {
                GameActions.withdraw(player,session);

            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Something went wrong with session factory");
        }
    }
    }


