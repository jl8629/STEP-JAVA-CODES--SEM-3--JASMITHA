import java.util.*;

public class EmailAnalysis {
    static class EmailInfo {
        String email, username, domain, domainName, extension;
        boolean valid;
        EmailInfo(String email, String username, String domain, String domainName, String extension, boolean valid) {
            this.email = email;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
            this.valid = valid;
        }
    }

    static boolean validateEmail(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (at == -1 || at != lastAt) return false;
        int dot = email.indexOf('.', at);
        if (dot == -1) return false;
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }

    static EmailInfo extractComponents(String email) {
        if (!validateEmail(email)) {
            return new EmailInfo(email, "-", "-", "-", "-", false);
        }
        int at = email.indexOf('@');
        int dot = email.indexOf('.', at);
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        String domainName = domain.substring(0, domain.indexOf('.'));
        String extension = domain.substring(domain.lastIndexOf('.') + 1);
        return new EmailInfo(email, username, domain, domainName, extension, true);
    }

    static void analyzeStatistics(List<EmailInfo> list) {
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        for (EmailInfo e : list) {
            if (e.valid) {
                validCount++;
                totalUsernameLength += e.username.length();
                domainCount.put(e.domainName, domainCount.getOrDefault(e.domainName, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String commonDomain = "-";
        int maxCount = 0;
        for (String d : domainCount.keySet()) {
            if (domainCount.get(d) > maxCount) {
                maxCount = domainCount.get(d);
                commonDomain = d;
            }
        }
        double avgLength = validCount > 0 ? (double) totalUsernameLength / validCount : 0;
        System.out.println("\nAnalysis Report:");
        System.out.println("Valid Emails: " + validCount);
        System.out.println("Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain: " + commonDomain);
        System.out.printf("Average Username Length: %.2f\n", avgLength);
    }

    static void displayTable(List<EmailInfo> list) {
        System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n", "Email", "Username", "Domain", "Domain Name", "Extension", "Valid?");
        System.out.println("\n");
        for (EmailInfo e : list) {
            System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n", e.email, e.username, e.domain, e.domainName, e.extension, e.valid);
        }
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        List<EmailInfo> list = new ArrayList<>();
        System.out.print("Enter number of emails: ");
        int n = Integer.parseInt(j.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            String email = j.nextLine();
            list.add(extractComponents(email));
        }
        displayTable(list);
        analyzeStatistics(list);
        
        j.close();
    }
}
