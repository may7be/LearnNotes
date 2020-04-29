package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author zhao on 2020/4/30
 * 面试题56 - I. 数组中数字出现的次数
 */
public class SingleNumbers56 {
    public int[] singleNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();
        int[] arr = new int[2];
        for (int num : nums) {
            if (list.contains(num)) {
                list.remove(Integer.valueOf(num));
            } else {
                list.add(num);
            }
        }
        arr[0] = list.getFirst();
        arr[1] = list.getLast();
        return arr;
    }
}
