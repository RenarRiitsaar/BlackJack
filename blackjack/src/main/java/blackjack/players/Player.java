package blackjack.players;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "blackjack")
public class Player extends Participants{


public Player(){

}
    public Player(String name, long balance) {
        super(name, balance);

    }
    }





