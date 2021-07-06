package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 7/6/21
 */
public class MinSubArrayLen209 {
    /**
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     * 进阶：
     * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
     * <p>
     * target = 7, nums = [2,3,1,2,4,3]
     */
    public int minSubArrayLen(int target, int[] nums) {
        //暴力法：依次增大长度，然后对所有连续子数组求和，满足条件即结束
        int len = nums.length;
        //1. 先依次记录前n项和
        int[] sums = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        //2.
        for (int i = 1; i <= len; i++) {
            //sum[0,i-1] -> sum[len-i, len-1]
            int start = 0, end = i - 1;
            int sum = sums[i];
            while (end <= len - 1) {
                //从第二项开始计算差值
                if (start > 0) {
                    sum += nums[end] - nums[start - 1];
                }
                if (sum >= target) {
                    return i;
                } else {
                    start++;
                    end++;
                }
            }
        }
        return 0;
    }

    /**
     * target = 7, nums = [2,3,1,2,4,3]
     */
    public int minSubArrayLen2(int target, int[] nums) {
        //双指针: 又叫滑动窗口法
        int start = 0, end = 0;
        int sum = nums[start];
        int res = Integer.MAX_VALUE;
        while (end <= nums.length - 1) {
            if (end > start) {
                sum += nums[end];
            }
            while (sum >= target) {
                //满足条件时，先记录当前的子数组长度
                res = Math.min(res, end - start + 1);
                //然后移动左指针，看是否还有更短的子数组
                sum -= nums[start];
                start++;
            }
            //未满足条件时，移动右指针
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
