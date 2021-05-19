package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author zhao on 5/19/21
 */
public class KthLargestValue1738 {
    @Test
    public void fun() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(-1);
        set.add(-1);
        set.add(-4);
        set.add(1);
        set.add(5);
        set.add(2);
        System.out.println(set);
        for (int i = 0; i < 3 - 1; i++) {
            set.pollLast();
        }
        System.out.println(set.last());

        int[][] matrix = {{5, 2}, {1, 6}};
        for (int[] ints : matrix) {
            for (int anInt : ints) {
//                System.out.println(anInt);
            }
        }
        int[][] m = {{8, 10, 5, 8, 5, 7, 6, 0, 1, 4, 10, 6, 4, 3, 6, 8, 7, 9, 4, 2}};
        int[][] m2 = {{10, 9, 5}, {2, 0, 4}, {1, 0, 9}, {3, 4, 8}};

//        System.out.println("rows: " + m.length);
//        System.out.println("columns: " + m[0].length);
//        System.out.println(kthLargestValue(m2, 1));

        int[] arr = new int[10];
        System.out.println("aar: " + Arrays.toString(arr));
        List<Integer> list = new ArrayList<>();
        System.out.println("list: " + list);
    }

    /**
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 1000
     * 0 <= matrix[i][j] <= 106
     * 1 <= k <= m * n
     */
    public int kthLargestValue4(int[][] matrix, int k) {
        //继续优化：最后的排序应该还能优化
        List<Integer> list = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] vm = new int[rows + 1][columns + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //这一步很关键
                vm[i + 1][j + 1] = vm[i][j] ^ vm[i][j + 1] ^ vm[i + 1][j] ^ matrix[i][j];
                list.add(vm[i + 1][j + 1]);
            }
        }
        System.out.println(list);
        Collections.sort(list);
        //取第k大的值就好
        return list.get(list.size() - k);
    }

    public int kthLargestValue3(int[][] matrix, int k) {
        //继续优化：可以多一行一列，这样就不用判断边界条件了。不影响复杂度，只是代码看起来更好了
        List<Integer> list = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] vm = new int[rows + 1][columns + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //这一步很关键
                vm[i + 1][j + 1] = vm[i][j] ^ vm[i][j + 1] ^ vm[i + 1][j] ^ matrix[i][j];
                list.add(vm[i + 1][j + 1]);
            }
        }
        System.out.println(list);
        Collections.sort(list);
        //取第k大的值就好
        return list.get(list.size() - k);
    }

    public int kthLargestValue2(int[][] matrix, int k) {
        //暴力法优化：当i>0 &j>0时，vm[i][j] = vm[i - 1][j - 1] ^ vm[i - 1][j] ^ vm[i][j - 1] ^ matrix[i][j];
        List<Integer> list = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] vm = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0) {
                    vm[0][0] = matrix[0][0];
                } else if (i == 0) {
                    vm[0][j] = vm[0][j - 1] ^ matrix[0][j];
                } else if (j == 0) {
                    vm[i][0] = vm[i - 1][0] ^ matrix[i][0];
                } else {
                    //这一步很关键
                    vm[i][j] = vm[i - 1][j - 1] ^ vm[i - 1][j] ^ vm[i][j - 1] ^ matrix[i][j];
                }
                list.add(vm[i][j]);
            }
        }
        System.out.println(list);
        Collections.sort(list);
        //取第k大的值就好
        return list.get(list.size() - k);
    }

    public int kthLargestValue(int[][] matrix, int k) {
        //暴力法：计算出每个位置的value值，然后排序，取第K大的值即可
        //果然会超时
        List<Integer> list = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] vm = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0) {
                    vm[0][0] = matrix[0][0];
                } else if (i == 0) {
                    vm[0][j] = vm[0][j - 1] ^ matrix[0][j];
                } else if (j == 0) {
                    vm[i][0] = vm[i - 1][0] ^ matrix[i][0];
                } else {
                    vm[i][j] = vm[i - 1][j - 1] ^ getXors(i, j, matrix);
                }
                list.add(vm[i][j]);
            }
        }
        System.out.println(list);
        Collections.sort(list);
        //取第k大的值就好
        return list.get(list.size() - k);
    }

    /**
     * [i][0]^[i][1]...^[i][j]^ [0][j]^[1][j]^...[i-1][j]
     */
    private int getXors(int i, int j, int[][] matrix) {
        int xors = 0;
        for (int m = 0; m <= i; m++) {
            xors ^= matrix[m][j];
        }
        for (int k = 0; k < j; k++) {
            xors ^= matrix[i][k];
        }
        return xors;
    }

}
