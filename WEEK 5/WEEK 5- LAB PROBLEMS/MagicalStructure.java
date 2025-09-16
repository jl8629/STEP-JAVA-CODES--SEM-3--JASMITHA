import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes, Map<String, Integer> resourceLimits) {
        if (kingdomName == null || kingdomName.isEmpty()) throw new IllegalArgumentException("kingdomName required");
        if (foundingYear <= 0) throw new IllegalArgumentException("foundingYear must be positive");
        if (allowedStructureTypes == null || allowedStructureTypes.length == 0) throw new IllegalArgumentException("allowedStructureTypes required");
        if (resourceLimits == null) throw new IllegalArgumentException("resourceLimits required");
        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length);
        this.resourceLimits = new HashMap<>(resourceLimits);
    }

    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
    public String[] getAllowedStructureTypes() { return Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length); }
    public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

    public static KingdomConfig createDefaultKingdom() {
        String[] types = {"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"};
        Map<String, Integer> limits = new HashMap<>();
        limits.put("gold", 100000);
        limits.put("mana", 10000);
        limits.put("stone", 50000);
        return new KingdomConfig("Avaloria", 1024, types, limits);
    }

    public static KingdomConfig createFromTemplate(String type) {
        if (type == null) throw new IllegalArgumentException("type required");
        if (type.equalsIgnoreCase("fortress")) {
            String[] types = {"EnchantedCastle", "WizardTower"};
            Map<String, Integer> limits = new HashMap<>();
            limits.put("gold", 200000);
            limits.put("mana", 5000);
            limits.put("stone", 150000);
            return new KingdomConfig("FortressRealm", 800, types, limits);
        } else {
            return createDefaultKingdom();
        }
    }

    @Override
    public String toString() {
        return "KingdomConfig{" +
                "kingdomName='" + kingdomName + '\'' +
                ", foundingYear=" + foundingYear +
                ", allowedStructureTypes=" + Arrays.toString(allowedStructureTypes) +
                ", resourceLimits=" + resourceLimits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KingdomConfig)) return false;
        KingdomConfig that = (KingdomConfig) o;
        return foundingYear == that.foundingYear &&
                Objects.equals(kingdomName, that.kingdomName) &&
                Arrays.equals(allowedStructureTypes, that.allowedStructureTypes) &&
                Objects.equals(resourceLimits, that.resourceLimits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(kingdomName, foundingYear, resourceLimits);
        result = 31 * result + Arrays.hashCode(allowedStructureTypes);
        return result;
    }
}

public class MagicalStructure {
    private final String structureId;
    private final long constructionTimestamp;
    private final String structureName;
    private final String location;
    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    public MagicalStructure(String name, String location) {
        this(name, location, 100, true);
    }

    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true);
    }

    public MagicalStructure(String name, String location, int power, boolean active) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("name required");
        if (location == null || location.isEmpty()) throw new IllegalArgumentException("location required");
        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        setMagicPower(power);
        this.isActive = active;
        this.currentMaintainer = "None";
    }

    public String getStructureId() { return structureId; }
    public long getConstructionTimestamp() { return constructionTimestamp; }
    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }
    public int getMagicPower() { return magicPower; }
    public boolean isActive() { return isActive; }
    public String getCurrentMaintainer() { return currentMaintainer; }

    public void setMagicPower(int magicPower) {
        this.magicPower = Math.max(MIN_MAGIC_POWER, Math.min(MAX_MAGIC_POWER, magicPower));
    }

    public void setActive(boolean active) { this.isActive = active; }
    public void setCurrentMaintainer(String currentMaintainer) { if (currentMaintainer != null) this.currentMaintainer = currentMaintainer; }

    @Override
    public String toString() {
        return "MagicalStructure{" +
                "structureId='" + structureId + '\'' +
                ", structureName='" + structureName + '\'' +
                ", location='" + location + '\'' +
                ", magicPower=" + magicPower +
                ", isActive=" + isActive +
                ", currentMaintainer='" + currentMaintainer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagicalStructure)) return false;
        MagicalStructure that = (MagicalStructure) o;
        return structureId.equals(that.structureId);
    }

    @Override
    public int hashCode() { return Objects.hash(structureId); }
}

class WizardTower {
    private final MagicalStructure core;
    private final int maxSpellCapacity;
    private final List<String> knownSpells;
    private String currentWizard;

    public WizardTower(String name, String location) {
        this(name, location, 200, Arrays.asList("Spark", "Shield"), "Apprentice");
    }

    public WizardTower(String name, String location, int maxSpellCapacity) {
        this(name, location, maxSpellCapacity, Arrays.asList("Spark", "Shield"), "Apprentice");
    }

    public WizardTower(String name, String location, int maxSpellCapacity, List<String> knownSpells, String currentWizard) {
        this.core = new MagicalStructure(name, location, Math.min(Math.max(200, maxSpellCapacity), MagicalStructure.MAX_MAGIC_POWER));
        this.maxSpellCapacity = Math.max(1, maxSpellCapacity);
        this.knownSpells = new ArrayList<>(Objects.requireNonNull(knownSpells));
        this.currentWizard = currentWizard == null ? "Unknown" : currentWizard;
    }

