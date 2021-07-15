package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 7/15/21
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 */
public class MyLinkedList {

    private ListNode fakeHead;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        fakeHead = new ListNode(-1);
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (isValid(index)) {
            ListNode temp = fakeHead;
            for (int i = 0; i <= index; i++) {
                temp = temp.next;
            }
            return temp.val;
        }
        return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if (size == 0) {
            fakeHead.next = node;
        } else {
            node.next = fakeHead.next;
            fakeHead.next = node;
        }
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        ListNode temp = fakeHead;
        for (int i = 0; i < size; i++) {
            temp = temp.next;
        }
        temp.next = node;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        index = Math.max(0, index);
        if (index == 0) {
            addAtHead(val);
        } else if (index < size) {
            ListNode node = new ListNode(val);
            ListNode pre = fakeHead;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            ListNode nextNode = pre.next;
            pre.next = node;
            node.next = nextNode;
            size++;
        } else if (index == size) {
            addAtTail(val);
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (isValid(index)) {
            ListNode pre = fakeHead;
            for (int i = 0; i < index; i++) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
            size--;
        }
    }

    private boolean isValid(int index) {
        return index >= 0 && index <= size - 1;
    }
}
