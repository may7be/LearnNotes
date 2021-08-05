package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/8/5
 */
public class Reverse541 {

    /**
     * 每 2k 个字符反转前 k 个字符。
     * <p>
     * 1 <= s.length <= 104
     * s 仅由小写英文组成
     * 1 <= k <= 104
     */
    public String reverseStr(String s, int k) {
        //其实就是每k个元素反转一下
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            //定义当前轮次
            int turn = i / k + 1;
            if (turn % 2 == 1) {
                //定义当前轮次，最小和最大pos的和
                int sum = (turn - 1) * k + Math.min(len - 1, turn * k - 1);
                sb.append(s.charAt(sum - i));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public String reverseStr2(String s, int k) {
        //用数组似乎更好些
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int turns = len / k;
        for (int i = 0; i <= turns; i++) {
            //奇数轮轮空
            if (i % 2 == 1) {
                continue;
            }
            int l = i * k, r = Math.min((i + 1) * k - 1, len - 1);
            while (l < r) {
                char te = chars[l];
                chars[l] = chars[r];
                chars[r] = te;
                l++;
                r--;
            }
        }
        return new String(chars);
    }
}
