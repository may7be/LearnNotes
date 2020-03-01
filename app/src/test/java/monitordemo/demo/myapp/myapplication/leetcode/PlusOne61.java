package monitordemo.demo.myapp.myapplication.leetcode;

import org.junit.Test;

import java.math.BigInteger;

/**
 * @Author zhao on 2020-03-01
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PlusOne61 {
    public int[] plusOne(int[] digits) {
        //1.判空,不过已经说明非空了 2.倒序遍历，从后往前加即可
        //思路2：先拿到代表的整数，然后加1，然后再转化为数组
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            //当前位置=9，需要特殊处理
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        //此时说明digits需要在首位补一个1
        int[] newArr = new int[length + 1];
        newArr[0] = 1;
        System.arraycopy(digits, 0, newArr, 1, length);
        return newArr;
    }

    public int[] plusOne2(int[] digits) {
        //思路2：先拿到代表的整数，然后加1，然后再转化为数组
        //这种想法不科学，数组的最大长度为21亿多，所对应的整数无法表示
        //应该会更耗时
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }
        //这里需要考虑越界问题，int最大位数位int的最大值
        //可惜BigInteger在java里用不了
        BigInteger num = new BigInteger(sb.toString());
        String str = String.valueOf(num.add(new BigInteger(String.valueOf(1))));
        int len = str.length();
        //这里依然会越界
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            //char '9',转为int 9,需要注意
            arr[i] = Character.getNumericValue(str.charAt(i));
            System.out.println(i + ": " + arr[i]);
        }
        return arr;
    }

    public int[] plusOne3(int[] digits) {
       //优化思路：向数组首位增加一个1时，可以 = new Int[];
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            //当前位置=9，需要特殊处理
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        //此时说明digits需要在首位补一个1
        digits = new int[length + 1];
        digits[0] = 1;
        return digits;
    }

    @Test
    public void fun() {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        plusOne2(arr);
    }

}
