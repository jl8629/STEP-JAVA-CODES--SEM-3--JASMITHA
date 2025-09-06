import java.util.*;

enum CrewRank {
    CADET(1), OFFICER(2), COMMANDER(3), CAPTAIN(4), ADMIRAL(5);
    private final int level;
    CrewRank(int level) { this.level = level; }
    public int getLevel() { return level; }
}

class SpaceCrew {
    private final String crewId;
    private final String homeplanet;
    private final CrewRank initialRank;
    protected CrewRank currentRank;
    protected int skillLevel;
    protected int missionCount;
    protected int spaceHours;

    static final String STATION_NAME = "Stellar Odyssey";
    static final int MAX_CREW_CAPACITY = 50;

    public SpaceCrew(String homeplanet) {
        this("Unknown", homeplanet, CrewRank.CADET, 0, 0, 0);
    }

    public SpaceCrew(String name, String homeplanet, CrewRank rank) {
        this(name, homeplanet, rank, 0, 0, 0);
    }

    public SpaceCrew(String name, String homeplanet, CrewRank rank, int missions, int skill) {
        this(name, homeplanet, rank, missions, skill, missions * 100);
    }

    public SpaceCrew(String name, String homeplanet, CrewRank rank, int missions, int skill, int hours) {
        this.crewId = generateCrewId();
        this.homeplanet = homeplanet;
        this.initialRank = rank;
        this.currentRank = rank;
        this.missionCount = missions;
        this.skillLevel = skill;
        this.spaceHours = hours;
    }

    private static String generateCrewId() {
        return UUID.randomUUID().toString();
    }

    public final String getCrewIdentification() {
        return "ID: " + crewId + " | Home: " + homeplanet + " | Initial Rank: " + initialRank;
    }

    public final boolean canBePromoted() {
        return currentRank.getLevel() < CrewRank.ADMIRAL.getLevel();
    }

    public final int calculateSpaceExperience() {
        return (missionCount * 50) + (skillLevel * 2) + (spaceHours / 10);
    }

    public void promote() {
        if (canBePromoted()) {
            currentRank = CrewRank.values()[currentRank.getLevel()];
            System.out.println(crewId + " promoted to " + currentRank);
        }
    }

    public void displayProfile() {
        System.out.println(getCrewIdentification() + " | Current Rank: " + currentRank +
                " | Skill: " + skillLevel + " | Missions: " + missionCount +
                " | Hours: " + spaceHours + " | Experience: " + calculateSpaceExperience());
    }
}

class PilotCrew extends SpaceCrew {
    private final String flightCertification;
    public PilotCrew(String name, String homeplanet, CrewRank rank, String flightCertification) {
        super(name, homeplanet, rank);
        this.flightCertification = flightCertification;
    }
    public String getFlightCertification() { return flightCertification; }
}

class ScienceCrew extends SpaceCrew {
    private final String researchSpecialization;
    public ScienceCrew(String name, String homeplanet, CrewRank rank, String specialization) {
        super(name, homeplanet, rank);
        this.researchSpecialization = specialization;
    }
    public String getResearchSpecialization() { return researchSpecialization; }
}

class EngineerCrew extends SpaceCrew {
    private final String engineeringType;
    public EngineerCrew(String name, String homeplanet, CrewRank rank, String engineeringType) {
        super(name, homeplanet, rank);
        this.engineeringType = engineeringType;
    }
    public String getEngineeringType() { return engineeringType; }
}

final class SpaceStationRegistry {
    private static final List<SpaceCrew> crewList = new ArrayList<>();

    public static void addCrew(SpaceCrew crew) {
        if (crewList.size() < SpaceCrew.MAX_CREW_CAPACITY) {
            crewList.add(crew);
            System.out.println("Crew added: " + crew.getCrewIdentification());
        } else {
            System.out.println("Station at full capacity!");
        }
    }

    public static void showAllCrew() {
        System.out.println("\n=== Crew Registry of " + SpaceCrew.STATION_NAME + " ===");
        for (SpaceCrew crew : crewList) {
            crew.displayProfile();
        }
    }

    public static void handleEmergency() {
        System.out.println("\n=== EMERGENCY SCENARIO ===");
        boolean pilotReady = false, engineerReady = false, scientistReady = false;
        for (SpaceCrew crew : crewList) {
            if (crew instanceof PilotCrew) pilotReady = true;
            if (crew instanceof EngineerCrew) engineerReady = true;
            if (crew instanceof ScienceCrew) scientistReady = true;
        }
        if (pilotReady && engineerReady && scientistReady) {
            System.out.println("Crisis resolved! Perfect crew combination available.");
        } else {
            System.out.println("Critical failure! Missing required roles.");
        }
    }
}

public class SpaceStationSimulation {
    public static void main(String[] args) {
        SpaceCrew cadet = new SpaceCrew("Earth");
        SpaceCrew officer = new SpaceCrew("Alice", "Mars", CrewRank.OFFICER);
        PilotCrew pilot = new PilotCrew("John", "Venus", CrewRank.COMMANDER, "Advanced Pilot License");
        ScienceCrew scientist = new ScienceCrew("Eve", "Europa", CrewRank.CAPTAIN, "Astrobiology");
        EngineerCrew engineer = new EngineerCrew("Max", "Titan", CrewRank.OFFICER, "Warp Core Systems");

        SpaceStationRegistry.addCrew(cadet);
        SpaceStationRegistry.addCrew(officer);
        SpaceStationRegistry.addCrew(pilot);
        SpaceStationRegistry.addCrew(scientist);
        SpaceStationRegistry.addCrew(engineer);

        SpaceStationRegistry.showAllCrew();
        SpaceStationRegistry.handleEmergency();
    }
}
