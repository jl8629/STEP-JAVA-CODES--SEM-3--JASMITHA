public class GameController {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    public GameController() {
        controllerBrand = "GenericPad";
        connectionType = "USB";
        hasVibration = true;
        batteryLevel = 100;
        sensitivity = 1.0;
    }

    public GameController(String controllerBrand, String connectionType, boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        if (batteryLevel < 0) this.batteryLevel = 0;
        else if (batteryLevel > 100) this.batteryLevel = 100;
        else this.batteryLevel = batteryLevel;
        if (sensitivity < 0.1) this.sensitivity = 0.1;
        else if (sensitivity > 3.0) this.sensitivity = 3.0;
        else this.sensitivity = sensitivity;
    }

    public GameController(String brand, String connectionType) {
        this.controllerBrand = brand;
        this.connectionType = connectionType;
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Brand: " + controllerBrand + ", Connection: " + connectionType + ", Vibration: " + hasVibration + ", Battery: " + batteryLevel + "%, Sensitivity: " + sensitivity);
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        System.out.println("GAMING CONTROLLER SETUP: ");
        GameController c1 = new GameController();
        GameController c2 = new GameController("ProPad", "Wireless", false, 85, 2.5);
        GameController c3 = new GameController("LitePad", "USB");

        c1.displayConfiguration();
        c1.calibrateController();
        c1.testVibration();

        c2.displayConfiguration();
        c2.calibrateController();
        c2.testVibration();

        c3.displayConfiguration();
        c3.calibrateController();
        c3.testVibration();
    }
}
