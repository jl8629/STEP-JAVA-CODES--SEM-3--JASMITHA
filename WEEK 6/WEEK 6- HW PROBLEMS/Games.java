import java.util.Objects;

class Game {
    String title;
    int players;

    Game(String title, int players) {
        this.title = title;
        this.players = players;
    }

    @Override
    public String toString() {
        return "Game[title=" + title + ", players=" + players + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Game game = (Game) obj;
        return players == game.players && Objects.equals(title, game.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, players);
    }
}

class CardGame extends Game {
    String deckType;

    CardGame(String title, int players, String deckType) {
        super(title, players);
        this.deckType = deckType;
    }

    @Override
    public String toString() {
        return super.toString() + ", CardGame[deckType=" + deckType + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        CardGame cg = (CardGame) obj;
        return Objects.equals(deckType, cg.deckType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deckType);
    }
}

public class Games {
    public static void main(String[] args) {
        Game g1 = new Game("Chess", 2);
        Game g2 = new Game("Chess", 2);
        CardGame cg1 = new CardGame("Poker", 4, "Standard 52");
        CardGame cg2 = new CardGame("Poker", 4, "Standard 52");
        CardGame cg3 = new CardGame("Poker", 4, "Custom Deck");

        System.out.println(g1);
        System.out.println(cg1);

        System.out.println("g1 equals g2: " + g1.equals(g2));
        System.out.println("cg1 equals cg2: " + cg1.equals(cg2));
        System.out.println("cg1 equals cg3: " + cg1.equals(cg3));
    }
}
