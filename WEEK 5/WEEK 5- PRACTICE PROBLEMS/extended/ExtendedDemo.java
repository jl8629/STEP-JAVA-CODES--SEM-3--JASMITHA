package extended;
import security.AccessModifierDemo;

public class ExtendedDemo extends AccessModifierDemo {
    public ExtendedDemo(int privateField, String defaultField, double protectedField, boolean publicField) {
        super(privateField, defaultField, protectedField, publicField);
    }

    public void testInheritedAccess() {
        // System.out.println(privateField); // error
        // System.out.println(defaultField); // error
        System.out.println(protectedField);
        System.out.println(publicField);

        // privateMethod(); // error
        // defaultMethod(); // error
        protectedMethod();
        publicMethod();
    }

    @Override
    protected void protectedMethod() {
        System.out.println("Overridden protected method in subclass called");
    }

    public static void main(String[] args) {
        ExtendedDemo child = new ExtendedDemo(10, "Hello", 30.5, true);
        child.testInheritedAccess();

        AccessModifierDemo parent = new AccessModifierDemo(20, "World", 40.5, false);
        // parent.protectedField; // error
        System.out.println(parent.publicField);
        parent.publicMethod();
    }
}
