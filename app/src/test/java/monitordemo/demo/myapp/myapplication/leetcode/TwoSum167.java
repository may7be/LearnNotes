package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhao on 5/1/21
 */
public class TwoSum167 {

    @Test
    public void fun() {
        int[] nums = {3, 2, 4};
        int t = 6;
        System.out.println(Arrays.toString(twoSum10(nums, t)));
    }

    /**
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     */
    public int[] twoSum10(int[] nums, int target) {
        //利用map来缩减寻找的时间
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[]{i, map.get(key)};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * 2 <= numbers.length <= 3 * 104
     * -1000 <= numbers[i] <= 1000
     * numbers 按 递增顺序 排列
     * -1000 <= target <= 1000
     * 仅存在一个有效答案
     */
    public int[] twoSum2(int[] nums, int target) {
        //依次遍历，然后从随后的数中，二分查找看是否有满足的
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            int dstPos = Arrays.binarySearch(nums, i + 1, len, target - num);
            if (dstPos >= 0) {
                return new int[]{i + 1, dstPos + 1};
            }
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        //定义ab两个指针，每次都能缩小边界
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                //移动左边界
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}
