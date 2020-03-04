package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

/**
 * @Author zhao on 2020-03-02
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddBinary67 {
    public String addBinary(String a, String b) {
        //1. 非空字符串 2. 倒序相加即可
        //思路2：先转为十进制，相加后再转为二进制即可；
        int al = a.length();
        int bl = b.length();
        int l = al >= bl ? al : bl;
        int at;
        int bt;
        int temp = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            //分别取值，取不到时赋值为0即可
            at = al - i >= 1 ? Character.getNumericValue(a.charAt(al - i - 1)) : 0;
            bt = bl - i >= 1 ? Character.getNumericValue(b.charAt(bl - i - 1)) : 0;
            //相加
            int t = at + bt + temp;
            if (t >= 2) {
                sb.insert(0, t % 2);
                temp = 1;
            } else {
                sb.insert(0, t);
                temp = 0;
            }
        }
        if (temp == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    public String addBinary2(String a, String b) {
        //思路2：先转为十进制，相加后再转为二进制即可；
        //问题在于Integer.MAX_VALUE位二进制转为整数后，只能用bigInteger表示

        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    @Test
    public void fun() {
        addBinary2("10", "10");
    }
}
