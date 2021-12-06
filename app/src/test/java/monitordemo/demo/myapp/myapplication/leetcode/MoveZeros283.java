package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/12/6
 */
class MoveZeros283 {
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     */
    public void moveZeroes(int[] nums) {
        //只能在循环中进行操作
        //记录0出现的次数
        int len = nums.length;
        int te = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                te++;
            } else {
                if (te > 0) {
                    //移动到对应位置
                    nums[i - te] = nums[i];
                }
            }
        }
        if (te > 0) {
            for (int i = len - te; i < len; i++) {
                nums[i] = 0;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        //依次赋值即可
        int te = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[te] = num;
                te++;
            }
        }
        for (int i = te; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
