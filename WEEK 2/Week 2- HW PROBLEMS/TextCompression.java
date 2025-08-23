import java.util.*;

public class TextCompression {
    static char[] uniqueChars(String text) {
        StringBuilder u = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (u.indexOf(String.valueOf(c)) == -1) u.append(c);
        }
        char[] arr = new char[u.length()];
        for (int i = 0; i < u.length(); i++) arr[i] = u.charAt(i);
        return arr;
    }

    static int[] countFrequencies(String text, char[] chars) {
        int[] freq = new int[chars.length];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == c) freq[j]++;
            }
        }
        return freq;
    }

    static String[][] createCodes(char[] chars, int[] freq) {
        String[][] map = new String[chars.length][2];
        Character[] symbols = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) symbols[i] = chars[i];
        Arrays.sort(symbols, (a, b) -> {
            int fa = freq[indexOf(chars, a)];
            int fb = freq[indexOf(chars, b)];
            return fb - fa;
        });
        for (int i = 0; i < symbols.length; i++) {
            map[i][0] = String.valueOf(symbols[i]);
            map[i][1] = Integer.toString(i + 1);
        }
        return map;
    }

    static int indexOf(char[] arr, char c) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == c) return i;
        return -1;
    }

    static String compress(String text, String[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (int j = 0; j < map.length; j++) {
                if (map[j][0].equals(String.valueOf(c))) sb.append(map[j][1]);
            }
        }
        return sb.toString();
    }

    static String decompress(String compressed, String[][] map) {
        StringBuilder sb = new StringBuilder();
        String temp = "";
        for (int i = 0; i < compressed.length(); i++) {
            temp += compressed.charAt(i);
            for (int j = 0; j < map.length; j++) {
                if (map[j][1].equals(temp)) {
                    sb.append(map[j][0]);
                    temp = "";
                    break;
                }
            }
        }
        return sb.toString();
    }

    static void displayAnalysis(String text, char[] chars, int[] freq, String[][] map, String compressed, String decompressed) {
        System.out.println("Character Frequency Table:");
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " : " + freq[i]);
        }
        System.out.println("\nCompression Mapping:");
        for (int i = 0; i < map.length; i++) {
            System.out.println(map[i][0] + " -> " + map[i][1]);
        }
        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);
        double ratio = (double) compressed.length() / text.length();
        double efficiency = (1 - ratio) * 100;
        System.out.printf("Compression Efficiency: %.2f%%\n", efficiency);
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String text = j.nextLine();
        char[] chars = uniqueChars(text);
        int[] freq = countFrequencies(text, chars);
        String[][] map = createCodes(chars, freq);
        String compressed = compress(text, map);
        String decompressed = decompress(compressed, map);
        displayAnalysis(text, chars, freq, map, compressed, decompressed);
        j.close();
    }
}
