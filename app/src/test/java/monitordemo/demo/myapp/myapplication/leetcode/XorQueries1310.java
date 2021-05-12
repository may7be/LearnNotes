package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhao on 5/12/21
 */
public class XorQueries1310 {

    /**
     * 1 <= arr.length <= 3 * 10^4
     * 1 <= arr[i] <= 10^9
     * 1 <= queries.length <= 3 * 10^4
     * queries[i].length == 2
     * 0 <= queries[i][0] <= queries[i][1] < arr.length
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        //暴力法：
        int len = queries.length;
        int[] xor = new int[len];
        for (int i = 0; i < len; i++) {
            xor[i] = cal(arr, queries[i]);
        }
        return xor;
    }

    private int cal(int[] arr, int[] query) {
        int countXor = 0;
        for (int i = query[0]; i <= query[1]; i++) {
            countXor ^= arr[i];
        }
        return countXor;
    }

    private int cal2(int[] arr, int[] query) {
        //优化方向：异或运算的结合律可以用来消除相同的俩元素；0也可以消除
        //但是反而会超时
        int countXor = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = query[0]; i <= query[1]; i++) {
            int num = arr[i];
            if (num != 0) {
                if (list.contains(num)) {
                    list.remove(Integer.valueOf(num));
                } else {
                    list.add(num);
                }
            }
        }
        for (Integer num : list) {
            countXor ^= num;
        }
        return countXor;
    }

    public int[] xorQueries2(int[] arr, int[][] queries) {
        //前缀异或计算法：最坏的情况, query的值就是[0, arr.len-1]
        //发现
        //1. 先计算前缀异或结果集
        int len = arr.length;
        int[] xorS = new int[len];
        xorS[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xorS[i] = xorS[i - 1] ^ arr[i];
        }
        //2.
        int len2 = queries.length;
        int[] xor = new int[len2];
        for (int i = 0; i < len2; i++) {
            xor[i] = cal3(arr, xorS, queries[i]);
        }
        return xor;
    }

    private int cal3(int[] arr, int[] xors, int[] query) {
        //优化方向2：
        int start = query[0];
        int end = query[1];
        if (start == end) {
            return arr[start];
        }
        if (start == 0) {
            return xors[end];
        }
        return xors[end] ^ xors[start - 1];
    }
}
