package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020-02-28
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInsert35 {

    public int searchInsert(int[] nums, int target) {
        //简单的二分查找
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //小知识点： java数组的最大长度为int的最大值
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //此时left = right
        return target <= nums[left] ? left : left + 1;
    }

    public int searchInsert2(int[] nums, int target) {
        //简单的二分查找: 优化一下，lef == right 的情况也可以写入循环
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //小知识点： java数组的最大长度为int的最大值
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
