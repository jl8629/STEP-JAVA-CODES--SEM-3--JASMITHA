//Program 2

import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your full name (first and last name): ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine();

        System.out.print("Write a sentence about your programming experience: ");
        String experience = scanner.nextLine();

        String[] nameParts = fullName.trim().split("\\s+");
        String firstName = nameParts.length > 0 ? nameParts[0] : "";
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        int characterCount = experience.replace(" ", "").length();

        String upperCaseLang = language.toUpperCase();

        System.out.println("\nSummary: ");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Language (Uppercase): " + upperCaseLang);
        System.out.println("Character Count (excluding spaces): " + characterCount);
        System.out.println("Experience Sentence: \"" + experience + "\"");

        scanner.close();
    }
}

