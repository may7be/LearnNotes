package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-02-26
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StrStr28 {
    public int strStr(String haystack, String needle) {
        //0. 判空； 1.不断地切割，然后用startWith判断即可
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        //依次比对即可
        int length = haystack.length();
        for (int i = 0; i < length; i++) {
            if (haystack.startsWith(needle)) {
                //startWith，直接返回i
                return i;
            } else {
                if (haystack.length() == needle.length()) {
                    //长度相同，没必要继续切割，返回-1
                    return -1;
                } else {
                    //长度不同，所以haystack长度至少是2，可以继续切割
                    haystack = haystack.substring(1);
                }
            }
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        //0. 判空； 1.不断地切割，然后用startWith判断即可
        //可优化的地方在于存在可能太多没必要的切割，如alithis this，前三位切割都没有意义
        //此时引入偏移表的概念：先比较前4位，不匹配看第5位的偏移量，决定下一次从哪开始
//        存储每一个在 模式串 中出现的字符，在 模式串 中出现的最右位置到尾部的距离 +1+1，例如 aab：
//
//        a 的偏移位就是 len(pattern)-1 = 2
//        b 的偏移位就是 len(pattern)-2 = 1
//        其他的均为 len(pattern)+1 = 4

        if (haystack == null || needle == null) {
            return -1;
        }
        int l2 = needle.length();
        if (l2 == 0) {
            return 0;
        }
        //依次比对即可
        int length = haystack.length();
        int i = 0;
        int offset= 0;
        while (i  < length) {
            haystack = haystack.substring(offset);
            if (haystack.startsWith(needle)) {
                //startWith，直接返回i
                return i;
            } else {
                if (haystack.length() <= l2) {
                    //长度相同，没必要继续切割，返回-1
                    return -1;
                } else {
                    //取出haystack 里needle.length()的后一位元素
                    String nextChar = haystack.substring(l2, l2 + 1);
                    //计算nextChar在needle中的偏移量
                    offset = getOffset(nextChar, needle);
                    i += offset;
                }
            }
        }
        return -1;
    }

    private int getOffset(String nextChar, String needle) {
        if (nextChar == null || needle == null || nextChar.length() > needle.length()) {
            return -1;
        }
        //如果不在这里，直接返回length +1
        if (!needle.contains(nextChar)) {
            return needle.length() + 1;
        }
        return needle.length() - needle.lastIndexOf(nextChar);
    }

    private boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    @Test
    public void fun(){
        System.out.println("offset: " + getOffset("l", "ll"));
        System.out.println("index: " + strStr2("hello", "ll"));
    }
}
