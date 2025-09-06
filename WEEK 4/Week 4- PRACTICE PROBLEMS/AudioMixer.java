public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    public AudioMixer() {
        this("StandardMix-8", 8, false);
    }

    public AudioMixer(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false);
    }

    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0);
    }

    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;
        System.out.println("Constructor executed for " + mixerModel);
    }

    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }

    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("MUSIC STUDIO SETUP: ");

        AudioMixer m1 = new AudioMixer();
        AudioMixer m2 = new AudioMixer("CompactMix-4", 4);
        AudioMixer m3 = new AudioMixer("ProMix-16", 16, true);
        AudioMixer m4 = new AudioMixer("UltraMix-32", 32, true, 150.0);

        m1.connectDevice("Keyboard");
        m1.connectDevice("Mic");
        m2.connectDevice("Guitar");
        m3.connectDevice("Drums");
        m3.connectDevice("Laptop");
        m4.connectDevice("DJ Console");
        m4.connectDevice("Synthesizer");
        m4.connectDevice("Bass");

        m1.displayMixerStatus();
        m2.displayMixerStatus();
        m3.displayMixerStatus();
        m4.displayMixerStatus();

        System.out.println("\nConstructor chaining shows that simpler constructors call more detailed ones, ensuring all fields are initialized consistently.");
    }
}
