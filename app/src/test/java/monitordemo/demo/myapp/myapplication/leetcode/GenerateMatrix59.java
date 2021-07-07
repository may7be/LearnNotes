package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author zhao on 7/6/21
 */
public class GenerateMatrix59 {

    /**
     * 1 <= n <= 20
     */
    public int[][] generateMatrix(int n) {
        //暴力法: 一圈一圈进行填充
        int[][] matrix = new int[n][n];
        int radius = n;
        int te = 0;
        for (int k = 1; k <= n * n; k++) {
            if (radius < 0) {
                break;
            }
            if (radius == 1) {
                matrix[n / 2][n / 2] = k;
            } else {
                if (k <= te + (4 * radius - 4)) {
                    int[] ij = getIJ(radius, k - te);
                    int r = (n - radius) / 2;
                    matrix[ij[0] + r][ij[1] + r] = k;
                } else {
                    k--;
                    te += 4 * radius - 4;
                    radius -= 2;
                }
            }
        }
        return matrix;
    }

    /**
     * k的范围[1,4n-4], 求对应的i和j
     */
    private int[] getIJ(int n, int k) {
        if (k <= n) {
            return new int[]{0, k - 1};
        } else if (k <= 2 * n - 1) {
            return new int[]{k - n, n - 1};
        } else if (k <= 3 * n - 2) {
            return new int[]{n - 1, 3 * n - 2 - k};
        } else {
            return new int[]{4 * n - 4 - k + 1, 0};
        }
    }

    @Test
    public void fun() {
        int n = 6;
        for (int k = 1; k <= 4 * n - 4; k++) {
//            System.out.println(Arrays.toString(getIJ(5, k)));
        }
        System.out.println(Arrays.deepToString(generateMatrix(n)));
    }
}
