//Question 4

import java.util.*;
public class CharFrequency {
    public static String[][] frequency(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) freq[s.charAt(i)]++;
        char[] unique = s.chars().distinct().mapToObj(c -> (char)c).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString().toCharArray();
        String[][] res = new String[unique.length][2];
        for (int i = 0; i < unique.length; i++) {
            res[i][0] = String.valueOf(unique[i]);
            res[i][1] = String.valueOf(freq[unique[i]]);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter a string to find the frequency of its characters: ");
        String s = j.nextLine();
        String[][] out = frequency(s);
        System.out.println("Character - Frequency");
        for (String[] row : out) System.out.println(row[0] + " - " + row[1]);

        j.close();
    }
}
