//Program 4

import java.util.Scanner;
import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        return input;
    }

    public static void analyzeText(String text) {
        String[] words = text.split(" ");
        int wordCount = words.length;
        int charCount = text.replace(" ", "").length();

        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        System.out.println("\nText Analysis: ");
        System.out.println("Total Words: " + wordCount);
        System.out.println("Total Characters (no spaces): " + charCount);
        System.out.println("Longest Word: " + longestWord);
    }

    public static String[] getWordsSorted(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z ]", "");
        String[] words = cleanedText.toLowerCase().split(" ");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SIMPLE TEXT PROCESSOR ===");
        System.out.print("Enter a short paragraph: ");
        String inputText = scanner.nextLine();

        String cleaned = cleanInput(inputText);
        System.out.println("\nCleaned Text: " + cleaned);

        analyzeText(cleaned);

        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("\nWords in Alphabetical Order:");
        for (String word : sortedWords) {
            System.out.println(word);
        }

        System.out.print("\nEnter a word to search: ");
        String searchWord = scanner.nextLine().toLowerCase();
        boolean found = Arrays.asList(sortedWords).contains(searchWord);
        if (found) {
            System.out.println("'" + searchWord + "' is found in the text.");
        } else {
            System.out.println("'" + searchWord + "' is NOT found in the text.");
        }

        scanner.close();
    }
}
