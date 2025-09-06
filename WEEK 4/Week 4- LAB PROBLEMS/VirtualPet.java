import java.util.*;

public class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private int stageIndex;
    private boolean isGhost;

    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    static int totalPetsCreated = 0;

    public VirtualPet() {
        this("Unknown", randomSpecies(), 0, 50, 50, 0);
    }

    public VirtualPet(String petName) {
        this(petName, randomSpecies(), 0, 60, 60, 1);
    }

    public VirtualPet(String petName, String species) {
        this(petName, species, 0, 70, 70, 2);
    }

    public VirtualPet(String petName, String species, int age, int happiness, int health, int stageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stageIndex = stageIndex;
        this.isGhost = false;
        totalPetsCreated++;
    }

    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private static String randomSpecies() {
        String[] options = {"Dragon", "Cat", "Dog", "Phoenix", "Unicorn"};
        return options[new Random().nextInt(options.length)];
    }

    public void feedPet() {
        if (!isGhost) {
            happiness += 5;
            health += 5;
        }
    }

    public void playWithPet() {
        if (!isGhost) {
            happiness += 10;
            health -= 2;
        }
    }

    public void healPet() {
        if (!isGhost) {
            health += 10;
        }
    }

    public void simulateDay() {
        if (isGhost) return;
        age++;
        happiness -= new Random().nextInt(5);
        health -= new Random().nextInt(5);
        if (health <= 0) {
            isGhost = true;
            species = "Ghost";
            stageIndex = -1;
        } else {
            evolvePet();
        }
    }

    public void evolvePet() {
        if (isGhost) return;
        if (age > 20 && stageIndex < 5) stageIndex = 5;
        else if (age > 15 && stageIndex < 4) stageIndex = 4;
        else if (age > 10 && stageIndex < 3) stageIndex = 3;
        else if (age > 5 && stageIndex < 2) stageIndex = 2;
        else if (age > 2 && stageIndex < 1) stageIndex = 1;
    }

    public String getPetStatus() {
        if (isGhost) return "Ghost";
        return EVOLUTION_STAGES[stageIndex];
    }

    public void displayInfo() {
        System.out.println("ID: " + petId + " | Name: " + petName + " | Species: " + species + " | Age: " + age + " | Happiness: " + happiness + " | Health: " + health + " | Stage: " + getPetStatus());
    }

    public static void main(String[] args) {
        System.out.println("=== VIRTUAL PET DAYCARE ===");
        List<VirtualPet> pets = new ArrayList<>();
        pets.add(new VirtualPet());
        pets.add(new VirtualPet("Luna"));
        pets.add(new VirtualPet("Rex", "Wolf"));
        pets.add(new VirtualPet("Zara", "Dragon", 3, 80, 90, 2));

        for (int day = 1; day <= 7; day++) {
            System.out.println("\n--- Day " + day + " ---");
            for (VirtualPet pet : pets) {
                pet.feedPet();
                pet.playWithPet();
                pet.simulateDay();
                pet.displayInfo();
            }
        }
        System.out.println("\nTotal Pets Created: " + VirtualPet.totalPetsCreated);
    }
}
