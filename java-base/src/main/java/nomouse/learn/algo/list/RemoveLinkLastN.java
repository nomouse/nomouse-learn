package nomouse.learn.algo.list;

import nomouse.learn.algo.ListNode;

/**
 * @author nomouse
 * @date 2021/9/21
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class RemoveLinkLastN {

    public ListNode remove(ListNode node, int n) {

        // [1,2,3,4,5] n=2
        // slow = 0, fast = n
        ListNode dummy = new ListNode(-1, node);

        ListNode slow = dummy;
        ListNode fast = node;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node =
            new ListNode(1,
                new ListNode(2,
                    new ListNode(3,
                        new ListNode(4,
                            new ListNode(5, null)))));

        ListNode reserve = new RemoveLinkLastN()
            .remove(node, 1);
        System.out.println(reserve);
    }
}
