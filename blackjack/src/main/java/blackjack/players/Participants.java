package blackjack.players;

import blackjack.game.DeckBuilder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;



@Getter @Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class Participants {

    @Transient
    Random random = new Random();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    @Transient
    private int score;
    @Column(name = "Balance")
    private long balance;
    @Transient
    private List<DeckBuilder> hand = new ArrayList<>();
    @Transient
    private List<Integer> calculated = new ArrayList<>();
    @Transient
    private int numOfAces;
    @Transient
    private List<Integer> aces;


    Participants(String name, long balance) {
        this.name = name;
        this.balance = balance;

    }

    public void dealHand() {

        int cardCount = 0;
        for (int i = 0; i < DeckBuilder.getDeck().size(); i++) {
            cardCount++;
        }

        hand.add(DeckBuilder.getDeck().get(random.nextInt(cardCount)));
        hand.add(DeckBuilder.getDeck().get(random.nextInt(cardCount)));

        DeckBuilder.getDeck().removeAll(hand);
    }

    public void hitCard() {
        int cardCount = 0;
        for (int i = 0; i < DeckBuilder.getDeck().size(); i++) {
            cardCount++;
        }
        hand.add(DeckBuilder.getDeck().get(random.nextInt(cardCount)));
        DeckBuilder.getDeck().removeAll(hand);

    }

    public void calculateScore() {

        setScore(0);

        numOfAces = 0;
        aces = new ArrayList<>();

        for (int i = 0; i < hand.size(); i++) {

            if (Objects.equals(hand.get(i).getCardID(), "A")) {
                aces.add(i);
                numOfAces++;

            } else {
                score += hand.get(i).getValue();
            }
        }
            for (int j = 0; j < numOfAces; j++) {

                    if (score + aces.get(j) > 21) {
                        aces.set(j, 1);

                    }else{
                        aces.set(j,11);
                        if(score + aces.get(j) > 21){
                            aces.set(j,1);
                        }
                    }
                score += aces.get(j);


            }
        }
    }








