//Program 2

import java.util.Scanner;

public class TextSplit {
    public static int LengthFinding (String str) {
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

    public static String[] splitWithoutSplit(String text) {
        int length = LengthFinding(text);

        int wordCount = 1;

        for (int i = 0 ; i < length ; i++) {
            if(text.charAt(i) == ' ') {
                wordCount++;
            }
        }

        int[] spaceIndexes = new int[wordCount - 1];
        int idx = 0;
        for(int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[idx++] = i;
            }
        }

        String[] words = new String[wordCount];
        int start = 0;
        for(int i = 0; i < wordCount ; i++) {
            int end = (i < wordCount - 1) ? spaceIndexes[i] : length;
            StringBuilder sb = new StringBuilder();

            for(int j = start; j < end; j++) {
                sb.append(text.charAt(j));
            }
            words[i] = sb.toString();
            start = end + 1;
        }
        return words;
    }

    public static boolean compareArrays(String[] arr1, String[] arr2) {
        if(arr1.length != arr2.length)
        return false;

        for(int i = 0; i < arr1.length; i++) {
            if(!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);

        System.out.println("Enter a sentence: ");
        String text = j.nextLine();

        String[] customSplit = splitWithoutSplit(text);

        String[] builtInSplit = text.split(" ");

        boolean areEqual = compareArrays(customSplit, builtInSplit);

        System.out.println("\nCustom Split: ");
        for(String word : customSplit) {
            System.out.println(word);
        }

        System.out.println("Built in Split: ");
        for(String word : builtInSplit) {
            System.out.println(word);
        }

        System.out.println("Are both equal: "+areEqual);

        j.close();
    }
}
