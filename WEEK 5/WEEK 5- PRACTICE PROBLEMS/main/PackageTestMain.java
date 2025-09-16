package main;
import security.AccessModifierDemo;

public class PackageTestMain {
    public static void main(String[] args) {
        AccessModifierDemo obj = new AccessModifierDemo(1, "Test", 2.5, true);
        // System.out.println(obj.privateField); // error
        // System.out.println(obj.defaultField); // error
        // System.out.println(obj.protectedField); // error
        System.out.println(obj.publicField);

        // obj.privateMethod(); // error
        // obj.defaultMethod(); // error
        // obj.protectedMethod(); // error
        obj.publicMethod();

        obj.testInternalAccess();
    }
}

