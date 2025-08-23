//Question 1

import java.util.Scanner;
public class StringBuiltInMethods {
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.println("Enter a string:");
        String sampleText = j.nextLine();
        System.out.println("Original length including spaces: " + sampleText.length());
        String trimmed = sampleText.trim();
        System.out.println("Length after trimming: " + trimmed.length());
        if (sampleText.length() > 5) {
            System.out.println("Character at index 5: " + sampleText.charAt(5));
        } else {
            System.out.println("String too short to get character at index 5");
        }
        int progIndex = sampleText.indexOf("Programming");
        if (progIndex != -1) {
            System.out.println("Substring 'Programming': " + sampleText.substring(progIndex, progIndex + 11));
        } else {
            System.out.println("'Programming' not found in the string");
        }
        System.out.println("Index of 'Fun': " + sampleText.indexOf("Fun"));
        System.out.println("Contains 'Java': " + sampleText.contains("Java"));
        System.out.println("Starts with 'Java' after trimming: " + trimmed.startsWith("Java"));
        System.out.println("Ends with '!': " + sampleText.endsWith("!"));
        System.out.println("Uppercase: " + sampleText.toUpperCase());
        System.out.println("Lowercase: " + sampleText.toLowerCase());
        System.out.println("Vowel count: " + countVowels(sampleText));
        System.out.print("Occurrences of 'a': ");
        findAllOccurrences(sampleText, 'a');
        j.close();
    }
    public static int countVowels(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }
    public static void findAllOccurrences(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) System.out.print(i + " ");
        }
        System.out.println();
    }
}
