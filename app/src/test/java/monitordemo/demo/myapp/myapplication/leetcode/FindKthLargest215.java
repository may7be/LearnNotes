package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;

/**
 * @Author zhao on 5/19/21
 */
public class FindKthLargest215 {
    /**
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     */
    public int findKthLargest(int[] nums, int k) {
        //快排、堆排序等后续再弄
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
