package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020-02-25
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveElement27 {

    public int removeElement(int[] nums, int val) {
        //判空；排序(非必要，而貌似也不能，因为不能使用额外空间)；
        //感觉双指针法依然有效
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = -1;
        int length = nums.length;
        for (int j = 0; j < length; j++) {
            if (nums[j] != val) {
                i++;
                if (i != j) {
                    nums[i] = nums[j];
                }
            }
        }
        return i + 1;
    }

    public int removeElement2(int[] nums, int val) {
        // 双指针法依然有效，但是依然有优化空间，比如【5，1，2，3，4】val=5, 除了第一位其他位置没有必要移动
        //优化思路就是当相等的时候，将当前元素和最后一位互换位置, 然后下一次比对倒数第二位元素
        //适用于删除元素比较少的特殊情况，如【1.2.3.4.5】val=5 ；【5，1，2，4，3】val = 5的情况

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int length = nums.length;
        while (i < length) {
            if (nums[i] != val) {
                i++;
            } else {
                nums[i] = nums[length - 1];
                length--;
            }
        }
        return i;
    }

    /**
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     */
    public int removeElement4(int[] nums, int val) {
        //记录相等的次数，然后跟数组最后一位换位。
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                count++;
                nums[i] = nums[len - 1];
                nums[len - 1] = -1;
                len--;
                i--;
            }
        }
        return nums.length - count;
    }
}
