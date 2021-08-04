package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhao on 2021/8/3
 */
public class ThreeSum15 {
    @Test
    public void fun() {
        int[] nums = {3, 0, -2, -1, 1, 2};
        System.out.println(threeSum3(nums));
    }

    /**
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 答案中不可以包含重复的三元组。
     */
    public List<List<Integer>> threeSum4(int[] nums) {
        //试试双指针：如何去重呢？---最最重要的是：碰到相同的元素，跳过就好了
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 3) {
            return lists;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return lists;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = len - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    lists.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //同时移动l和r，而且直接移动到和当前元素不同的位置
                    do {
                        l++;
                    } while (l < r && nums[l] == nums[l - 1]);
                    do {
                        r--;
                    } while (l < r && nums[r] == nums[r + 1]);
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        //试试双指针：遍历nums, 定义left和right指针---事实证明如何去重，确实有点难受，使用list.contains()还是有点慢
        //前提是有序
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 3) {
            return lists;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int l = i + 1, r = len - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> list = Arrays.asList(nums[i], nums[l], nums[r]);
                    if (!lists.contains(list)) {
                        lists.add(list);
                    }
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return lists;
    }


    public List<List<Integer>> threeSum2(int[] nums) {
        //显然暴力法是不可取的
        //既然要求结果不能重复，那么就可以把nums中的每个元素存入map，value即为其出现的次数.
        //那么可能出现的情况：三个一样的0；两个一样的；都不一样；----最终其实也没改变时间复杂度，还是得嵌套三次,还是会超时
        //都不一样时的时间复杂度优化为O(n*n)即不会超时了
        //0.
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length < 3) {
            return lists;
        }
        //1. 存入map
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            //如果存在0，0，0就存入
            if (num == 0 && map.get(0) == 3) {
                lists.add(Arrays.asList(0, 0, 0));
            }
        }
        //2. 分情况进行穷举：
        //2.1 两个一样的
        for (Integer key : map.keySet()) {
            if (key == 0 || map.get(key) == 1) {
                continue;
            }
            if (map.containsKey(-2 * key)) {
                lists.add(Arrays.asList(key, key, -2 * key));
            }
        }
        //2.2 都不一样
        List<Integer> keys = new ArrayList<>(map.keySet());
        int size = keys.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int t = -(keys.get(i) + keys.get(j));
                if (map.containsKey(t)) {
                    //要求索引不能小于j
                    if (keys.indexOf(t) > j) {
                        lists.add(Arrays.asList(keys.get(i), keys.get(j), t));
                    }
                }
            }
        }
        return lists;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        //暴力法：嵌套循环进行穷举----果然会超时
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        if (len < 3) {
            return lists;
        }
        // 排序，穷举
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        if (!lists.contains(list)) {
                            lists.add(list);
                        }
                    } else if (sum > 0) {
                        break;
                    }
                }
            }
        }
        return lists;
    }


}
