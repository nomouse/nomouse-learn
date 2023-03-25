package nomouse.learn.algo.list;

import nomouse.learn.algo.ListNode;

/**
 * @author nomouse
 * @date 2021/9/21 题意：删除链表中等于给定值 val 的所有节点。
 */
public class RemoveLinkElement {

    public static ListNode remove(ListNode r, int val) {
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
                            new ListNode(3, null)))));

        ListNode reserve = removeH(node, 3);
        print(reserve);
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }


    /**
     *    1 2 3 4 2
     *  x 1 2 3 4 2
     *  l r
     */
    private static ListNode removeH(ListNode node, int v) {
        ListNode d = new ListNode(-1, node);

        ListNode l = d;
        ListNode r = node;
        while (r != null) {
            if(r.val == v){
                // 删除当前
                r = r.next;
                l.next = r;
            }else {
                l = r;
                r= r.next;
            }
        }
        return d.next;
    }


}
