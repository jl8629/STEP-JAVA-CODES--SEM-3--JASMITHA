//Question 2

import java.util.*;
public class UniqueChars {
    public static int length(String s) {
        int count = 0;
        for (char c : s.toCharArray()) count++;
        return count;
    }
    public static char[] unique(String s) {
        char[] temp = new char[length(s)];
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean found = false;
            for (int k = 0; k < i; k++) if (s.charAt(i) == s.charAt(k)) found = true;
            if (!found) temp[idx++] = s.charAt(i);
        }
        return Arrays.copyOf(temp, idx);
    }
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter a string to find its unique characters: ");
        String s = j.nextLine();
        char[] res = unique(s);
        System.out.println("Unique characters:");
        for (char c : res) System.out.print(c+ " ");

        j.close();
    }
}
