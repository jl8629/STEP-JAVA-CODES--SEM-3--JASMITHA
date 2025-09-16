import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Objects;

final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isEmpty()) throw new IllegalArgumentException("Invalid species name");
        if (evolutionStages == null || evolutionStages.length == 0) throw new IllegalArgumentException("Evolution stages required");
        if (maxLifespan <= 0) throw new IllegalArgumentException("Max lifespan must be positive");
        if (habitat == null || habitat.isEmpty()) throw new IllegalArgumentException("Invalid habitat");
        this.speciesName = speciesName;
        this.evolutionStages = Arrays.copyOf(evolutionStages, evolutionStages.length);
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return Arrays.copyOf(evolutionStages, evolutionStages.length); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }
}

public class VirtualPet {
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    private String petName;
    private int age;
    private int happiness;
    private int health;

    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Baby", "Teen", "Adult", "Elder"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    public VirtualPet() {
        this("RandomPet", new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 100, "Generic"), 0, 50, 50);
    }

    public VirtualPet(String petName) {
        this(petName, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 100, "Generic"), 0, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species) {
        this(petName, species, 0, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species, int age, int happiness, int health) {
        if (petName == null || petName.isEmpty()) throw new IllegalArgumentException("Invalid pet name");
        this.petId = generatePetId();
        this.petName = petName;
        this.species = Objects.requireNonNull(species);
        this.birthTimestamp = System.currentTimeMillis();
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
    }

    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }
    public String getPetName() { return petName; }
    public int getAge() { return age; }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }

    public void setPetName(String petName) { if (petName != null && !petName.isEmpty()) this.petName = petName; }
    public void setAge(int age) { this.age = Math.max(0, age); }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness); }
    public void setHealth(int health) { this.health = validateStat(health); }

    private int validateStat(int stat) { return Math.max(0, Math.min(100, stat)); }
    private String generatePetId() { return UUID.randomUUID().toString(); }
    private void modifyHappiness(int delta) { setHappiness(happiness + delta); }
    private void modifyHealth(int delta) { setHealth(health + delta); }
    private void updateEvolutionStage() { }

    public void feedPet(String foodType) { modifyHealth(calculateFoodBonus(foodType)); updateEvolutionStage(); }
    public void playWithPet(String gameType) { modifyHappiness(calculateGameEffect(gameType)); updateEvolutionStage(); }

    protected int calculateFoodBonus(String foodType) { return foodType.equalsIgnoreCase("Meat") ? 10 : 5; }
    protected int calculateGameEffect(String gameType) { return gameType.equalsIgnoreCase("Fetch") ? 15 : 8; }

    int[] getInternalState() { return new int[]{age, happiness, health}; }

    public String toString() {
        return "VirtualPet{" +
                "petId='" + petId + '\'' +
                ", species=" + species.getSpeciesName() +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", health=" + health +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet that = (VirtualPet) o;
        return petId.equals(that.petId);
    }

    public int hashCode() { return Objects.hash(petId); }
}

class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet pet;

    public DragonPet(String dragonType, String breathWeapon, VirtualPet pet) {
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
        this.pet = pet;
    }

    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public VirtualPet getPet() { return pet; }

    public String toString() { return "DragonPet{" + dragonType + ", " + breathWeapon + ", " + pet + '}'; }
}

class RobotPet {
    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet pet;

    public RobotPet(boolean needsCharging, int batteryLevel, VirtualPet pet) {
        this.needsCharging = needsCharging;
        this.batteryLevel = batteryLevel;
        this.pet = pet;
    }

    public boolean isNeedsCharging() { return needsCharging; }
    public int getBatteryLevel() { return batteryLevel; }
    public VirtualPet getPet() { return pet; }

    public void setNeedsCharging(boolean needsCharging) { this.needsCharging = needsCharging; }
    public void setBatteryLevel(int batteryLevel) { this.batteryLevel = Math.max(0, Math.min(100, batteryLevel)); }

    public String toString() { return "RobotPet{" + needsCharging + ", battery=" + batteryLevel + ", " + pet + '}'; }
}

class MainTest {
    public static void main(String[] args) {
        PetSpecies dragonSpecies = new PetSpecies("Dragon", new String[]{"Egg", "Hatchling", "Wyrmling", "Elder Dragon"}, 500, "Mountains");
        VirtualPet pet1 = new VirtualPet("Flamey", dragonSpecies);
        pet1.feedPet("Meat");
        pet1.playWithPet("Fetch");
        DragonPet dragon = new DragonPet("Fire", "Flame Breath", pet1);

        PetSpecies robotSpecies = new PetSpecies("Robot", new String[]{"Prototype", "Model X", "AI-Enhanced"}, 300, "Lab");
        VirtualPet pet2 = new VirtualPet("RoboBuddy", robotSpecies);
        RobotPet robot = new RobotPet(true, 75, pet2);

        System.out.println(pet1);
        System.out.println(dragon);
        System.out.println(robot);
    }
}
