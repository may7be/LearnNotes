package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author zhao on 5/2/21
 */
public class MajorityElement169 {
    public int majorityElement3(int[] nums) {
        int limit = nums.length / 2 + 1;
        for (int num : nums) {
            //依次判断是否是出现满足要求
            if (judge(num, nums, limit)) {
                return num;
            }
        }
        return -1;
    }

    private boolean judge(int num, int[] nums, int limit) {
        int appearSum = 0;
        for (int i : nums) {
            if (i == num) {
                appearSum++;
                if (appearSum >= limit) {
                    return true;
                }
            }
        }
        return false;
    }

    public int majorityElement2(int[] nums) {
        //经过排序，时间就不是线性的了：O(nlogn)
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElement(int[] nums) {
        //用map存储，即可
        HashMap<Integer, Integer> map = new HashMap<>();
        int limit = nums.length / 2;
        int appearSumMax = 0;
        for (int num : nums) {
            int te = map.getOrDefault(num, 0) + 1;
            appearSumMax = Math.max(appearSumMax, te);
            if (appearSumMax > limit) {
                return num;
            }
            map.put(num, te);
        }
        return -1;
    }
}
