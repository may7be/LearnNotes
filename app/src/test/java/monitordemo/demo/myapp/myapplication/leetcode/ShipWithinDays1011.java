package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;

/**
 * @Author zhao on 4/26/21
 */
public class ShipWithinDays1011 {
    public int shipWithinDaysV2(int[] weights, int D) {
        int totalW = Arrays.stream(weights).sum();
        int maxW = Arrays.stream(weights).max().getAsInt();
        return shipWithinDaysV2(maxW, totalW, weights, D);
    }

    private int shipWithinDaysV2(int left, int right, int[] weights, int D) {
        if (left >= right) {
            return right;
        }
        int midW = (left + right) / 2;
        int days = 1;
        int countW = 0;
        for (int weight : weights) {
            countW += weight;
            if (countW > midW) {
                countW = weight;
                days++;
            }
            if (days > D) {
                break;
            }
        }
        if (days <= D) {
            return shipWithinDaysV2(left, midW, weights, D);
        }else {
            return shipWithinDaysV2(midW + 1, right, weights, D);
        }
    }

    /**
     * 1 <= D <= weights.length <= 50000
     * 1 <= weights[i] <= 500
     */
    public int shipWithinDays(int[] weights, int D) {
        // 1天时，返回总重，也是最小值；length时，返回最大重量，也是最大值。
        // D >= maxW
        int len = weights.length;
        if (len == 1) {
            return weights[0];
        }
        //1. 先求最大重量和总重，确定边界
        int totalW = 0;
        int maxW = 0;
        for (int weight : weights) {
            totalW += weight;
            maxW = Math.max(maxW, weight);
        }
        if (D == 1) {
            return totalW;
        }
        if (D == len) {
            return maxW;
        }
        //2. 然后利用二分法[maxW, totalW]，不断调整最大最小值，是否满足
        return getMinW(maxW, totalW, weights, D);
    }

    private int getMinW(int minW, int maxW, int[] weights, int D) {
        System.out.println("minW: " + minW + ", maxW: " + maxW);
        if (minW >= maxW) {
            return maxW;
        }
        //同时计算min 和mid是否满足条件，然后来调整最大和最小值
        int midW = (minW + maxW) / 2;
        int daysForMin = 1;
        int daysForMid = 1;
        int countWForMin = 0;
        int countWForMid = 0;
        for (int weight : weights) {
            //天数未超过时，才进行计算
            if (daysForMin <= D) {
                countWForMin += weight;
                //超重后，重新计算(由于最小值为maxW，所以至少会运两件以上的)
                if (countWForMin > minW) {
                    countWForMin = weight;
                    daysForMin++;
                }
            }
            if (daysForMid <= D) {
                countWForMid += weight;
                if (countWForMid > midW) {
                    countWForMid = weight;
                    daysForMid++;
                }
            }
        }
        if (daysForMin <= D) {
            return minW;
        }
        //mid满足 or 不满足
        if (daysForMid <= D) {
            return getMinW(minW + 1, midW, weights, D);
        } else {
            return getMinW(midW + 1, maxW, weights, D);
        }
    }
}
