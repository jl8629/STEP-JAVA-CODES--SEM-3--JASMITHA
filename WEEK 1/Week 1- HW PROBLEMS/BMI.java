//Question 1

import java.util.*;
public class BMI {
    public static double calculateBMI(double weight, double height) {
        height = height / 100;
        return weight / (height * height);
    }
    public static String status(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }
    public static void main(String[] args) {
        Scanner j = new Scanner(System.in);
        double[][] hw = new double[10][2];
        String[][] result = new String[10][4];
        System.out.println("Enter weight (kg) and height (cm) for 10 persons:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Person " + (i+1) + " - Weight (kg): ");
            hw[i][0] = j.nextDouble();
            System.out.print("Person " + (i+1) + " - Height (cm): ");
            hw[i][1] = j.nextDouble();
        }
        for (int i = 0; i < 10; i++) {
            double bmi = calculateBMI(hw[i][0], hw[i][1]);
            result[i][0] = String.valueOf(hw[i][1]);
            result[i][1] = String.valueOf(hw[i][0]);
            result[i][2] = String.format("%.2f", bmi);
            result[i][3] = status(bmi);
        }
        System.out.println("\nHeight(cm)\tWeight(kg)\tBMI\tStatus");
        for (String[] row : result) System.out.println(String.join("\t", row));

        j.close();
    }
}
