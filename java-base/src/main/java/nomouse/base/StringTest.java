package nomouse.base;

/**
 * @author nomouse
 * @date 2021/7/28
 */
public class StringTest {

    private static void equalsTest() {
        String a = "123";
        String b = "123";
        String c = new String("123");
        String d = new String("123");
        System.out.println(a == "123");
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(a.equals(d));
    }

    public static void main(String[] args) {

        equalsTest();
    }
}
