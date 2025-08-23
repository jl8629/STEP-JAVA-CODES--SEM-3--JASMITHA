//Question 7

import java.util.*;
public class Palindrome {
    public static boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter a string to check if it is a palindrome: ");
        String s = j.nextLine();
        if (isPalindrome(s)) System.out.println("Palindrome");
        else System.out.println("Not a Palindrome");

        j.close();
    }
}
