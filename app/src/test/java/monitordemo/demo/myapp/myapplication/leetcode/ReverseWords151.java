package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author zhao on 2020/4/10
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseWords151 {
    public String reverseWords(String s) {
        //1.
        if (s == null) {
            return null;
        }
        //2.
        String[] arr = s.trim().split(" ");
        int len = arr.length;
        if (len == 1) {
            return arr[0];
        }
        //不需要线程安全
        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            String cur = arr[i];
            if (!"".equals(cur)) {
                sb.append(cur);
                if (i > 0) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    public String reverseWords2(String s) {
        //思路2：使用更多的api，String.split; Collections.reverse、String.join
        if (s == null) {
            return null;
        }
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        System.out.println(list.toString());
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public String reverseWords3(String s) {
        //思路3: 不使用系统api, 硬写, 没必要
        if (s == null) {
            return null;
        }
        return "";
    }

    public String reverseWords4(String s) {
        //trim、倒叙遍历、切割、拼接
        String trim = s.trim();
        int len = trim.length();
        StringBuilder sb = new StringBuilder();
        int start = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (" ".equals(String.valueOf(trim.charAt(i)))) {
                if (start != -1) {
                    sb.append(trim.substring(i + 1, start + 1));
                    sb.append(" ");
                    start = -1;
                }
            } else {
                if (start == -1) {
                    start = i;
                }
                if (i == 0) {
                    sb.append(trim.substring(0, start + 1));
                }
            }
        }
        return sb.toString().trim();
    }

    @Test
    public void fun() {
        String a = null;
        String b = "";
        String c = " ";
        String d = "  ";
        String e = "s";
        String f = "the sky is blue";
        String g = "  hello world!  ";
        String h = "a good   example";
        String[] arr = h.split(" ");
        String s = Arrays.toString(arr);
        for (String s1 : arr) {
//            System.out.println(s1);
        }
//        System.out.println(reverseWords2(h));
        char c1 = ' ';
        System.out.println(c1);
    }
}
