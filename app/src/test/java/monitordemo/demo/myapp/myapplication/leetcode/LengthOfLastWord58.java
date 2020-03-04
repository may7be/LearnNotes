package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-03-01
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
 * <p>
 * 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello World"
 * 输出: 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLastWord58 {
    public int lengthOfLastWord(String s) {
        //1. 判空 2. 倒着找空格即好，从第一个不是空格的地方记录，然后依次找空格即好. 边界问题是重点
        //或者可以用切割来
        if (s == null || s.length() == 0) {
            return 0;
        }
        char black = ' ';
        int length = s.length();
        //contains内部也是for循环，可以不要此行
        if (!s.contains(String.valueOf(black))) {
            return length;
        }
        int beginIndex = -1;
        for (int i = length - 1; i >= 0; i--) {
            //先判断是否找到了开始的索引
            if (beginIndex == -1) {
                //再判断当前是否是空格
                if (s.charAt(i) != black) {
                    beginIndex = i;
                }
            } else {
                if (s.charAt(i) == black) {
                    return beginIndex - i;
                }
            }
        }
        return beginIndex + 1;
    }

    public int lengthOfLastWord2(String s) {
        //优化思路：是否contains可以省略；是否可以先trim
        //优化2：s只有1位时，貌似没有必要，还是需要比对是否是空格
        if (s == null || s.length() == 0) {
            return 0;
        }
        char black = ' ';
        //是否可以不用找开始的索引:
        String trim = s.trim();
        int length = trim.length();
        //trim过后，beginIndex默认为最后一位，只需要找第一个出现的空格即可
        int beginIndex = length - 1;
        for (int i = length - 2; i >= 0; i--) {
            if (trim.charAt(i) == black) {
                return beginIndex - i;
            }

        }
        return beginIndex + 1;
    }

    public int lengthOfLastWord3(String s) {
        //优化思路：直接用空格切割是否可以
        if (s == null || s.length() == 0) {
            return 0;
        }
        //先trim
        String trim = s.trim();
        //再切割
        String[] split = trim.split(" ");
        System.out.println("split size: " + split.length);
        return split[split.length - 1].length();
    }

    public int lengthOfLastWord4(String s) {
        //优化思路：删除不必要的临时变量
        //优化2：s只有1位时，貌似没有必要，还是需要比对是否是空格
        if (s == null || s.length() == 0) {
            return 0;
        }
        //是否可以不用找开始的索引:
        String trim = s.trim();
        int length = trim.length();
        //trim过后，beginIndex默认为最后一位，只需要找第一个出现的空格即可
        for (int i = length - 2; i >= 0; i--) {
            if (trim.charAt(i) == ' ') {
                return length - 1 - i;
            }
        }
        return length;
    }

    @Test
    public void fun() {
        System.out.println(lengthOfLastWord3(" ag ddc "));
    }
}
