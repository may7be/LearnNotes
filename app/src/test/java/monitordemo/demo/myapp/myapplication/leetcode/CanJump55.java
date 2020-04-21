package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/17
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanJump55 {

    private List<Integer> cannotList;
    private int[] nums;

    public boolean canJump2(int[] nums) {
        //优化思路：list太耗时，用指针
        //1.
        if (nums == null || nums.length <= 1) {
            return true;
        }
        this.nums = nums;
        //2. 记录cannotJump的索引
        cannotList = new ArrayList<>();
        return helper(0);
    }

    private boolean helper(int left) {
        int first = nums[left];
        if (first == 0 || cannotList.contains(left)) {
            return false;
        }
        if (first >= nums.length - 1 - left) {
            return true;
        }
        for (int i = 1; i <= first; i++) {
            if (helper(left + i)) {
                return true;
            } else {
                cannotList.add(left + i);
            }
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        //1.
        if (nums == null || nums.length <= 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        //2. 转为list处理
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        //记录不能jump的list.size,以此来节省冗余运算
        cannotList = new ArrayList<>();
        //3.
        return canJump(list);
    }

    private boolean canJump(List<Integer> list) {
        int first = list.get(0);
        for (int i = first; i >= 1; i--) {
            //间隔为1时，直接返回true
            if (list.size() - i <= 1) {
                return true;
            }
            if (cannotList.contains(list.size() - i)) {
                return false;
            }
            if (list.get(i) != 0 && canJump(list.subList(i, list.size()))) {
                return true;
            } else {
                cannotList.add(list.size() - i);
            }
        }
        return false;
    }

    @Test
    public void fun() {
        int[] arr = {
                2, 0, 6, 9, 8, 4, 5, 0, 8, 9, 1, 2, 9, 6, 8, 8, 0, 6, 3, 1, 2, 2, 1, 2, 6, 5, 3, 1, 2, 2, 6, 4, 2, 4, 3, 0, 0, 0, 3, 8, 2, 4, 0, 1, 2, 0, 1, 4, 6, 5, 8, 0, 7, 9, 3, 4, 6, 6, 5, 8, 9, 3, 4, 3, 7, 0, 4, 9, 0, 9, 8, 4, 3, 0, 7, 7, 1, 9, 1, 9, 4, 9, 0, 1, 9, 5, 7, 7, 1, 5, 8, 2, 8, 2, 6, 8, 2, 2, 7, 5, 1, 7, 9, 6};
        int[] arr2 = {2, 0, 0};
        int[] arr3 = {2, 0};
        int[] arr4 = {2, 5, 0, 0};
        System.out.println(canJump2(arr));
    }
}
