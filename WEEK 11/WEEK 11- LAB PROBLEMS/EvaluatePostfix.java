import java.util.*;

public class EvaluatePostfix {
    public static int evaluatePostfix(String expr) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expr.split(" ");

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else {
                int val2 = stack.pop();
                int val1 = stack.pop();
                switch (token) {
                    case "+": stack.push(val1 + val2); break;
                    case "-": stack.push(val1 - val2); break;
                    case "*": stack.push(val1 * val2); break;
                    case "/": stack.push(val1 / val2); break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter postfix expression: ");
        String expr = sc.nextLine();
        System.out.println("Result: " + evaluatePostfix(expr));
    }
}
