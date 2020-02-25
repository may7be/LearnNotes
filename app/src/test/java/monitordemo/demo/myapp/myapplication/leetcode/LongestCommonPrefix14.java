package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020-02-22
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestCommonPrefix14 {

    public String longestCommonPrefix(String[] strs) {
        //1. 首先判空 2.按照长短排序 3.从最短的str开始遍历是否startWith,如果是直接返回即可；如果不是，最短的切割掉最后一位，再次遍历
        //思路2：可能能用栈来处理
        //思路3：依次比对前后两个str的prefix
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = getCommonPrefix(strs[0], strs[1]);
        for (int i = 2; i < strs.length; i++) {
            prefix = getCommonPrefix(prefix, strs[i]);
        }
        return prefix;
    }

    private String getCommonPrefix(String str1, String str2) {
        if (isEmpty(str1) || isEmpty(str2)) {
            return "";
        }
        //先切割为一样的长度
        if (str1.length() < str2.length()) {
            str2 = str2.substring(0, str1.length());
        } else if (str1.length() > str2.length()) {
            str1 = str1.substring(0, str2.length());
        }
        //如果相同，直接return
        if (str1.equals(str2)) {
            return str1;
        }
        //然后就只能遍历了
        int length = str1.length();
        for (int i = length - 1; i > 0; i--) {
            str1 = str1.substring(0, i);
            if (str2.startsWith(str1)) {
                return str1;
            }
        }
        return "";
    }

    public String longestCommonPrefix2(String[] strs) {
        //思路2：可能能用栈来处理,其实跟栈没关系; 问题在于时间复杂度为n的平方
        if (strs == null || strs.length == 0) {
            return "";
        }
        //拿出第一个str进行遍历
        String firstStr = strs[0];
        if (isEmpty(firstStr)) {
            return "";
        }
        StringBuilder prefix = new StringBuilder();
        char[] chars = firstStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char firstStrChar = chars[i];
            //遍历原数组
            for (String str : strs) {
                //包含空串，直接返回
                if (isEmpty(str)) {
                    return "";
                }
                //从第二个开始；startWith的也可以直接continue
                if (str.startsWith(firstStr)) {
                    continue;
                }
                //先判断i是否越界；然后判断如果不相同，直接return;
                if (i >= str.length() || !(str.charAt(i) == firstStrChar)) {
                    return prefix.toString();
                }
            }
            prefix.append(firstStrChar);
        }
        return prefix.toString();
    }

    /**
     * 不能使用TextUtils
     *
     * @param str s
     * @return b
     */
    private boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public String longestCommonPrefix3(String[] strs) {
        //思路4：不断地切割
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int length = strs.length;
        for (int i = 1; i < length; i++) {
            String str = strs[i];
            if (str.isEmpty()) {
                return "";
            }
            while (!str.startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if (prefix.isEmpty()) {
                return "";
            }
        }
        return prefix;
    }
}