    public MagicalStructure getCore() { return core; }
    public int getMaxSpellCapacity() { return maxSpellCapacity; }
    public List<String> getKnownSpells() { return Collections.unmodifiableList(knownSpells); }
    public String getCurrentWizard() { return currentWizard; }
    public void setCurrentWizard(String currentWizard) { if (currentWizard != null) this.currentWizard = currentWizard; }
    public void learnSpell(String spell) { if (spell != null && !spell.isEmpty() && knownSpells.size() < maxSpellCapacity) knownSpells.add(spell); }

    @Override
    public String toString() {
        return "WizardTower{" + core + ", maxSpellCapacity=" + maxSpellCapacity + ", knownSpells=" + knownSpells + ", currentWizard='" + currentWizard + '\'' + '}';
    }
}

class EnchantedCastle {
    private final MagicalStructure core;
    private final String castleType;
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle(String name, String location) {
        this(name, location, "Royal", 500, true);
    }

    public EnchantedCastle(String name, String location, String castleType, int defenseRating, boolean hasDrawbridge) {
        this.core = new MagicalStructure(name, location, Math.min(Math.max(300, defenseRating), MagicalStructure.MAX_MAGIC_POWER));
        this.castleType = castleType == null ? "Royal" : castleType;
        this.defenseRating = Math.max(0, defenseRating);
        this.hasDrawbridge = hasDrawbridge;
    }

    public MagicalStructure getCore() { return core; }
    public String getCastleType() { return castleType; }
    public int getDefenseRating() { return defenseRating; }
    public boolean isHasDrawbridge() { return hasDrawbridge; }
    public void setDefenseRating(int defenseRating) { this.defenseRating = Math.max(0, defenseRating); }
    public void setHasDrawbridge(boolean hasDrawbridge) { this.hasDrawbridge = hasDrawbridge; }

    @Override
    public String toString() { return "EnchantedCastle{" + core + ", castleType='" + castleType + '\'' + ", defenseRating=" + defenseRating + ", hasDrawbridge=" + hasDrawbridge + '}'; }
}

class MysticLibrary {
    private final MagicalStructure core;
    private final Map<String, String> bookCollection;
    private int knowledgeLevel;

    public MysticLibrary(String name, String location) {
        this(name, location, new HashMap<>(), 50);
    }

    public MysticLibrary(String name, String location, Map<String, String> bookCollection, int knowledgeLevel) {
        this.core = new MagicalStructure(name, location, Math.min(Math.max(50, knowledgeLevel * 5), MagicalStructure.MAX_MAGIC_POWER));
        this.bookCollection = new HashMap<>(Objects.requireNonNull(bookCollection));
        this.knowledgeLevel = Math.max(0, knowledgeLevel);
    }

    public MagicalStructure getCore() { return core; }
    public Map<String, String> getBookCollection() { return new HashMap<>(bookCollection); }
    public int getKnowledgeLevel() { return knowledgeLevel; }
    public void setKnowledgeLevel(int knowledgeLevel) { this.knowledgeLevel = Math.max(0, knowledgeLevel); }
    public void addBook(String isbn, String title) { if (isbn != null && title != null) bookCollection.put(isbn, title); }

    @Override
    public String toString() { return "MysticLibrary{" + core + ", bookCollection=" + bookCollection + ", knowledgeLevel=" + knowledgeLevel + '}'; }
}

class DragonLair {
    private final MagicalStructure core;
    private final String dragonType;
    private final long treasureValue;
    private final int territorialRadius;

    public DragonLair(String name, String location, String dragonType) {
        this(name, location, dragonType, 100000L, 50);
    }

    public DragonLair(String name, String location, String dragonType, long treasureValue, int territorialRadius) {
        this.core = new MagicalStructure(name, location, Math.min(Math.max(400, territorialRadius * 5), MagicalStructure.MAX_MAGIC_POWER));
        this.dragonType = dragonType == null ? "Wyrm" : dragonType;
        this.treasureValue = Math.max(0, treasureValue);
        this.territorialRadius = Math.max(0, territorialRadius);
    }

    public MagicalStructure getCore() { return core; }
    public String getDragonType() { return dragonType; }
    public long getTreasureValue() { return treasureValue; }
    public int getTerritorialRadius() { return territorialRadius; }

    @Override
    public String toString() { return "DragonLair{" + core + ", dragonType='" + dragonType + '\'' + ", treasureValue=" + treasureValue + ", territorialRadius=" + territorialRadius + '}'; }
}

