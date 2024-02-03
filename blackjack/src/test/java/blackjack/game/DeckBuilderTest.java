package blackjack.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckBuilderTest {

    @Test
    void buildDeck() {
        DeckBuilder.buildDeck();

        int ExpectedCardsInDeck = 52;
        int ActualCardsInDeck = DeckBuilder.getDeck().size();

        assertEquals(ExpectedCardsInDeck, ActualCardsInDeck);

    }
}