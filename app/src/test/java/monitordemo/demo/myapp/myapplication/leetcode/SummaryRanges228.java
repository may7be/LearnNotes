package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2021/11/25
 */
class SummaryRanges228 {
    /**
     * 0 <= nums.length <= 20
     * -231 <= nums[i] <= 231 - 1
     * nums 中的所有值都 互不相同
     * nums 按升序排列
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return list;
        }
        //1. 定义左右两个指针
        int l = 0, r = len;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[l] + i - l) {
                r = i;
            } else {
                appendStr(l, r, list, nums);
                //重置指针
                l = i;
                r = len;
            }
        }
        //2. 结束循环
        appendStr(l, r, list, nums);
        return list;
    }

    private void appendStr(int l, int r, List<String> list, int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append(nums[l]);
        if (r != nums.length) {
            sb.append("->");
            sb.append(nums[r]);
        }
        list.add(sb.toString());
    }
}
