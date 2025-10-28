import java.util.*;

public class StackUndoFeature {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> actions = new Stack<>();
        String text = "";
        while (true) {
            System.out.println("\n1. Add Text\n2. Undo\n3. Display\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.print("Enter text to add: ");
                String newText = sc.nextLine();
                actions.push(text);
                text += newText;
            } else if (choice == 2) {
                if (!actions.isEmpty())
                    text = actions.pop();
                else
                    System.out.println("Nothing to undo");
            } else if (choice == 3) {
                System.out.println("Current Text: " + text);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
