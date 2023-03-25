package nomouse.learn.algo.list;

import nomouse.learn.algo.ListNode;

/**
 * 回文链表
 * <br>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
 *
 * @author nomouse
 * @date 2021/11/16
 */
public class Palindrome {

    public boolean isPalindrome(ListNode node) {

        ListNode slow = node;
        ListNode fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode cur;
        if (fast.next == null) {
            // 为空是奇数
            cur = slow.next;
        } else {
            cur = slow;
        }

        //反转后半部分
        ListNode dummy = new ListNode();
        dummy.next = cur;
        while (cur.next != null) {
            ListNode removed = cur.next;
            cur.next = removed.next;
            removed.next = dummy.next;
            dummy.next = removed;
        }

        // 对比从开头到翻转后的
        dummy = dummy.next;
        while (dummy != null) {
            if (dummy.val != node.val) {
                return false;
            }
            dummy = dummy.next;
            node = node.next;
        }

        //ListNode tmp = dummy.next;
        //while (tmp != null) {
        //    System.out.println(tmp.val);
        //    tmp = tmp.next;
        //}

        return true;
    }

    public static void main(String[] args) {
        ListNode node =
            new ListNode(1,
                new ListNode(2,
                    new ListNode(3,
                        new ListNode(2,
                            new ListNode(2, null)))));

        System.out.println(new Palindrome().isPalindrome(node));
        //System.out.println(reserve);
    }
}