class KingdomManager {
    private final List<Object> structures;
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config) {
        this.config = Objects.requireNonNull(config);
        this.structures = new ArrayList<>();
    }

    public void addStructure(Object structure) { if (structure != null) structures.add(structure); }
    public List<Object> getStructures() { return Collections.unmodifiableList(structures); }
    public KingdomConfig getConfig() { return config; }

    public static boolean canStructuresInteract(Object s1, Object s2) {
        if (s1 == null || s2 == null) return false;
        if (s1 instanceof DragonLair || s2 instanceof DragonLair) return true;
        if (s1.getClass().equals(s2.getClass())) return true;
        if ((s1 instanceof WizardTower && s2 instanceof MysticLibrary) || (s2 instanceof WizardTower && s1 instanceof MysticLibrary)) return true;
        return false;
    }

    public static String performMagicBattle(Object attacker, Object defender) {
        int powerA = extractOffensivePower(attacker);
        int powerB = extractDefensivePower(defender);
        if (powerA <= powerB) return "Defender holds. Attacker failed.";
        int diff = powerA - powerB;
        if (diff > 200) return "Attacker overwhelms defender decisively.";
        return "Attacker wins with narrow margin.";
    }

    public static int calculateKingdomPower(Object[] structures) {
        if (structures == null) return 0;
        int sum = 0;
        for (Object s : structures) sum += extractOffensivePower(s);
        return sum;
    }

    private static int extractOffensivePower(Object s) {
        if (s instanceof DragonLair) return ((DragonLair) s).getCore().getMagicPower() + ((DragonLair) s).getTerritorialRadius() * 2;
        if (s instanceof WizardTower) return ((WizardTower) s).getCore().getMagicPower() + ((WizardTower) s).getKnownSpells().size() * 10;
        if (s instanceof EnchantedCastle) return ((EnchantedCastle) s).getCore().getMagicPower() + ((EnchantedCastle) s).getDefenseRating();
        if (s instanceof MysticLibrary) return ((MysticLibrary) s).getCore().getMagicPower() + ((MysticLibrary) s).getKnowledgeLevel() * 5;
        if (s instanceof MagicalStructure) return ((MagicalStructure) s).getMagicPower();
        return 0;
    }

    private static int extractDefensivePower(Object s) {
        if (s instanceof EnchantedCastle) return ((EnchantedCastle) s).getDefenseRating() + ((EnchantedCastle) s).getCore().getMagicPower() / 2;
        if (s instanceof DragonLair) return (int) (((DragonLair) s).getTreasureValue() / 1000) + ((DragonLair) s).getTerritorialRadius();
        if (s instanceof WizardTower) return ((WizardTower) s).getCore().getMagicPower() / 2 + ((WizardTower) s).getKnownSpells().size() * 5;
        if (s instanceof MysticLibrary) return ((MysticLibrary) s).getKnowledgeLevel() * 3 + ((MysticLibrary) s).getCore().getMagicPower() / 3;
        if (s instanceof MagicalStructure) return ((MagicalStructure) s).getMagicPower() / 2;
        return 0;
    }

    private String determineStructureCategory(Object structure) {
        if (structure instanceof DragonLair) return "DragonLair";
        if (structure instanceof WizardTower) return "WizardTower";
        if (structure instanceof EnchantedCastle) return "EnchantedCastle";
        if (structure instanceof MysticLibrary) return "MysticLibrary";
        if (structure instanceof MagicalStructure) return "MagicalStructure";
        return "Unknown";
    }
}

class KingdomDemo {
    public static void main(String[] args) {
        KingdomConfig defaultConfig = KingdomConfig.createDefaultKingdom();
        KingdomManager manager = new KingdomManager(defaultConfig);

        WizardTower tower = new WizardTower("High Spire", "North Peak", 300, Arrays.asList("Spark", "Gale", "Ward"), "Merlin");
        EnchantedCastle castle = new EnchantedCastle("Stoneheart", "Central Vale", "Fortress", 800, true);
        Map<String, String> books = new HashMap<>();
        books.put("ISBN-001", "Tome of Light");
        MysticLibrary library = new MysticLibrary("Scriptorium", "East Quarter", books, 120);
        DragonLair lair = new DragonLair("Smolder Den", "Volcanic Ridge", "Ancient Drake", 500000L, 120);

        manager.addStructure(tower);
        manager.addStructure(castle);
        manager.addStructure(library);
        manager.addStructure(lair);

        System.out.println("Config: " + manager.getConfig());
        System.out.println("Structures:");
        manager.getStructures().forEach(System.out::println);

        System.out.println("Can tower interact with library? " + KingdomManager.canStructuresInteract(tower, library));
        System.out.println("Can castle interact with lair? " + KingdomManager.canStructuresInteract(castle, lair));

        System.out.println("Battle result (tower vs castle): " + KingdomManager.performMagicBattle(tower, castle));
        System.out.println("Battle result (lair vs castle): " + KingdomManager.performMagicBattle(lair, castle));

        Object[] arr = {tower, castle, library, lair};
        System.out.println("Total kingdom power: " + KingdomManager.calculateKingdomPower(arr));
    }
}
