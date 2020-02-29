package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-02-28
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * <p>
 * 注意：整数序列中的每一项将表示为一个字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountAndSay38 {
    public String countAndSay(int n) {
        //题意最后一句有点别扭，应该是：输出外观数列的第 n 项， n为正整数 （1 ≤ n ≤ 30）
        String str = "1";
        for (int i = 1; i < n; i++) {
            str = next(str);
        }
        return str;
    }

    public String countAndSay2(int n) {
        //还是递归
        if (n == 1) {
            return "1";
        }
        return next(countAndSay2(n - 1));
    }

    /**
     * 求外观数列的下一项
     *
     * @return s
     */
    public String next(String before) {
        if (isEmpty(before)) {
            throw new IllegalArgumentException();
        }
        char[] chars = before.toCharArray();
        char temp = chars[0];
        int count = 1;
        StringBuilder sb = new StringBuilder();
        int length = chars.length;
        for (int i = 1; i < length; i++) {
            if (chars[i] == temp) {
                count++;
            } else {
                sb.append(count).append(temp);
                temp = chars[i];
                count = 1;
            }
        }
        sb.append(count).append(temp);
        System.out.println(before + ": " + sb.toString());
        return sb.toString();
    }

    private boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    @Test
    public void fun() {
//        System.out.println("1: " + next("1"));
//        System.out.println("2: " + next(next("1")));

        for (int i = 1; i < 5; i++) {
            System.out.println(i + ":" + countAndSay2(i));
        }
    }
}
