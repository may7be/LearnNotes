package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @Author zhao on 2020/5/13
 */
public class MinStack155 {

    private final LinkedList<Integer> list;
    private final LinkedList<Integer> minList;
    private int minValue = Integer.MAX_VALUE;

    public MinStack155() {
        list = new LinkedList<>();
        minList = new LinkedList<>();
    }

    public void push(int x) {
        list.add(x);
        minValue = Math.min(minValue, x);
        minList.add(minValue);
    }

    public void pop() {
        list.removeLast();
        minList.removeLast();
    }

    public int top() {
        return list.getLast();
    }

    public int getMin() {
        return minList.getLast();
    }

    @Test
    public void fun() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(-2);
        set.add(1);
        set.add(-3);
        set.add(4);
//        System.out.println(set);

        String s = "a";
    }
}
