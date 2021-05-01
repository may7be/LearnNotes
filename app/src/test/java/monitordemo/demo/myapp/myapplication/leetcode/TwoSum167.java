package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;

/**
 * @Author zhao on 5/1/21
 */
public class TwoSum167 {

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
