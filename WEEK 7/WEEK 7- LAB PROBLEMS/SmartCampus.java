class SmartDevice {
    String deviceId;
    SmartDevice(String deviceId) {
        this.deviceId = deviceId;
    }
    public void showStatus() {
        System.out.println("Smart Device [" + deviceId + "] is active.");
    }
}

class SmartClassroom extends SmartDevice {
    SmartClassroom(String deviceId) {
        super(deviceId);
    }
    public void controlLighting(boolean on) {
        System.out.println("Classroom " + deviceId + " lighting " + (on ? "ON" : "OFF"));
    }
    public void controlAC(boolean on) {
        System.out.println("Classroom " + deviceId + " AC " + (on ? "ON" : "OFF"));
    }
    public void controlProjector(boolean on) {
        System.out.println("Classroom " + deviceId + " projector " + (on ? "ON" : "OFF"));
    }
}

class SmartLab extends SmartDevice {
    SmartLab(String deviceId) {
        super(deviceId);
    }
    public void manageEquipment(String equipment) {
        System.out.println("Lab " + deviceId + " managing equipment: " + equipment);
    }
    public void activateSafetySystem() {
        System.out.println("Lab " + deviceId + " safety system activated!");
    }
}

class SmartLibrary extends SmartDevice {
    SmartLibrary(String deviceId) {
        super(deviceId);
    }
    public void trackOccupancy(int count) {
        System.out.println("Library " + deviceId + " occupancy: " + count + " people");
    }
    public void checkBookAvailability(String book) {
        System.out.println("Library " + deviceId + " checking availability of \"" + book + "\"");
    }
}

public class SmartCampus {
    public static void main(String[] args) {
        SmartDevice[] devices = new SmartDevice[] {
            new SmartClassroom("CR101"),
            new SmartLab("LAB202"),
            new SmartLibrary("LIB303"),
            new SmartClassroom("CR104")
        };

        for (SmartDevice d : devices) {
            d.showStatus();
            if (d instanceof SmartClassroom) {
                SmartClassroom sc = (SmartClassroom) d;
                sc.controlLighting(true);
                sc.controlAC(false);
                sc.controlProjector(true);
            } else if (d instanceof SmartLab) {
                SmartLab sl = (SmartLab) d;
                sl.manageEquipment("3D Printer");
                sl.activateSafetySystem();
            } else if (d instanceof SmartLibrary) {
                SmartLibrary lib = (SmartLibrary) d;
                lib.trackOccupancy(42);
                lib.checkBookAvailability("Data Structures in Java");
            }
        }
    }
}
