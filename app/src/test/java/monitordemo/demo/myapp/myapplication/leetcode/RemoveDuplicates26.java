package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020-02-24
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicates26 {

    public int removeDuplicates(int[] nums) {
        //1. 判空 2.遍历, 不纠结怎么删除，只需要记录新数组的长度即可
        //题意：不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
        //意思其实是需要把前几位赋值为正确的值而已
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //记录下当前位置的值temp，遍历取值，如果不相同则count++，temp更新，同时判断count和当前索引是否相同，如果不同，将当前索引的值赋值为记录的temp
        int count = 1;
        int temp = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i] != temp) {
                temp = nums[i];
                //如果前一位是相同
                if (count != i) {
                    nums[count] = temp;
                }
                count++;
            }
        }
        return count;
    }

    public int removeDuplicates2(int[] nums) {
        //方法1优化版. temp可以用nums[count-1]来表示
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 1;
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i] != nums[count - 1]) {
                if (count != i) {
                    nums[count] = nums[i];
                }
                count++;
            }
        }
        return count;
    }

    public int removeDuplicates3(int[] nums) {
        //双指针法更为简单：慢指针i,快指针j，最后i+1即为新数组的长度
        //比较j位置元素跟i位置元素，相等则i不移动，j继续移动；不相等，则移动i指针，同时将j位置元素赋值给i即可。
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int length = nums.length;
        for (int j = 1; j < length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                if (i != j) {
                    nums[i] = nums[j];
                }
            }
        }
        return i + 1;
    }
}
