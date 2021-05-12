package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @Author zhao on 5/12/21
 */
public class ContainsDuplicate217 {
    public boolean containsDuplicate(int[] nums) {
        //使用set即可
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        //使用map效率应该会高点
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return true;
            } else {
                map.put(num, 0);
            }
        }
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        //先排序，然后依次比对相邻的元素
        Arrays.sort(nums);
        int last = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            if (last == num) {
                return true;
            }
            last = num;
        }
        return false;
    }

    public boolean containsDuplicate4(int[] nums) {
        //hashSet
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate5(int[] nums) {
        //Arrays.stream api，但是也比较耗时
        return Arrays.stream(nums).distinct().count() < nums.length;
    }
}
