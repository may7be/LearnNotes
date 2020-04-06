package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 2020/4/6
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Trap42 {
    private int[] mHeight;

    public int trap2(int[] height) {
        //1.
        if (height == null || height.length <= 2) {
            return 0;
        }
        //2.
        mHeight = height;
        return trapHelper(0, mHeight.length - 1);
    }

    private int trapHelper(int start, int end) {
        if (end - start < 2) {
            return 0;
        }
        int count = 0;
        int tempCount = 0;
        int tempBigger = mHeight[start];
        int tempStart = start;
        for (int i = start + 1; i <= end; i++) {
            if (mHeight[i] >= tempBigger) {
                tempBigger = mHeight[i];
                count += tempCount;
                tempCount = 0;
                tempStart = i;
            } else {
                tempCount += tempBigger - mHeight[i];
            }
        }
        //tempStart对应的值-1，递归
        mHeight[tempStart] -= 1;
        if (tempCount > 0) {
            count += trapHelper(tempStart, end);
        }
        return count;
    }

    public int trap(int[] height) {
        //1.
        if (height == null || height.length <= 2) {
            return 0;
        }
        //2.
        //遍历，如果剩下的元素数<=2, 直接return
        int count = 0;
        int tempCount = 0;
        int tempBigger = height[0];
        int len = height.length;
        List<Integer> list = new ArrayList<>();
        list.add(tempBigger);
        for (int i = 1; i < len; i++) {
            if (height[i] >= tempBigger) {
                tempBigger = height[i];
                count += tempCount;
                tempCount = 0;
                list.clear();
                list.add(height[i]);
            } else {
                tempCount += tempBigger - height[i];
                list.add(height[i]);
            }
        }
        //3. 处理list
        if (list.size() >= 4) {
            //❌❌发现还是需要递归，所以，可以用指针会好点
        }
        return count;
    }
}
