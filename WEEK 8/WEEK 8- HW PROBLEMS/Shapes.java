abstract class Shape {
    abstract double area();
    abstract double perimeter();
    void displayInfo() {
        System.out.println("This is a shape with area and perimeter");
    }
}

class Circle extends Shape {
    private double radius;
    Circle(double radius) {
        this.radius = radius;
    }
    double area() {
        return Math.PI * radius * radius;
    }
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    double area() {
        return length * width;
    }
    double perimeter() {
        return 2 * (length + width);
    }
}

public class Shapes {
    public static void main(String[] args) {
        Circle c = new Circle(5);
        c.displayInfo();
        System.out.println("Circle Area: " + c.area());
        System.out.println("Circle Perimeter: " + c.perimeter());
        Rectangle r = new Rectangle(4, 6);
        r.displayInfo();
        System.out.println("Rectangle Area: " + r.area());
        System.out.println("Rectangle Perimeter: " + r.perimeter());
    }
}
