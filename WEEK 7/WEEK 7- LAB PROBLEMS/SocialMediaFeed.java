import java.time.LocalDateTime;

class Post {
    String author;
    String content;
    LocalDateTime time;

    Post(String author, String content) {
        this.author = author;
        this.content = content;
        this.time = LocalDateTime.now();
    }

    public void display() {
        System.out.println(author + ": " + content + " (" + time + ")");
    }
}

class InstagramPost extends Post {
    int likes;
    String hashtags;

    InstagramPost(String author, String content, int likes, String hashtags) {
        super(author, content);
        this.likes = likes;
        this.hashtags = hashtags;
    }

    @Override
    public void display() {
        System.out.println("Instagram Post by " + author);
        System.out.println(content);
        System.out.println("Hashtags: " + hashtags);
        System.out.println("Likes: " + likes);
        System.out.println("Posted at: " + time);
    }
}

class TwitterPost extends Post {
    int retweets;

    TwitterPost(String author, String content, int retweets) {
        super(author, content);
        this.retweets = retweets;
    }

    @Override
    public void display() {
        System.out.println("Twitter Post by " + author);
        System.out.println(content + " (" + content.length() + " chars)");
        System.out.println("Retweets: " + retweets);
        System.out.println("Posted at: " + time);
    }
}

class LinkedInPost extends Post {
    int connections;

    LinkedInPost(String author, String content, int connections) {
        super(author, content);
        this.connections = connections;
    }

    @Override
    public void display() {
        System.out.println("LinkedIn Post by " + author);
        System.out.println("Content: " + content);
        System.out.println("Professional Connections: " + connections);
        System.out.println("Posted at: " + time);
    }
}

public class SocialMediaFeed {
    public static void main(String[] args) {
        Post insta = new InstagramPost("Arushi", "Enjoying the beach!", 120, "#sunset #beach");
        Post tweet = new TwitterPost("Ruchi", "Java is awesome!", 45);
        Post linkedin = new LinkedInPost("Tom", "Excited to start a new role at TechCorp.", 500);

        insta.display();
        tweet.display();
        linkedin.display();
    }
}
