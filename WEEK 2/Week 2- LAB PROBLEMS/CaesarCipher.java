import java.util.*;

public class CaesarCipher {
    static String encrypt(String text, int shift) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res.append((char) ((c - 'A' + shift) % 26 + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                res.append((char) ((c - 'a' + shift) % 26 + 'a'));
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    static void displayAscii(String label, String text) {
        System.out.print(label + ": " + text + " | ASCII: ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print((int) text.charAt(i) + " ");
        }
        System.out.println();
    }

    static boolean validate(String original, String decrypted) {
        return original.equals(decrypted);
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = j.nextLine();
        System.out.print("Enter shift value: ");
        int shift = j.nextInt();

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        displayAscii("Original", text);
        displayAscii("Encrypted", encrypted);
        displayAscii("Decrypted", decrypted);

        System.out.println("Validation (Original == Decrypted): " + validate(text, decrypted));
        
        j.close();
    }
}
