package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2021/8/5
 */
class ReverseString344 {
    public void reverseString(char[] s) {
        //直接转换
        int len = s.length;
        if (len <= 1) {
            return;
        }
        int l = 0, r = len - 1;
        while (l < r) {
            char te = s[l];
            s[l] = s[r];
            s[r] = te;
            l++;
            r--;
        }
    }
}
