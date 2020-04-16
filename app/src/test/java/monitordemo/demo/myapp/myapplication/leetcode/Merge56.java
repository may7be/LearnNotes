package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author zhao on 2020/4/16
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Merge56 {
    public int[][] merge3(int[][] intervals) {
        //思路3：利用系统api简化操作
        //1.
        if (intervals == null) {
            return null;
        }
        //2. 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //3. 比较，赋值，删除
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        for (int i = 0; i < list.size() - 1; i++) {
            int[] cur = list.get(i);
            int[] next = list.get(i + 1);
            if (cur[1] >= next[0]) {
                if (cur[1] < next[1]) {
                    list.get(i)[1] = next[1];
                }
                list.remove(i + 1);
                i--;
            }
        }
        //4. 转为arr
        return list.toArray(new int[list.size()][2]);
    }

    public int[][] merge2(int[][] intervals) {
        //优化思路：减少冗余操作
        //1.
        if (intervals == null) {
            return null;
        }
        int len = intervals.length;
        if (len <= 1) {
            return intervals;
        }
        //2. 排序
        int[][] ints = sortArr(intervals);
        //3. 比较，赋值，删除
        List<int[]> list = new ArrayList<>(Arrays.asList(ints));
        for (int i = 0; i < list.size() - 1; i++) {
            int[] cur = list.get(i);
            int[] next = list.get(i + 1);
            if (cur[1] >= next[0]) {
                if (cur[1] < next[1]) {
                    list.get(i)[1] = next[1];
                }
                list.remove(i + 1);
                i--;
            }
        }
        //4. 转为arr
        int[][] arr = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public int[][] merge(int[][] intervals) {
        //思路：先排序；循环依次比对前后两个即可，right<left, 则continue; >=left,直接合并，继续这个过程即可；  linkedList
        //感觉记录一个right很重要
        //1.
        if (intervals == null) {
            return null;
        }
        int len = intervals.length;
        if (len <= 1) {
            return intervals;
        }
        //2. 排序
        int[][] ints = sortArr(intervals);
        //3. 依次比对放入新的arr
        int[][] arr = new int[len][2];
        int[] tep = ints[0];
        int index = 0;
        for (int i = 1; i < len; i++) {
            int[] cur = ints[i];
            if (tep[1] < cur[0]) {
                arr[index] = tep;
                index++;
                tep = cur;
            } else {
                if (tep[1] < cur[1]) {
                    tep[1] = cur[1];
                }
            }
        }
        arr[index] = tep;
        if (index == len - 1) {
            return arr;
        }
        int[][] newArr = new int[index + 1][2];
        System.arraycopy(arr, 0, newArr, 0, index + 1);
        return newArr;
    }

    private int[][] sortArr(int[][] arr) {
        //冒泡排序
        int len = arr.length;
        for (int j = len - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (arr[i][0] > arr[i + 1][0]) {
                    int[] t = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = t;
                }
            }
        }
        return arr;
    }

    private int[] sortArr(int[] arr) {
        //冒泡排序
        int len = arr.length;
        for (int j = len - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    int t = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = t;
                }
            }
        }
        return arr;
    }

    @Test
    public void fun() {
        int[] a = new int[]{1, 5, 4, 6, 3, 1, 2, 4, 1};
        int[][] arr = new int[][]{{1, 2}, {3, 4}, {2, 3}, {2, 4}};
        Arrays.sort(new int[0][2], new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
//        sortArr(arr);
        System.out.println(Arrays.deepToString(arr));
    }
}
