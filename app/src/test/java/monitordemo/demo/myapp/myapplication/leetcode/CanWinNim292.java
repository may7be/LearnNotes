package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/12/6
 */
class CanWinNim292 {
    /**
     * 1 <= n <= 231 - 1
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    public boolean canWinNim2(int n) {
        return (n & 3) != 0;
    }
}
