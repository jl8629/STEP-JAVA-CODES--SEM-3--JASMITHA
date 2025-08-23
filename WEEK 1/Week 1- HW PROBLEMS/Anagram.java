//Question 8

import java.util.*;
public class Anagram {
    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] freqA = new int[256], freqB = new int[256];
        for (char c : a.toCharArray()) freqA[c]++;
        for (char c : b.toCharArray()) freqB[c]++;
        return Arrays.equals(freqA, freqB);
    }
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter the first string: ");
        String a = j.nextLine();
        System.out.print("Enter the second string: ");
        String b = j.nextLine();
        if (isAnagram(a, b)) System.out.println("Anagrams");
        else System.out.println("Not Anagrams");

        j.close();
    }
}
