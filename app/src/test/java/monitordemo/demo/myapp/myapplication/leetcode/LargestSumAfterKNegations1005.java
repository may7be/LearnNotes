package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author zhao on 2021/12/3
 */
public class LargestSumAfterKNegations1005 {
    /**
     * 1 <= nums.length <= 104
     * -100 <= nums[i] <= 100
     * 1 <= k <= 104
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        //分情况处理：nums中负值的个数和k的关系
        //1. 先排序
        Arrays.sort(nums);
        //2. 没负值
        if (nums[0] >= 0) {
            if (k % 2 == 0) {
                return Arrays.stream(nums).sum();
            } else {
                return Arrays.stream(nums).sum() - nums[0] * 2;
            }
        }
        //3. 有负值
        //记录剩余次数
        int times = 0;
        for (int i = 0; i < k; i++) {
            if (i < nums.length && nums[i] <= 0) {
                nums[i] = -nums[i];
            } else {
                times = k - i;
                break;
            }
        }
        if (times % 2 == 0) {
            return Arrays.stream(nums).sum();
        } else {
            //先求最小值
            int min = Arrays.stream(nums).min().getAsInt();
            return Arrays.stream(nums).sum() - min * 2;
        }
    }

    @Test
    public void fun() {
        int[] nums = {2, -3, -1, 5, -4};
        int k = 20;
        Arrays.sort(nums);
        System.out.println(nums[0]);
    }
}
