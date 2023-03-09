package nomouse.learn.algo.list;

import nomouse.learn.algo.ListNode;

/**
 * @author nomouse
 * @date 2021/9/21
 */
public class SwitchLinkElement {

    public ListNode switchElement(ListNode r) {
        // [1,2,3,4,5] -> [2,1,4,3,5]
        // 1. 前两个；2. 中间2个；3.后两个；4. 后一个

        // 虚拟节点
        ListNode dummy = new ListNode(-1, r);

        ListNode pre = dummy;
        ListNode first = r;
        ListNode second = null;
        ListNode next = null;

        while (first != null) {
            second = first.next;
            if (second == null) {
                break;
            }

            next = second.next != null ? second.next : null;
            pre.next = second;
            second.next = first;
            first.next = next;

            // 向下
            pre = first;
            first = next;
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

        ListNode reserve = new SwitchLinkElement()
            .switchElement(node);
        System.out.println(reserve);
    }
}
