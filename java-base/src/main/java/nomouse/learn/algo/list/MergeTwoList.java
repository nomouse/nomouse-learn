package nomouse.learn.algo.list;

import nomouse.learn.algo.ListNode;

/**
 * 合并两个有序链表
 *
 * @author nomouse
 * @date 2021/11/18
 */
public class MergeTwoList {

    public static ListNode mergeTwoLists(ListNode l1,
        ListNode l2) {
        ListNode dummy = new ListNode();

        ListNode pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                pre.next = l2;
                l2 = l2.next;
            } else {
                pre.next = l1;
                l1 = l1.next;
            }
            pre = pre.next;
        }

        pre.next = (l1 == null ? l2 : l1);

        ListNode tmp = dummy.next;
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node =
            new ListNode(1,
                new ListNode(3,
                    new ListNode(5, null)));

        ListNode node2 = new ListNode(1,
            new ListNode(2,
                new ListNode(4, null)));

        System.out.println(mergeTwoLists(node, node2));

    }

    /**
     * l d>1>2>4 2>3>7 r
     *
     * @param node1
     * @param node2
     */
    public ListNode mergeTwo(ListNode node1, ListNode node2) {

        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        ListNode head = (node1.val <= node2.val ? node1 : node2);


        return head;
    }


}
