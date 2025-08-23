//Program 1

import java.util.Scanner;

public class StringLength {
    public static int WithoutLengthMethod (String str) {
        int count = 0;
        try {
            while (true) { 
                str.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {

        }
        return count;
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);

        System.out.println("Enter a string: ");
        String input = j.next();

        int length = WithoutLengthMethod(input);

        System.out.println("Length without length method: "+length);
        System.out.println("Length using length method: "+input.length());

        j.close();
    }
}
