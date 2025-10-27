import java.util.*;

public class TextEditorUndo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command (TYPE <word>/UNDO/PRINT/EXIT): ");
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("TYPE")) {
                String word = sc.next();
                stack.push(word);
            } else if (cmd.equalsIgnoreCase("UNDO")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (cmd.equalsIgnoreCase("PRINT")) {
                for (String word : stack) System.out.print(word + " ");
                System.out.println();
            } else if (cmd.equalsIgnoreCase("EXIT")) {
                break;
            }
        }
    }
}
