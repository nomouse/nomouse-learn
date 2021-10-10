package nomouse.algo.list;

import nomouse.algo.ListNode;

/**
 * @author nomouse
 * @date 2021/9/21 题意：删除链表中等于给定值 val 的所有节点。
 */
public class RemoveLinkElement {

    public ListNode remove(ListNode r, int val) {
        ListNode pre = new ListNode(0, r);

        ListNode cur = pre;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode node =
            new ListNode(1,
                new ListNode(2,
                    new ListNode(3,
                        new ListNode(4,
                            new ListNode(5, null)))));

        ListNode reserve = new RemoveLinkElement()
            .remove(node, 3);
        System.out.println(reserve);
    }
}
