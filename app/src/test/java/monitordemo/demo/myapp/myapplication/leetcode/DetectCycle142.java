package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 7/26/21
 */
public class DetectCycle142 {

    /**
     * 链表中节点的数目范围在范围 [0, 104] 内
     * -105 <= Node.val <= 105
     * pos 的值为 -1 或者链表中的一个有效索引
     * <p>
     * 进阶：
     * 你是否可以使用 O(1) 空间解决此题？
     */
    public ListNode detectCycle(ListNode head) {
        //记录出现过的节点，比对即可
        List<ListNode> list = new ArrayList<>();
        ListNode te = head;
        list.add(te);
        while (te != null) {
            if (list.contains(te.next)) {
                return te.next;
            } else {
                list.add(te.next);
                te = te.next;
            }
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        //先用快慢指针判断是否有环；如果有环，找到入口节点是个技术活
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            //1. 移动指针
            slow = slow.next;
            fast = fast.next.next;
            //2. 相等则说明有环
            if (slow == fast) {
                //定义一个新指针指向head，同时移动newHead和slow，相遇的节点即为入口节点
                ListNode newHead = head;
                while (true){
                    if (newHead == slow) {
                        return newHead;
                    }
                    newHead = newHead.next;
                    slow = slow.next;
                }
            }
        }
        return null;
    }
}
