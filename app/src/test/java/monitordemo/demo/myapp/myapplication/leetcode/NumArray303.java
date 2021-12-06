package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/12/6
 */
class NumArray303 {

    private final int[] sums;

    /**
     * 0 <= nums.length <= 104
     * -105 <= nums[i] <= 105
     * 0 <= i <= j < nums.length
     * 最多调用 104 次 sumRange 方法
     */
    public NumArray303(int[] nums) {
        //记录前i项值即可
        int len = nums.length;
        sums = new int[len + 1];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return sums[right];
        }else {
            return sums[right] - sums[left - 1];
        }
    }
}
