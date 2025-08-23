//Question 3

import java.util.Scanner;
public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.println("Enter a string for ASCII processing:");
        String input = j.nextLine();
        for (char ch : input.toCharArray()) {
            System.out.println(ch + " -> " + (int) ch + " -> " + classifyCharacter(ch));
            if (Character.isLetter(ch)) {
                char toggled = toggleCase(ch);
                System.out.println(toggled + " ASCII: " + (int) toggled);
                System.out.println("Case diff: " + Math.abs(((int)Character.toUpperCase(ch)) - ((int)Character.toLowerCase(ch))));
            }
        }
        System.out.println("Caesar cipher with shift 3: " + caesarCipher(input, 3));
        j.close();
    }
    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        if (Character.isLowerCase(ch)) return "Lowercase Letter";
        if (Character.isDigit(ch)) return "Digit";
        return "Special Character";
    }
    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char)(ch + 32);
        if (Character.isLowerCase(ch)) return (char)(ch - 32);
        return ch;
    }
    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                sb.append((char)((c - base + shift) % 26 + base));
            } else sb.append(c);
        }
        return sb.toString();
    }
}
