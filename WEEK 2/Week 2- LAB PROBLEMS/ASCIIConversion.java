import java.util.*;

public class ASCIIConversion {
    static char toUpper(char c) {
        if (c >= 97 && c <= 122) return (char)(c - 32);
        return c;
    }

    static char toLower(char c) {
        if (c >= 65 && c <= 90) return (char)(c + 32);
        return c;
    }

    static String convertToUpper(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) res.append(toUpper(s.charAt(i)));
        return res.toString();
    }

    static String convertToLower(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) res.append(toLower(s.charAt(i)));
        return res.toString();
    }

    static String convertToTitle(String s) {
        StringBuilder res = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                res.append(c);
                newWord = true;
            } else {
                if (newWord) {
                    res.append(toUpper(c));
                    newWord = false;
                } else {
                    res.append(toLower(c));
                }
            }
        }
        return res.toString();
    }

    static void displayTable(String input, String manualUpper, String manualLower, String manualTitle) {
        String builtUpper = input.toUpperCase();
        String builtLower = input.toLowerCase();
        System.out.printf("%-20s %-20s %-20s %-20s\n", "Conversion", "Manual", "Built-in", "Match?");
        System.out.println("\n");
        System.out.printf("%-20s %-20s %-20s %-20s\n", "Uppercase", manualUpper, builtUpper, manualUpper.equals(builtUpper));
        System.out.printf("%-20s %-20s %-20s %-20s\n", "Lowercase", manualLower, builtLower, manualLower.equals(builtLower));
        System.out.printf("%-20s %-20s %-20s %-20s\n", "Title Case", manualTitle, "N/A", "N/A");
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = j.nextLine();
        String manualUpper = convertToUpper(input);
        String manualLower = convertToLower(input);
        String manualTitle = convertToTitle(input);
        displayTable(input, manualUpper, manualLower, manualTitle);
        
        j.close();
    }
}

