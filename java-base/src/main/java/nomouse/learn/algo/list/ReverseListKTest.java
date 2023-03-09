package nomouse.learn.algo.list;

import nomouse.learn.algo.ListNode;

/**
 * @author nomouse
 * @date 2021/9/7
 * <p>
 * 给出一个链表，每k个节点一组进行翻转，并返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么将最后剩余节点保持原有顺序。
 */
public class ReverseListKTest {

    public static ListNode reverseK(ListNode head, int k) {
        // 1>2>3>4>5>6 k=3
        // 3>2>1>6>5>4
        // 1. -1>1>2>3>4>5>6
        // 2. -1>2>1>3>4>5>6
        // 3. -1>3>2>1>4>5>6
        // 1. 查询长度
        ListNode dummy = new ListNode(-1, head);
        ListNode stage = dummy;
        ListNode cur = head;
        int step = 1;
        while (cur.next != null) {
            if (step < k) {
                // 正常反转
                ListNode remove = cur.next;
                cur.next = remove.next;
                remove.next = stage.next;
                stage.next = remove;
                step++;
            } else if (step == k) {
                stage = cur;
                cur = cur.next;
                step = 1;
            }

        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node =
            new ListNode(1,
                new ListNode(2,
                    new ListNode(3,
                        new ListNode(4,
                            new ListNode(5, null)))));

        ListNode res = reverseK(node, 2);
        System.out.println(res);
    }
}
