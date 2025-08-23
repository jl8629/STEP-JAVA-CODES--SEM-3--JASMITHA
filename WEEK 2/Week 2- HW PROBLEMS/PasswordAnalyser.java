import java.util.*;

public class PasswordAnalyser {
    static int[] analyzePassword(String pwd) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            int ascii = (int) c;
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digit++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    static boolean hasCommonPattern(String pwd) {
        String[] patterns = {"123", "abc", "qwerty", "password"};
        for (String p : patterns) {
            if (pwd.toLowerCase().contains(p)) return true;
        }
        return false;
    }

    static int calculateScore(String pwd, int[] counts) {
        int score = 0;
        int length = pwd.length();
        if (length > 8) score += (length - 8) * 2;
        if (counts[0] > 0) score += 10;
        if (counts[1] > 0) score += 10;
        if (counts[2] > 0) score += 10;
        if (counts[3] > 0) score += 10;
        if (hasCommonPattern(pwd)) score -= 10;
        return score;
    }

    static String getStrengthLevel(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+<>?";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));
        String all = upper + lower + digits + special;
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) chars.add(sb.charAt(i));
        Collections.shuffle(chars);
        StringBuilder finalPwd = new StringBuilder();
        for (char c : chars) finalPwd.append(c);
        return finalPwd.toString();
    }

    static void displayResults(List<String> passwords) {
        System.out.printf("%-15s %-7s %-7s %-7s %-7s %-10s %-8s %-10s\n",
                "Password", "Length", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        System.out.println("\n");
        for (String pwd : passwords) {
            int[] counts = analyzePassword(pwd);
            int score = calculateScore(pwd, counts);
            String strength = getStrengthLevel(score);
            System.out.printf("%-15s %-7d %-7d %-7d %-7d %-10d %-8d %-10s\n",
                    pwd, pwd.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
        }
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        List<String> passwords = new ArrayList<>();
        System.out.print("Enter number of passwords to analyze: ");
        int n = j.nextInt();
        j.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            passwords.add(j.nextLine());
        }
        System.out.println("\nPassword Strength Analysis:");
        displayResults(passwords);
        System.out.print("\nEnter desired length for new strong password: ");
        int len = j.nextInt();
        String strongPwd = generatePassword(len);
        System.out.println("Generated Strong Password: " + strongPwd);
        j.close();
    }
}
