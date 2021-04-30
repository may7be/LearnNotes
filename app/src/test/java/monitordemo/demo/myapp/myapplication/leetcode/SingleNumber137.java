package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;

/**
 * @Author zhao on 4/30/21
 */
public class SingleNumber137 {
    public int singleNumber2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        int last = nums[0];
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            if (num != last) {
                if (i % 3 == 1) {
                    break;
                } else {
                    last = num;
                }
            }
        }
        return last;
    }

    /**
     * 1 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
     *  
     * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     */
    public int singleNumber(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        int last = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num != last) {
                if (count == 1) {
                    return last;
                } else {
                    last = num;
                    count = 1;
                }
            } else {
                count++;
            }
        }
        return last;

    }
}
