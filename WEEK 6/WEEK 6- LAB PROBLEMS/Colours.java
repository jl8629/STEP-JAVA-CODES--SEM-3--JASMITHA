class Colour {
    protected String name;

    public Colour(String name) {
        this.name = name;
        System.out.println("Colour constructor called");
    }
}

class PrimaryColour extends Colour {
    protected int intensity;

    public PrimaryColour(String name, int intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColour constructor called");
    }
}

class RedColour extends PrimaryColour {
    private String shade;

    public RedColour(String name, int intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColour constructor called");
    }

    public void displayDetails() {
        System.out.println("Colour Name: " + name);
        System.out.println("Intensity: " + intensity);
        System.out.println("Shade: " + shade);
    }
}

public class Colours {
    public static void main(String[] args) {
        RedColour rc = new RedColour("Red", 90, "Crimson");
        rc.displayDetails();
    }
}
