import java.util.*;

public class StringPerformance {
    static class Result {
        String method;
        long timeTaken;
        int length;
        Result(String method, long timeTaken, int length) {
            this.method = method;
            this.timeTaken = timeTaken;
            this.length = length;
        }
    }

    static Result testStringConcat(int iterations) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s = s + "x";
        }
        long end = System.currentTimeMillis();
        return new Result("String (+)", end - start, s.length());
    }

    static Result testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("x");
        }
        long end = System.currentTimeMillis();
        return new Result("StringBuilder", end - start, sb.length());
    }

    static Result testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("x");
        }
        long end = System.currentTimeMillis();
        return new Result("StringBuffer", end - start, sb.length());
    }

    static void displayTable(Result r1, Result r2, Result r3) {
        System.out.printf("%-15s %-20s %-20s\n", "Method", "Time (ms)", "Final Length");
        System.out.println("\n");
        System.out.printf("%-15s %-20d %-20d\n", r1.method, r1.timeTaken, r1.length);
        System.out.printf("%-15s %-20d %-20d\n", r2.method, r2.timeTaken, r2.length);
        System.out.printf("%-15s %-20d %-20d\n", r3.method, r3.timeTaken, r3.length);
    }

    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int iterations = j.nextInt();
        Result r1 = testStringConcat(iterations);
        Result r2 = testStringBuilder(iterations);
        Result r3 = testStringBuffer(iterations);
        displayTable(r1, r2, r3);

        j.close();
    }
}
