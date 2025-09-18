class Tool {
    private String name;
    protected String material;
    public int weight;

    Tool(String name, String material, int weight) {
        this.name = name;
        this.material = material;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
}

class Hammer extends Tool {
    Hammer(String name, String material, int weight) {
        super(name, material, weight);
    }

    public void showAccess() {
        System.out.println("Private field name (via getter): " + getName());
        System.out.println("Protected field material: " + material);
        System.out.println("Public field weight: " + weight);
    }
}

public class Tools {
    public static void main(String[] args) {
        Hammer h = new Hammer("Claw Hammer", "Steel", 5);
        h.showAccess();
    }
}
