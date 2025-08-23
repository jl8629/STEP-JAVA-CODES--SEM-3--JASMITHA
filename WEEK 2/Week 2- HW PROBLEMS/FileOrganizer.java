import java.util.*;

class FileInfo {
    String originalName;
    String baseName;
    String extension;
    String category;
    String subCategory;
    String newName;
    boolean valid;
    String content; 
}

public class FileOrganizer {

    static FileInfo parseFileName(String fileName, String content) {
        FileInfo f = new FileInfo();
        f.originalName = fileName;
        f.content = content;
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == 0 || dotIndex == fileName.length() - 1) {
            f.valid = false;
            f.baseName = fileName;
            f.extension = "";
        } else {
            f.baseName = fileName.substring(0, dotIndex);
            f.extension = fileName.substring(dotIndex + 1);
            f.valid = validateFileName(f.baseName);
        }
        return f;
    }

    static boolean validateFileName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_' || c == '-')) return false;
        }
        return true;
    }

    static String categorize(FileInfo f) {
        String ext = f.extension.toLowerCase();
        if (ext.equals("txt") || ext.equals("doc")) return "Document";
        if (ext.equals("jpg") || ext.equals("png")) return "Image";
        if (ext.equals("mp3") || ext.equals("wav")) return "Audio";
        if (ext.equals("mp4") || ext.equals("avi")) return "Video";
        if (ext.equals("java") || ext.equals("cpp")) return "Code";
        return "Unknown";
    }

    static void contentAnalysis(FileInfo f) {
        if (!f.extension.equalsIgnoreCase("txt")) return;
        String text = f.content.toLowerCase();
        if (text.contains("resume") || text.contains("cv")) f.subCategory = "Resume";
        else if (text.contains("report")) f.subCategory = "Report";
        else if (text.contains("class") || text.contains("public")) f.subCategory = "Code";
        else f.subCategory = "General Text";
    }

    static void generateNewName(FileInfo f, Map<String,Integer> nameCounter, String date) {
        StringBuilder sb = new StringBuilder();
        sb.append(f.category);
        if (f.subCategory != null) sb.append("_").append(f.subCategory);
        sb.append("_").append(date);

        String key = sb.toString();
        int count = nameCounter.getOrDefault(key, 0) + 1;
        nameCounter.put(key, count);

        sb.append("_").append(count).append(".").append(f.extension);
        f.newName = sb.toString();
    }

    static void generateReport(List<FileInfo> files) {
        System.out.println("File Organization Report");
        System.out.println("\n");
        System.out.printf("%-20s %-12s %-25s\n", "Original Name", "Category", "New Name");
        System.out.println("----------------------------------------------------");
        Map<String,Integer> counts = new HashMap<>();
        for (FileInfo f : files) {
            f.category = categorize(f);
            contentAnalysis(f);
            if (f.newName == null) f.newName = "(invalid)";
            System.out.printf("%-20s %-12s %-25s\n", f.originalName, f.category, f.newName);
            counts.put(f.category, counts.getOrDefault(f.category, 0) + 1);
        }
        System.out.println("\nCategory Counts:");
        for (String c : counts.keySet()) {
            System.out.println(c + ": " + counts.get(c));
        }
    }

    static void generateBatchCommands(List<FileInfo> files) {
        System.out.println("\nBatch Rename Commands:");
        for (FileInfo f : files) {
            if (f.valid && !f.category.equals("Unknown")) {
                System.out.println("rename " + f.originalName + " -> " + f.newName);
            } else {
                System.out.println("skip " + f.originalName + " (invalid or unknown)");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();
        Map<String,Integer> counter = new HashMap<>();
        String date = "2025-08-22"; 

        System.out.println("Enter files with optional content (end with empty line):");
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            System.out.println("Enter sample content for " + line + " (single line):");
            String content = sc.nextLine();
            FileInfo f = parseFileName(line, content);
            if (f.valid) generateNewName(f, counter, date);
            files.add(f);
        }

        generateReport(files);
        generateBatchCommands(files);
    }
}
