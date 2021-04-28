package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 4/28/21
 */
public class GetIntersectionNode160 {
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //定义两个指针a和b，a从headA开始，遍历完A后，接着遍历B；b也是同样的行为，从B->A
        //遍历过程中，进行比对即可。其实，只需要后从交换的指针开始比对即可
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            if (a != null) {
                a = a.next;
            } else {
                a = headB;
            }
            if (b != null) {
                b = b.next;
            } else {
                b = headA;
            }
        }
        return a;
    }

    /**
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        if (headA == headB) {
            return headA;
        }
        //求出各自的结点数；遍历两个node，更长的先开始
        int lenA = getDepth(headA);
        int lenB = getDepth(headB);
        if (lenA < lenB) {
            int startPos = lenB - lenA;
            for (int i = 0; i < startPos; i++) {
                headB = headB.next;
            }
        } else {
            int startPos = lenA - lenB;
            for (int i = 0; i < startPos; i++) {
                headA = headA.next;
            }
        }
        return getIntersectionNode(lenA, headA, headB);
    }

    /**
     * 判断长度相同(不为0)的链表是否相交
     */
    private ListNode getIntersectionNode(int len, ListNode headA, ListNode headB) {
        if (headA == headB) {
            return headA;
        }
        for (int i = 0; i < len - 1; i++) {
            headA = headA.next;
            headB = headB.next;
            if (headA == headB) {
                return headA;
            }
        }
        return null;
    }

    private int getDepth(ListNode node) {
        if (node == null) {
            return 0;
        }
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
}
