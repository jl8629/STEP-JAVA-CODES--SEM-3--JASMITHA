abstract class Food {
    public final void prepare() {
        wash();
        cook();
        serve();
    }

    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

class Pizza extends Food {
    protected void wash() {
        System.out.println("Washing vegetables and dough ingredients");
    }

    protected void cook() {
        System.out.println("Baking pizza in oven");
    }

    protected void serve() {
        System.out.println("Serving hot pizza with toppings");
    }
}

class Soup extends Food {
    protected void wash() {
        System.out.println("Washing vegetables and spices");
    }

    protected void cook() {
        System.out.println("Boiling ingredients to make soup");
    }

    protected void serve() {
        System.out.println("Serving soup in a bowl");
    }
}

public class Foods {
    public static void main(String[] args) {
        Food f1 = new Pizza();
        Food f2 = new Soup();
        f1.prepare();
        System.out.println();
        f2.prepare();
    }
}
