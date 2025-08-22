//Program 1

public class StringManipulation {
    public static void main(String[] args) {
        String str1 = "Java Programming";
        System.out.println(str1);

        char[] charArray = {'J', 'a', 'v', 'a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str2 = new String(charArray);
        System.out.println(str2);

        StringBuilder sb = new StringBuilder();
        sb.append("Java");
        sb.append(" ");
        sb.append("Programming");
        String str3 = sb.toString();
        System.out.println(str3);

        String str0 = new String("Java Programming");
        System.out.println(str0);

        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str3 == str1);

        System.out.println(str1.equals(str2));
        System.out.println(str2.equals(str3));
        System.out.println(str3.equals(str1));

        System.out.println("\"Code is Poetry\"- Unknown");
        System.out.println("Path :C\\Java\\Projects");

    }
}