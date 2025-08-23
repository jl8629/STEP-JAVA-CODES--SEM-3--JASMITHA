//Program 4

import java.util.Scanner;

public class ShortestLongestStrings {

    public static String[] splitIntoWords(String text) {
        text = text + " ";
        String word = "";
        int count = 0;
        for (int i = 0; i < getLength(text); i++) {
            if (text.charAt(i) != ' ') {
                word += text.charAt(i);
            } else {
                if (!word.equals("")) {
                    count++;
                    word = "";
                }
            }
        }
        String[] words = new String[count];
        word = "";
        int index = 0;
        for (int i = 0; i < getLength(text); i++) {
            if (text.charAt(i) != ' ') {
                word += text.charAt(i);
            } else {
                if (!word.equals("")) {
                    words[index++] = word;
                    word = "";
                }
            }
        }
        return words;
    }

    public static int getLength(String str) {
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

    public static String[][] getWordLengthArray(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getLength(words[i]));
        }
        return result;
    }

    public static int[] findShortestAndLongest(String[][] wordLengthArray) {
        int shortestIndex = 0;
        int longestIndex = 0;
        for (int i = 1; i < wordLengthArray.length; i++) {
            int currentLength = Integer.parseInt(wordLengthArray[i][1]);
            int shortestLength = Integer.parseInt(wordLengthArray[shortestIndex][1]);
            int longestLength = Integer.parseInt(wordLengthArray[longestIndex][1]);
            if (currentLength < shortestLength) {
                shortestIndex = i;
            }
            if (currentLength > longestLength) {
                longestIndex = i;
            }
        }
        return new int[]{shortestIndex, longestIndex};
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String text = j.nextLine();
        String[] words = splitIntoWords(text);
        String[][] wordLengthArray = getWordLengthArray(words);
        int[] indices = findShortestAndLongest(wordLengthArray);
        System.out.println("\nWord and their lengths:");
        for (String[] pair : wordLengthArray) {
            System.out.println(pair[0] + " - " + pair[1]);
        }
        System.out.println("\nShortest word: " + wordLengthArray[indices[0]][0] +
                           " (" + wordLengthArray[indices[0]][1] + ")");
        System.out.println("Longest word: " + wordLengthArray[indices[1]][0] +
                           " (" + wordLengthArray[indices[1]][1] + ")");

        j.close();

    }
}

