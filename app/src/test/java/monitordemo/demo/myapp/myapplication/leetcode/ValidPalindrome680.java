package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020/5/19
 * 680. 验证回文字符串 Ⅱ， 最多删除一个字符
 */
public class ValidPalindrome680 {
    private String str;
    private boolean deleted;

    public boolean validPalindrome(String s) {
        //思路：双指针法即可
        if (s == null) {
            return true;
        }
        this.str = s;
        return helper(0, s.length() - 1);
    }

    private boolean helper(int left, int right) {
        if (left >= right) {
            return true;
        }
        if (str.charAt(left) == str.charAt(right)) {
            return helper(left + 1, right - 1);
        }
        //跨过一个索引
        if (!deleted) {
            deleted = true;
            return helper(left + 1, right) || helper(left, right - 1);
        }
        return false;
    }
}
