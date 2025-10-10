class Address implements Cloneable {
    String city;
    String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[City: " + city + ", Street: " + street + "]";
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    protected Object shallowClone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected Object deepClone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.address = new Address(address.city, address.street);
        return cloned;
    }

    @Override
    public String toString() {
        return "Person [Name: " + name + ", Address: " + address + "]";
    }
}

public class CloningDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("New York", "5th Avenue");
        Person p1 = new Person("John", addr);

        Person shallowCopy = (Person) p1.shallowClone();
        Person deepCopy = (Person) p1.deepClone();

        System.out.println("Original: " + p1);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy: " + deepCopy);

        p1.address.city = "Los Angeles";

        System.out.println("\nAfter modifying original address:");
        System.out.println("Original: " + p1);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy: " + deepCopy);
    }
}
