package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author zhao on 2020/4/14
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNums445 {
    /**
     * 非空 链表来代表两个非负整数
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //思路：存入list，倒叙相加组成新的ListNode
        //1.获取倒序list
        List<Integer> list1 = getList(l1);
        List<Integer> list2 = getList(l2);
        //2. 相加
        List<Integer> list = new ArrayList<>();
        int size = Math.max(list1.size(), list2.size());
        int temp = 0, count;
        for (int i = 0; i <= size - 1; i++) {
            count = 0;
            count += temp;
            if (i < list1.size()) {
                count += list1.get(i);
            }
            if (i < list2.size()) {
                count += list2.get(i);
            }
            temp = (count) / 10;
            list.add(0, (count) % 10);
        }
        if (temp > 0) {
            list.add(0, temp);
        }
        //3. 组成listNode
        ListNode root = null;
        ListNode t = null;
        while (!list.isEmpty()) {
            Integer remove = list.remove(0);
            if (t == null) {
                root = new ListNode(remove);
                t = root;
            } else {
                t.next = new ListNode(remove);
                t = t.next;
            }
        }
        return root;
    }

    private List<Integer> getList(ListNode l1) {
        List<Integer> list = new ArrayList<>();
        while (l1 != null) {
            list.add(0, l1.val);
            l1 = l1.next;
        }
        return list;
    }

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        //官方思路：用栈, 跟list没区别
        //1. 压栈
        Stack<Integer> stack1 = getStack(l1);
        Stack<Integer> stack2 = getStack(l2);
        //2. 出栈
        int count = 0, temp = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            temp = 0;
            Integer pop1 = stack1.pop();
            if (pop1 != null) {
                count += pop1;
            }
            Integer pop2 = stack2.pop();
            if (pop2 != null) {
                count += pop2;
            }
            temp = count / 10;

        }
        return null;
    }

    private Stack<Integer> getStack(ListNode l1) {
        Stack<Integer> stack = new Stack<>();
        while (l1 != null) {
            stack.push(l1.val);
            l1 = l1.next;
        }
        return stack;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //❌❌优化思路：删除最后一个for循环, 发现并不能删除
        //1.获取倒序list
        List<Integer> list1 = getList(l1);
        List<Integer> list2 = getList(l2);
        //2. 相加
        int size = Math.max(list1.size(), list2.size());
        int temp = 0, count;
        ListNode root = null, te = null, next = null;
        for (int i = 0; i <= size - 1; i++) {
            count = 0;
            count += temp;
            if (i < list1.size()) {
                count += list1.get(i);
            }
            if (i < list2.size()) {
                count += list2.get(i);
            }
            temp = (count) / 10;
            root = new ListNode(count % 10);

            if (i > 0) {
                next = new ListNode(count % 10);
            }
            if (te == null) {
                te = new ListNode(count % 10);
            } else {

            }
            te.next = new ListNode(count % 10);
        }
        if (te != null) {
            root.next = te;
        }
        if (temp > 0) {
            root = new ListNode(temp);
            root.next = te;
        }
        return root;
    }
}
