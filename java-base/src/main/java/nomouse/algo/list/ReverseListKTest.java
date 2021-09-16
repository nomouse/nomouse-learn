package nomouse.algo.list;

/**
 * @author nomouse
 * @date 2021/9/7
 */
public class ReverseListKTest {

    public static ListNode reverseK(ListNode head, int k) {
        return null;
    }

    public static void main(String[] args) {
        ListNode node =
            new ListNode(1,
                new ListNode(2,
                    new ListNode(3,
                        new ListNode(4,
                            new ListNode(5, null)))));

        System.out.println(reverseK(node, 2));
    }
}
