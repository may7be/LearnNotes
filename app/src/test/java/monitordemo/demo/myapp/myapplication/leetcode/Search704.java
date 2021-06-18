package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 6/18/21
 */
public class Search704 {
    /**
     * （升序)
     * 你可以假设 nums 中的所有元素是不重复的。
     * n 将在 [1, 10000]之间。
     * nums 的每个元素都将在 [-9999, 9999]之间。
     */
    private int[] nums;
    private int target;

    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        return search(0, nums.length - 1);
    }

    private int search(int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return search(mid + 1, right);
        } else {
            return search(left, mid - 1);
        }
    }
}
