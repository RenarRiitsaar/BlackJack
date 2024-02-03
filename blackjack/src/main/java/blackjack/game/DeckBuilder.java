package blackjack.game;


import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.util.*;


@Getter @Setter

public class DeckBuilder {

    @Getter
    private static List<DeckBuilder> deck;
    @Getter
    private static List<ImageIcon> heartsImage = new ArrayList<>();
    @Getter
    private static List<ImageIcon> diamondsImage = new ArrayList<>();
    @Getter
    private static List<ImageIcon> spadesImage = new ArrayList<>();
    @Getter
    private static List<ImageIcon> clubsImage = new ArrayList<>();
    @Getter
    private String cardID;
    @Getter
    private String suit;
    @Getter
    private int value;
    @Getter
    private ImageIcon cardTemplate;
    @Getter
    private static ImageIcon back = new ImageIcon("src/main/resources/back.jpg");

    DeckBuilder(String name, int value, ImageIcon cardTemplate) {

        String[] card = name.split("");
        this.cardID = card[0];
        this.suit = card[1];
        this.value = value;
        this.cardTemplate = cardTemplate;


    }

    public static void buildDeck() {

        for (int i = 0; i <=9 ; i++) {
            heartsImage.add( new ImageIcon ("src/main/resources/" + i + "H.png"));
            diamondsImage.add(new ImageIcon("src/main/resources/" + i + "D.png"));
            spadesImage.add(new ImageIcon("src/main/resources/" + i + "S.png"));
            clubsImage.add(new ImageIcon("src/main/resources/" + i + "C.png"));

        }




        List<DeckBuilder> hearts = new ArrayList<>();
        for (int i = 2; i <= 9; i++) {
            hearts.add(new DeckBuilder(i + "H", i, heartsImage.get(i)));
        }

        List<DeckBuilder> diamonds = new ArrayList<>();
        for (int i = 2; i <= 9; i++) {
            diamonds.add(new DeckBuilder(i + "D", i, diamondsImage.get(i)));
        }

        List<DeckBuilder> spades = new ArrayList<>();
        for (int i = 2; i <= 9; i++) {
            spades.add(new DeckBuilder(i + "S", i, spadesImage.get(i)));
        }

        List<DeckBuilder> clubs = new ArrayList<>();
        for (int i = 2; i < 10; i++) {
            clubs.add(new DeckBuilder(i + "C", i, clubsImage.get(i)));
        }

        deck = new ArrayList<>();

        for (int i = 0; i < hearts.size(); i++) {
            deck.add(hearts.get(i));
            deck.add(diamonds.get(i));
            deck.add(spades.get(i));
            deck.add(clubs.get(i));

        }
        deck.add(new DeckBuilder("TH", 10,  new ImageIcon("src/main/resources/TH.png")));
        deck.add(new DeckBuilder("JH", 10,  new ImageIcon("src/main/resources/JH.png")));
        deck.add(new DeckBuilder("QH", 10,  new ImageIcon("src/main/resources/QH.png")));
        deck.add(new DeckBuilder("KH", 10,  new ImageIcon("src/main/resources/KH.png")));
        deck.add(new DeckBuilder("AH", 11,  new ImageIcon("src/main/resources/AH.png")));

        deck.add(new DeckBuilder("TD", 10, new ImageIcon("src/main/resources/TD.png")));
        deck.add(new DeckBuilder("JD", 10, new ImageIcon("src/main/resources/JD.png")));
        deck.add(new DeckBuilder("QD", 10, new ImageIcon("src/main/resources/QD.png")));
        deck.add(new DeckBuilder("KD", 10, new ImageIcon("src/main/resources/KD.png")));
        deck.add(new DeckBuilder("AD", 11, new ImageIcon("src/main/resources/AD.png")));

        deck.add(new DeckBuilder("TS", 10, new ImageIcon("src/main/resources/TS.png")));
        deck.add(new DeckBuilder("JS", 10, new ImageIcon("src/main/resources/JS.png")));
        deck.add(new DeckBuilder("QS", 10, new ImageIcon("src/main/resources/QS.png")));
        deck.add(new DeckBuilder("KS", 10, new ImageIcon("src/main/resources/KS.png")));
        deck.add(new DeckBuilder("AS", 11, new ImageIcon("src/main/resources/AS.png")));

        deck.add(new DeckBuilder("TC", 10, new ImageIcon("src/main/resources/TC.png")));
        deck.add(new DeckBuilder("JC", 10, new ImageIcon("src/main/resources/JC.png")));
        deck.add(new DeckBuilder("QC", 10, new ImageIcon("src/main/resources/QC.png")));
        deck.add(new DeckBuilder("KC", 10, new ImageIcon("src/main/resources/KC.png")));
        deck.add(new DeckBuilder("AC", 11, new ImageIcon("src/main/resources/AC.png")));


       // Collections.shuffle(deck);

    }




    @Override
    public String toString() {
        return
                "cardID= " + cardID + " " +
                "suit= " + suit + " value = " + value + " Template = " + cardTemplate;
    }
}
