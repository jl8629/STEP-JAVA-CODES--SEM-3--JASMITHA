import java.util.*;

public class SpellChecker {
    static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isLetter(c)) {
                if (i > start) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    static int stringDistance(String w1, String w2) {
        int[][] dp = new int[w1.length() + 1][w2.length() + 1];
        for (int i = 0; i <= w1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= w2.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= w1.length(); i++) {
            for (int j = 1; j <= w2.length(); j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                 Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[w1.length()][w2.length()];
    }

    static String suggestWord(String word, String[] dictionary) {
        String suggestion = word;
        int minDist = Integer.MAX_VALUE;
        for (String d : dictionary) {
            int dist = stringDistance(word.toLowerCase(), d.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                suggestion = d;
            }
        }
        return (minDist <= 2) ? suggestion : word;
    }

    static void displayResults(List<String> words, String[] dictionary) {
        System.out.printf("%-15s %-15s %-10s %-15s\n", "Word", "Suggestion", "Distance", "Status");
        System.out.println("\n");
        for (String w : words) {
            String suggestion = w;
            int minDist = Integer.MAX_VALUE;
            for (String d : dictionary) {
                int dist = stringDistance(w.toLowerCase(), d.toLowerCase());
                if (dist < minDist) {
                    minDist = dist;
                    suggestion = d;
                }
            }
            String status = (minDist == 0) ? "Correct" : (minDist <= 2 ? "Misspelled" : "Unknown");
            System.out.printf("%-15s %-15s %-10d %-15s\n", w, (minDist == 0 ? "-" : suggestion), minDist, status);
        }
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = j.nextLine();
        String[] dictionary = {"hello", "world", "java", "spell", "checker", "program", "example"};
        List<String> words = extractWords(sentence);
        displayResults(words, dictionary);
        j.close();
    }
}
