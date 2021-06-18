package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 6/18/21
 */
public class SearchRange34 {
    /**
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     */
    private int[] nums;
    private int target;

    public int[] searchRange(int[] nums, int target) {
        this.nums = nums;
        this.target = target;

        //1. 先找到一个
        int temp = search(0, nums.length - 1);
        if (temp == -1) {
            return new int[]{-1, -1};
        }
        //2. 寻找左边界和右边界
        int left = searchLeft(0, temp);
        int right = searchRight(temp, nums.length - 1);
        return new int[]{left, right};
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

    private int searchLeft(int left, int right) {
        //都是<=target的
        if (nums[left] == target) {
            return left;
        }
        left++;
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return searchLeft(left, mid);
        } else {
            return searchLeft(mid + 1, right);
        }
    }

    private int searchRight(int left, int right) {
        //都是>=target的
        if (nums[right] == target) {
            return right;
        }
        right--;
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return searchRight(mid, right);
        } else {
            return searchRight(left, mid - 1);
        }
    }

    public int[] searchRange2(int[] nums, int target) {
        //遍历即可
        int left = -1, right = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (left == -1) {
                    left = i;
                }else {
                    right = i;
                }
            }
        }
        //如果只有一个相等的
        if (left != -1 && right == -1) {
            right = left;
        }
        return new int[]{left, right};
    }
}
