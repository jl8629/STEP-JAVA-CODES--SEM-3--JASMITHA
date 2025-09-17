class Fruit {
    protected String colour;
    protected String taste;

    public Fruit(String colour, String taste) {
        this.colour = colour;
        this.taste = taste;
    }

    public void displayFruitInfo() {
        System.out.println("Fruit Colour: " + colour);
        System.out.println("Fruit Taste: " + taste);
    }
}

class Apple extends Fruit {
    private String variety;

    public Apple(String colour, String taste, String variety) {
        super(colour, taste);
        this.variety = variety;
    }

    public void displayAppleInfo() {
        System.out.println("Apple Color: " + colour);
        System.out.println("Apple Taste: " + taste);
        System.out.println("Apple Variety: " + variety);
    }
}

public class Fruits {
    public static void main(String[] args) {
        Apple myApple = new Apple("Red", "Sweet", "Fuji");

        System.out.println("Using Parent Method: ");
        myApple.displayFruitInfo();

        System.out.println("\nUsing Child Method: ");
        myApple.displayAppleInfo();
    }
}

