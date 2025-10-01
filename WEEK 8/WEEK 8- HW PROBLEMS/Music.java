interface Playable {
    void play();
    void pause();
}

class MusicPlayer implements Playable {
    public void play() {
        System.out.println("Music is playing");
    }
    public void pause() {
        System.out.println("Music is paused");
    }
}

class VideoPlayer implements Playable {
    public void play() {
        System.out.println("Video is playing");
    }
    public void pause() {
        System.out.println("Video is paused");
    }
}

public class Music {
    public static void main(String[] args) {
        Playable ref = new MusicPlayer();
        ref.play();
        ref.pause();
        ref = new VideoPlayer();
        ref.play();
        ref.pause();
    }
}
