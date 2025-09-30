public class SmartPhone implements Camera, MusicPlayer {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo with smartphone");
    }
    @Override
    public void playMusic() {
        System.out.println("Playing music on smartphone");
    }
}