import java.util.*;

public class TextBasedCalculator {
    static Scanner j = new Scanner(System.in);

    static boolean validate(String exp) {
        int balance = 0;
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (!(c == ' ' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || (c >= '0' && c <= '9')))
                return false;
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    static int[] parseNumbers(String exp, List<Character> ops) {
        List<Integer> nums = new ArrayList<>();
        int i = 0;
        while (i < exp.length()) {
            char c = exp.charAt(i);
            if (c == ' ') { i++; continue; }
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                ops.add(c);
                i++;
            } else {
                int j = i;
                while (j < exp.length() && exp.charAt(j) >= '0' && exp.charAt(j) <= '9') j++;
                nums.add(Integer.parseInt(exp.substring(i, j)));
                i = j;
            }
        }
        int[] arr = new int[nums.size()];
        for (i = 0; i < nums.size(); i++) arr[i] = nums.get(i);
        return arr;
    }

    static StringBuilder steps = new StringBuilder();

    static int evalNoParen(String exp) {
        List<Character> ops = new ArrayList<>();
        int[] nums = parseNumbers(exp, ops);
        List<Integer> numbers = new ArrayList<>();
        for (int n : nums) numbers.add(n);
        for (int i = 0; i < ops.size();) {
            char op = ops.get(i);
            if (op == '*' || op == '/') {
                int a = numbers.get(i), b = numbers.get(i + 1);
                int r = (op == '*') ? a * b : a / b;
                numbers.set(i, r);
                numbers.remove(i + 1);
                ops.remove(i);
                steps.append(a + " " + op + " " + b + " = " + r + "\n");
            } else i++;
        }
        while (!ops.isEmpty()) {
            char op = ops.remove(0);
            int a = numbers.remove(0), b = numbers.remove(0);
            int r = (op == '+') ? a + b : a - b;
            numbers.add(0, r);
            steps.append(a + " " + op + " " + b + " = " + r + "\n");
        }
        return numbers.get(0);
    }

    static int eval(String exp) {
        while (exp.contains("(")) {
            int close = exp.indexOf(")");
            int open = exp.lastIndexOf("(", close);
            int val = evalNoParen(exp.substring(open + 1, close));
            exp = exp.substring(0, open) + val + exp.substring(close + 1);
        }
        return evalNoParen(exp);
    }

    public static void main(String[] args) {
        System.out.println("Enter expression:");
        String exp = j.nextLine();
        if (!validate(exp)) {
            System.out.println("Invalid expression");
            return;
        }
        steps.append("Original: " + exp + "\n");
        int result = eval(exp);
        steps.append("Final Result: " + result);
        System.out.println(steps.toString());
    }
}
