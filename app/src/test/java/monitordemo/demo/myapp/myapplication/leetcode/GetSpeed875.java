package monitordemo.demo.myapp.myapplication.leetcode;

import java.util.Arrays;

/**
 * @Author zhao on 4/26/21
 */
public class GetSpeed875 {
    /**
     * 1 <= piles.length <= 10^4
     * piles.length <= H <= 10^9
     * 1 <= piles[i] <= 10^9
     */
    public int minEatingSpeed(int[] piles, int h) {
        if (piles.length == 1) {
            return piles[0];
        }
        int max = Arrays.stream(piles).max().getAsInt();
        //最小为1，最大为max
        return getSpeed(1, max, piles, h);
    }

    private int getSpeed(int left, int right, int[] piles, int h) {
        while (left < right) {
            int mid = (left + right) / 2;
            int costH = 0;
            for (int pile : piles) {
                costH += pile % mid == 0 ? pile / mid : pile / mid + 1;
                if (costH > h) {
                    break;
                }
            }
            if (costH > h) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }
        return right;
    }
}
