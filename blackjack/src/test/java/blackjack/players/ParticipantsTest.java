package blackjack.players;

import blackjack.game.DeckBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {

    @Test
    void calculateScore() {
        DeckBuilder.buildDeck();
        Player player = new Player("Player",1);

        player.getHand().add(DeckBuilder.getDeck().get(51));
        player.getHand().add(DeckBuilder.getDeck().get(26));
        player.getHand().add(DeckBuilder.getDeck().get(51));
        player.getHand().add(DeckBuilder.getDeck().get(26));
        player.getHand().add(DeckBuilder.getDeck().get(51));


        player.calculateScore();

        int expectedScore = 19;
        int actualScore = player.getScore();

        assertEquals(expectedScore,actualScore);
    }
}