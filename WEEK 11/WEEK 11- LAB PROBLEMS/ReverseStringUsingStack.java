import java.util.*;

public class ReverseStringUsingStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray())
            stack.push(ch);

        String reversed = "";
        while (!stack.isEmpty())
            reversed += stack.pop();

        System.out.println("Reversed String: " + reversed);
    }
}
