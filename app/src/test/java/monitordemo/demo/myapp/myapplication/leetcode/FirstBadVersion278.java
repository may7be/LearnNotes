package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/12/6
 */
class FirstBadVersion278 {
    /**
     * 1 <= bad <= n <= 231 - 1
     */
    public int firstBadVersion(int n) {
        //二分查找
        int l = 1, r = n;
        while (l < r) {
            //防止数据溢出
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean isBadVersion(int num) {
        return num % 2 == 0;
    }
}
