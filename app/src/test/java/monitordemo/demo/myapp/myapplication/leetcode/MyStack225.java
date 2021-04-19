package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author zhao on 4/19/21
 */
public class MyStack225 {
    /**
     * Initialize your data structure here.
     */
    private Deque<Integer> list1;

    public MyStack225() {
        list1 = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        list1.push(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return list1.pop();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return list1.getFirst();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return list1.isEmpty();
    }
}
