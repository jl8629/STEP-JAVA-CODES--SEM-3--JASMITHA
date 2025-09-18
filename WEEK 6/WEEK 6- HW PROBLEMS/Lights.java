class Light {
    String type;
    int intensity;

    Light() {
        this("Generic Light");
        System.out.println("Light(): Default constructor");
    }

    Light(String type) {
        this(type, 50);
        System.out.println("Light(String): Constructor with type");
    }

    Light(String type, int intensity) {
        this.type = type;
        this.intensity = intensity;
        System.out.println("Light(String, int): Constructor with type and intensity");
    }
}

class LED extends Light {
    String colour;

    LED() {
        this("White");
        System.out.println("LED(): Default constructor");
    }

    LED(String colour) {
        this(colour, "LED Light", 100);
        System.out.println("LED(String): Constructor with colour");
    }

    LED(String colour, String type, int intensity) {
        super(type, intensity);
        this.colour = colour;
        System.out.println("LED(String, String, int): Constructor with colour, type, and intensity");
    }
}

public class Lights {
    public static void main(String[] args) {
        LED led1 = new LED();
        System.out.println();
        LED led2 = new LED("Red");
        System.out.println();
        LED led3 = new LED("Blue", "Smart LED", 200);

        System.out.println(led1);
        System.out.println(led2);
        System.out.println(led3);
    }
}
