import java.util.*;

public class TextFormatter {
    static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (i > start) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        List<String> current = new ArrayList<>();
        int len = 0;

        for (String w : words) {
            if (len + w.length() + current.size() > width) {
                int spaces = width - len;
                int gaps = current.size() - 1;
                StringBuilder justified = new StringBuilder();
                if (gaps > 0) {
                    int spaceEach = spaces / gaps;
                    int extra = spaces % gaps;
                    for (int i = 0; i < current.size(); i++) {
                        justified.append(current.get(i));
                        if (i < gaps) {
                            for (int s = 0; s < spaceEach; s++) justified.append(" ");
                            if (i < extra) justified.append(" ");
                        }
                    }
                } else {
                    justified.append(current.get(0));
                    while (justified.length() < width) justified.append(" ");
                }
                lines.add(justified.toString());
                current.clear();
                len = 0;
            }
            current.add(w);
            len += w.length();
        }
        StringBuilder last = new StringBuilder();
        for (int i = 0; i < current.size(); i++) {
            if (i > 0) last.append(" ");
            last.append(current.get(i));
        }
        while (last.length() < width) last.append(" ");
        lines.add(last.toString());
        return lines;
    }

    static List<String> centerText(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();
        for (String l : lines) {
            int padding = (width - l.trim().length()) / 2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < padding; i++) sb.append(" ");
            sb.append(l.trim());
            while (sb.length() < width) sb.append(" ");
            centered.add(sb.toString());
        }
        return centered;
    }

    static long measureWithConcat(List<String> words, int width) {
        long start = System.nanoTime();
        String line = "";
        int len = 0;
        for (String w : words) {
            if (len + w.length() + 1 > width) {
                line = "";
                len = 0;
            }
            line = line + w + " ";
            len += w.length();
        }
        return System.nanoTime() - start;
    }

    static void display(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String l = lines.get(i);
            System.out.printf("Line %d (%d chars): %s\n", i + 1, l.length(), l);
        }
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = j.nextLine();
        System.out.print("Enter line width: ");
        int width = j.nextInt();
        j.close();

        System.out.println("\nOriginal Text:\n" + text);

        List<String> words = extractWords(text);
        List<String> justified = justifyText(words, width);
        List<String> centered = centerText(justified, width);

        System.out.println("\nLeft-Justified Text:");
        display(justified);

        System.out.println("\nCenter-Aligned Text:");
        display(centered);

        long builderTime = System.nanoTime();
        justifyText(words, width);
        builderTime = System.nanoTime() - builderTime;

        long concatTime = measureWithConcat(words, width);

        System.out.println("\nPerformance Comparison:");
        System.out.printf("StringBuilder Time: %d ns\n", builderTime);
        System.out.printf("String Concatenation Time: %d ns\n", concatTime);
    }
}
