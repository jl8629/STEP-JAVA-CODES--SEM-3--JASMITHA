import java.util.*;

public class StackUsingList {
    private List<Integer> stack = new ArrayList<>();

    public void push(int item) {
        stack.add(item);
    }

    public int pop() {
        if (!isEmpty())
            return stack.remove(stack.size() - 1);
        else
            throw new EmptyStackException();
    }

    public int peek() {
        if (!isEmpty())
            return stack.get(stack.size() - 1);
        else
            throw new EmptyStackException();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void display() {
        System.out.println("Stack: " + stack);
    }

    public static void main(String[] args) {
        StackUsingList s = new StackUsingList();
        s.push(10);
        s.push(20);
        s.push(30);
        s.display();
        System.out.println("Top element: " + s.peek());
        System.out.println("Popped element: " + s.pop());
        s.display();
        System.out.println("Is stack empty? " + s.isEmpty());
    }
}
