class Content {
    String title;
    Content(String title) {
        this.title = title;
    }
    public void play() {
        System.out.println("Playing: " + title);
    }
}

class Movie extends Content {
    double rating;
    int duration;
    boolean subtitles;
    Movie(String title, double rating, int duration, boolean subtitles) {
        super(title);
        this.rating = rating;
        this.duration = duration;
        this.subtitles = subtitles;
    }
    public void showMovieDetails() {
        System.out.println("Movie: " + title + " | Rating: " + rating + " | Duration: " + duration + " mins | Subtitles: " + (subtitles ? "Yes" : "No"));
    }
}

class TVSeries extends Content {
    int seasons;
    int episodes;
    TVSeries(String title, int seasons, int episodes) {
        super(title);
        this.seasons = seasons;
        this.episodes = episodes;
    }
    public void suggestNextEpisode() {
        System.out.println("TV Series: " + title + " | Total Seasons: " + seasons + " | Total Episodes: " + episodes);
        System.out.println("Suggested: Watch the next episode!");
    }
}

class Documentary extends Content {
    String tags;
    String relatedContent;
    Documentary(String title, String tags, String relatedContent) {
        super(title);
        this.tags = tags;
        this.relatedContent = relatedContent;
    }
    public void showEducationalInfo() {
        System.out.println("Documentary: " + title + " | Tags: " + tags + " | Related: " + relatedContent);
    }
}

public class StreamingPlatform {
    public static void main(String[] args) {
        Content[] watchlist = new Content[] {
            new Movie("Inception", 8.8, 148, true),
            new TVSeries("Stranger Things", 4, 34),
            new Documentary("Planet Earth", "Nature, Wildlife", "Blue Planet")
        };

        for (Content c : watchlist) {
            c.play();
            if (c instanceof Movie) {
                Movie m = (Movie) c;
                m.showMovieDetails();
            } else if (c instanceof TVSeries) {
                TVSeries t = (TVSeries) c;
                t.suggestNextEpisode();
            } else if (c instanceof Documentary) {
                Documentary d = (Documentary) c;
                d.showEducationalInfo();
            }
        }
    }
}
