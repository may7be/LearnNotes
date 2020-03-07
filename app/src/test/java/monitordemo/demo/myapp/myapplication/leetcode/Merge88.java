package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author zhao on 2020-03-07
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 num1 成为一个有序数组。
 * <p>
 *  
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Merge88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //1. 判空 2. 直接插入进去，然后排序即可。太麻烦
        //还是一个一个插入吧

        if (nums1 == null || nums2 == null || n == 0) {
            return;
        }
        //如果1的最大值比2的最小值还小，直接插入到队尾即可
        if (m == 0 || nums1[m - 1] <= nums2[0]) {
            System.arraycopy(nums2, 0, nums1, m, n);
            for (int i = 0; i < n; i++) {
                nums1[m + i] = nums2[i];
            }
            return;
        }
        //反之,
        if (nums1[0] >= nums2[n - 1]) {
            //先倒序将nums1集体后移n位
            for (int i = m - 1; i >= 0; i--) {
                nums1[i + n] = nums1[i];
            }
            //再将nums2依次插入到队首
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        //其他都是正常的，依次比较两个数即可
        int[] temp = new int[m + n];
        int i = 0, j = 0;
        for (int k = 0; k < m + n; k++) {
            System.out.println("i: " + i + ", j= " + j);
            //需要判断i j是否越界
            if (i >= m) {
                System.out.println("nums1_" + k + ": " + nums2[j] + " i >=m");
                System.out.println();
                temp[k] = nums2[j];
                j++;
                continue;
            }
            if (j >= n) {
                System.out.println("nums1_" + k + ": " + nums1[i] + " j >=n");
                System.out.println();
                temp[k] = nums1[i];
                i++;
                continue;
            }

            if (nums1[i] <= nums2[j]) {
                System.out.println("nums1_" + k + ": " + nums1[i] + " a <= b");
                System.out.println();
                temp[k] = nums1[i];
                i++;
            } else {
                System.out.println("nums1_" + k + ": " + nums2[j] + " a > b");
                System.out.println();
                temp[k] = nums2[j];
                j++;
            }
        }
        //再复制temp到nums1
        for (int i1 = 0; i1 < m + n; i1++) {
            nums1[i1] = temp[i1];

        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        //优化思路1：冗余代码太多，尤其是批量复制数组时
        //❌但是反而更耗时了
        if (nums1 == null || nums2 == null || n == 0) {
            return;
        }
        //如果1的最大值比2的最小值还小，直接插入到队尾即可
        if (m == 0 || nums1[m - 1] <= nums2[0]) {
            System.arraycopy(nums2, 0, nums1, m, n);
            return;
        }
        //反之,
        if (nums1[0] >= nums2[n - 1]) {
            //先倒序将nums1集体后移n位
            for (int i = m - 1; i >= 0; i--) {
                nums1[i + n] = nums1[i];
            }
            //再将nums2依次插入到队首
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        //其他都是正常的，依次比较两个数即可
        int[] temp = new int[m + n];
        int i = 0, j = 0;
        for (int k = 0; k < m + n; k++) {
            System.out.println("i: " + i + ", j= " + j);
            //需要判断i j是否越界
            if (i >= m) {
                //复制
                System.arraycopy(nums2, j, temp, k, n - i);
                break;
            }
            if (j >= n) {
                System.arraycopy(nums1, i, temp, k, m - i);
                break;
            }

            if (nums1[i] <= nums2[j]) {
                temp[k] = nums1[i];
                i++;
            } else {
                temp[k] = nums2[j];
                j++;
            }
        }
        //再复制temp到nums1
        System.arraycopy(temp, 0, nums1, 0, m + n);
    }

    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        //优化思路2：没必要判空了，但是需要注意越界。
        //从后排序

        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            int t = i + j + 1;
            if (nums1[i] >= nums2[j]) {
                nums1[t] = nums1[i];
                i--;
            } else {
                nums1[t] = nums2[j];
                j--;
            }
        }
        if (i < 0) {
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        } else {
            //这句话看起来是多么的多余
//            System.arraycopy(nums1, 0, nums1, 0, i + 1);
        }
    }

    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        //利用系统api
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


    @Test
    public void fun() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        merge2(nums1, m, nums2, n);
    }
}
