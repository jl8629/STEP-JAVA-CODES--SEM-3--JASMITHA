class SmartDevice {
    protected String deviceName;

    SmartDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    void status() {
        System.out.println(deviceName + " status: Active");
        System.out.println();
    }
}

class SmartTV extends SmartDevice {
    private int volume;
    private String channel;

    SmartTV(String deviceName, int volume, String channel) {
        super(deviceName);
        this.volume = volume;
        this.channel = channel;
    }

    void controlTV() {
        System.out.println("Smart TV Control:");
        System.out.println("Channel: " + channel);
        System.out.println("Volume: " + volume);
        System.out.println("Streaming Apps: Netflix, YouTube, Hulu");
        System.out.println();
    }
}

class SmartThermostat extends SmartDevice {
    private double temperature;
    private double humidity;
    private boolean energySaver;

    SmartThermostat(String deviceName, double temperature, double humidity, boolean energySaver) {
        super(deviceName);
        this.temperature = temperature;
        this.humidity = humidity;
        this.energySaver = energySaver;
    }

    void controlThermostat() {
        System.out.println("Smart Thermostat Control:");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Energy Saver Mode: " + (energySaver ? "ON" : "OFF"));
        System.out.println();
    }
}

class SmartSecurity extends SmartDevice {
    private boolean alarmActive;
    private int cameraCount;

    SmartSecurity(String deviceName, boolean alarmActive, int cameraCount) {
        super(deviceName);
        this.alarmActive = alarmActive;
        this.cameraCount = cameraCount;
    }

    void controlSecurity() {
        System.out.println("Smart Security Control:");
        System.out.println("Alarm: " + (alarmActive ? "Activated" : "Deactivated"));
        System.out.println("Cameras: " + cameraCount);
        System.out.println("Access Control: Enabled");
        System.out.println();
    }
}

class SmartKitchen extends SmartDevice {
    private int cookingTime;
    private double temperature;

    SmartKitchen(String deviceName, int cookingTime, double temperature) {
        super(deviceName);
        this.cookingTime = cookingTime;
        this.temperature = temperature;
    }

    void controlKitchen() {
        System.out.println("Smart Kitchen Appliance Control:");
        System.out.println("Cooking Time: " + cookingTime + " minutes");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Recipes: Breakfast, Lunch, Dinner presets");
        System.out.println();
    }
}

public class SmartHomeAutomation {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartTV("Living Room TV", 25, "HBO"),
            new SmartThermostat("Hall Thermostat", 22.5, 45, true),
            new SmartSecurity("Home Security", true, 4),
            new SmartKitchen("Kitchen Oven", 30, 180)
        };

        for (SmartDevice device : devices) {
            device.status();
            if (device instanceof SmartTV) {
                ((SmartTV) device).controlTV();
            } else if (device instanceof SmartThermostat) {
                ((SmartThermostat) device).controlThermostat();
            } else if (device instanceof SmartSecurity) {
                ((SmartSecurity) device).controlSecurity();
            } else if (device instanceof SmartKitchen) {
                ((SmartKitchen) device).controlKitchen();
            }
        }
    }
}
