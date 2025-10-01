abstract class Fruit {
    protected String color;
    protected String taste;
    abstract void showDetails();
}

interface Edible {
    void nutrientsInfo();
}

class Apple extends Fruit implements Edible {
    private String variety;
    Apple(String color, String taste, String variety) {
        this.color = color;
        this.taste = taste;
        this.variety = variety;
    }
    void showDetails() {
        System.out.println("Apple Variety: " + variety + ", Color: " + color + ", Taste: " + taste);
    }
    public void nutrientsInfo() {
        System.out.println("Rich in fiber, Vitamin C, and antioxidants");
    }
}

public class Fruits {
    public static void main(String[] args) {
        Apple a = new Apple("Red", "Sweet", "Fuji");
        a.showDetails();
        a.nutrientsInfo();
    }
}
