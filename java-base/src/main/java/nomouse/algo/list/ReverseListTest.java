package nomouse.algo.list;

import nomouse.algo.ListNode;

/**
 * @author nomouse
 * @date 2021/9/7
 */
public class ReverseListTest {

    public static ListNode reverseH(ListNode head) {

        // 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
        // -1>1>2>3>4>5
        // -1>2>1>3>4>5
        //  d   c r
        // -1>3>2>1>4>5
        // 虚拟头
        ListNode dummy = new ListNode(-1, head);
        // 当前
        ListNode cur = head;
        while (cur.next != null) {
            ListNode remove = cur.next;
            cur.next = remove.next;
            remove.next = dummy.next;
            dummy.next = remove;
        }

        return dummy.next;
    }

    public static ListNode reverseI(ListNode head) {

        // 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
        //
        // 虚拟头
        ListNode pre = null;
        // 当前
        ListNode cur = head;
        // 后一个
        ListNode tmp;
        while (cur != null) {
            tmp = cur.next;
            // 反转
            cur.next = pre;
            // 向后移动
            pre = cur;
            cur = tmp;
        }

        return pre;
    }

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

        ListNode reserve = reverseH(node);
        System.out.println(reserve);
    }
}
