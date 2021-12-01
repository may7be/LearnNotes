package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/12/1
 */
class MaxPower1446 {

    /**
     * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     * <p>
     * 请你返回字符串的能量。
     * <p>
     * 1 <= s.length <= 500
     * s 只包含小写英文字母。
     */
    public int maxPower(String s) {
        if (s.length() == 1) {
            return 1;
        }
        //遍历即可
        char t = 0;
        int max = 1, tm = 1;
        for (char c : s.toCharArray()) {
            if (t == 0) {
                t = c;
            } else {
                if (t == c) {
                    tm++;
                } else {
                    t = c;
                    max = Math.max(max, tm);
                    tm = 1;
                }
            }
        }
        return Math.max(max, tm);
    }
}
