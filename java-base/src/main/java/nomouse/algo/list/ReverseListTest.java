package nomouse.algo.list;

/**
 * @author nomouse
 * @date 2021/9/7
 */
public class ReverseListTest {

    public static ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void main(String[] args) {
        ListNode node =
            new ListNode(1,
                new ListNode(2,
                    new ListNode(3,
                        new ListNode(4,
                            new ListNode(5, null)))));

        ListNode reserve = reverse(node);
        System.out.println(reserve);
    }
}
