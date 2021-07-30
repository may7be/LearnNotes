package monitordemo.demo.myapp.myapplication.leetcode;

import android.util.ArraySet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author zhao on 7/30/21
 */
public class Intersection349 {

    /**
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        //可优化的地方：直接使用hashSet可能更好；最后的比对，也是
        //1. 将一个数组存入map,顺便去了重
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {
            if (!map1.containsKey(i)) {
                map1.put(i, i);
            }
        }
        //2. 判断是否存在即可: ArraySet是安卓特有的类，不能用于java test
        Set<Integer> set = new ArraySet<>();
        for (int i : nums2) {
            if (map1.containsKey(i)) {
                set.add(i);
            }
        }
        //3. Collection<Integer> 转为int[]
        return set.stream().mapToInt(value -> value).toArray();
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        //1. 将一个数组存入map,顺便去了重
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int i : nums1) {
            if (!map1.containsKey(i)) {
                map1.put(i, i);
            }
        }
        //2. 判断是否存在即可: java中无法使用set, 用list代替
        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map1.containsKey(i) && !list.contains(i)) {
                list.add(i);
            }
        }
        //3. Collection<Integer> 转为int[]
        return list.stream().mapToInt(value -> value).toArray();
    }

    @Test
    public void fun() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersection2(nums1, nums2)));
    }
}
