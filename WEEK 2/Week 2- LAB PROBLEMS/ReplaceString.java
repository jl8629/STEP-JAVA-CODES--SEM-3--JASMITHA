//Question 1

import java.util.Scanner;
import java.util.*;
public class ReplaceString {
    public static List<Integer> findOccurrences(String text, String find) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);

        while(index != -1) {
            positions.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return positions;
    }

    public static String manualReplace (String text, String find, String replace) {
        List<Integer> positions = findOccurrences(text, find);
        if (positions.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        int j = 0;

        while(i < text.length()) {
            if ( j < positions.size() && i == positions.get(j)) {
                result.append(replace);
                i += find.length();
                j++;
            }
            else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareWithBuiltIn(String manual, String original, String find, String replace) {
        String builtIn = original.replace(find, replace);
        return manual.equals(builtIn);
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);

        System.out.println("Enter main text: ");
        String text = j.nextLine();

        System.out.println("Enter substring to find: ");
        String find = j.nextLine();

        System.out.println("Enter substring to replace: ");
        String replace = j.nextLine();

        String manualResult = manualReplace(text, find, replace);
        boolean same = compareWithBuiltIn(manualResult, text, find, replace);

        System.out.println("Manual Replace result: "+manualResult);
        System.out.println("Built in Replace result: "+text.replace(find,replace));
        System.out.println("Are both results equal: "+same);

        j.close();
    }
}