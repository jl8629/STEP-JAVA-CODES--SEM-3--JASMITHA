class ArtPiece {
    protected String title;
    protected String artist;

    ArtPiece(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    void displayInfo() {
        System.out.println("Art Title: " + title);
        System.out.println("Artist: " + artist);
        System.out.println();
    }
}

class Painting extends ArtPiece {
    private String brushTechnique;
    private String colorPalette;
    private String frame;

    Painting(String title, String artist, String brushTechnique, String colorPalette, String frame) {
        super(title, artist);
        this.brushTechnique = brushTechnique;
        this.colorPalette = colorPalette;
        this.frame = frame;
    }

    void paintingDetails() {
        System.out.println("Painting Details:");
        System.out.println("Brush Technique: " + brushTechnique);
        System.out.println("Color Palette: " + colorPalette);
        System.out.println("Frame: " + frame);
        System.out.println();
    }
}

class Sculpture extends ArtPiece {
    private String material;
    private String dimensions;
    private String lighting;

    Sculpture(String title, String artist, String material, String dimensions, String lighting) {
        super(title, artist);
        this.material = material;
        this.dimensions = dimensions;
        this.lighting = lighting;
    }

    void sculptureDetails() {
        System.out.println("Sculpture Details:");
        System.out.println("Material: " + material);
        System.out.println("Dimensions: " + dimensions);
        System.out.println("Lighting Requirements: " + lighting);
        System.out.println();
    }
}

class DigitalArt extends ArtPiece {
    private String resolution;
    private String fileFormat;
    private boolean interactive;

    DigitalArt(String title, String artist, String resolution, String fileFormat, boolean interactive) {
        super(title, artist);
        this.resolution = resolution;
        this.fileFormat = fileFormat;
        this.interactive = interactive;
    }

    void digitalArtDetails() {
        System.out.println("Digital Art Details:");
        System.out.println("Resolution: " + resolution);
        System.out.println("File Format: " + fileFormat);
        System.out.println("Interactive Elements: " + (interactive ? "Yes" : "No"));
        System.out.println();
    }
}

class Photography extends ArtPiece {
    private String cameraSettings;
    private String editingDetails;
    private String printSpecs;

    Photography(String title, String artist, String cameraSettings, String editingDetails, String printSpecs) {
        super(title, artist);
        this.cameraSettings = cameraSettings;
        this.editingDetails = editingDetails;
        this.printSpecs = printSpecs;
    }

    void photographyDetails() {
        System.out.println("Photography Details:");
        System.out.println("Camera Settings: " + cameraSettings);
        System.out.println("Editing Details: " + editingDetails);
        System.out.println("Print Specifications: " + printSpecs);
        System.out.println();
    }
}

public class DigitalArtGallery {
    public static void main(String[] args) {
        ArtPiece[] gallery = {
            new Painting("Sunset Bliss", "Alice", "Oil on Canvas", "Warm Tones", "Wooden Frame"),
            new Sculpture("The Thinker", "Bob", "Bronze", "2m x 1m", "Spotlight"),
            new DigitalArt("Virtual Dream", "Carol", "4K", "MP4", true),
            new Photography("City Lights", "David", "ISO 400, f/2.8", "Photoshop", "A3 Print")
        };

        for (ArtPiece art : gallery) {
            art.displayInfo();
            if (art instanceof Painting) {
                ((Painting) art).paintingDetails();
            } else if (art instanceof Sculpture) {
                ((Sculpture) art).sculptureDetails();
            } else if (art instanceof DigitalArt) {
                ((DigitalArt) art).digitalArtDetails();
            } else if (art instanceof Photography) {
                ((Photography) art).photographyDetails();
            }
        }
    }
}
