//Question 2

import java.util.*;
public class StringManipulation {
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String input = j.nextLine();
        String trimmed = input.trim();
        String underscores = trimmed.replace(" ", "_");
        String noDigits = underscores.replaceAll("\\d", "");
        String[] words = noDigits.split("_");
        String joined = String.join(" | ", words);
        System.out.println("No punctuation: " + removePunctuation(trimmed));
        System.out.println("Capitalized words: " + capitalizeWords(trimmed));
        System.out.println("Reversed order: " + reverseWordOrder(trimmed));
        System.out.println("Word frequency:");
        countWordFrequency(trimmed);
        System.out.println("Joined words: " + joined);
        j.close();
    }
    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }
    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1).toLowerCase()).append(" ");
        return sb.toString().trim();
    }
    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String,Integer> freq = new LinkedHashMap<>();
        for (String w : words) freq.put(w, freq.getOrDefault(w,0)+1);
        for (Map.Entry<String,Integer> e : freq.entrySet()) System.out.println(e.getKey() + ": " + e.getValue());
    }
}
