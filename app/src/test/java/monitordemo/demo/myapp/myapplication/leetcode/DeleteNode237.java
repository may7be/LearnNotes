package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/11/26
 */
class DeleteNode237 {
    /**
     * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
     * 题目数据保证需要删除的节点 不是末尾节点 。
     * <p>
     * 链表中节点的数目范围是 [2, 1000]
     * -1000 <= Node.val <= 1000
     * 链表中每个节点的值都是唯一的
     * 需要删除的节点 node 是 链表中的一个有效节点 ，且 不是末尾节点
     */
    public void deleteNode(ListNode node) {
        //正常删除就是修改last的next指向next即可，即需要三个节点：last、current、next
        //目前的困境就是无法访问到last
        //同时还有一个条件：不是末尾节点，即至少可以访问到两个节点：current和next
        //所以可以想到其实可以删除next节点，只不过删之前需要把current节点的值改为next的值
        //其实就是换种思维，删除节点并必须把节点删除，可以赋值
        node.val = node.next.val;
        //删除next节点即可
        node.next = node.next.next;
    }

    public void deleteNode2(ListNode node) {
        //记录next的val
        int te = node.next.val;
        //删除next节点
        node.next = node.next.next;
        //修改node的节点值
        node.val = te;
    }
}
