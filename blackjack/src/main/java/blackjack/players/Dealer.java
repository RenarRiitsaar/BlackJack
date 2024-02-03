package blackjack.players;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Dealer extends Participants{

    @Getter @Setter
    private static long gamePot = 0;

    public Dealer(String name, long balance) {
        super(name, balance);


    }


    }

