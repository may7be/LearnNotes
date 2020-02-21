package monitordemo.demo.myapp.myapplication.leetcode;

/**
 * @Author zhao on 2020-02-21
 * <p>
 * /*
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 */
public class RomeNum2Int {
    public int romanToInt(String s) {
        //思路
        //1. s是罗马数字，说明肯定是合法的，不会出现别的字符。而且是1-3999之间，也排除了0的可能（查资料发现确实罗马数字里没有0）
        //2. 因为6种特殊情况，可以直接从s的长短，依次判断，一位的，二位的，多位的。但是IIL到底能不能表示48呢，题目也没说清楚（应该是不能的）。
        //3. 确实上面的理解有问题，但是48也绝不对用IIL表示，更常见的是LIV表示54。
        // 意思就是从左往右，如果左边大就做加法，右边大就做减法即可。但是DM这种表示啥还真不好说，或者说是错误写法，应该是错误写法。

        int length = s.length();
        int num = 0;
        for (int i = 0; i < length; i++) {
            int value = getValue(s.charAt(i));
            if (i + 1 >= length) {
                num += value;
            } else {
                int nextValue = getValue(s.charAt(i + 1));
                if (value < nextValue) {
                    num -= value;
                } else {
                    num += value;
                }
            }
        }
        return num;
    }

    public int romanToInt2(String s) {
        //思路
        //方法1的主要问题在于每次循环都要取两次值，完全可以从1开始循环，然后取到当前值后跟pre相比，然后再决定+或者-pre就好
        int length = s.length();
        int num = 0;
        int preValue = getValue(s.charAt(0));
        //1.考虑只有1位的情况
        for (int i = 1; i < length; i++) {
            int value = getValue(s.charAt(i));
            if (preValue < value) {
                num -= preValue;
            } else {
                num += preValue;
            }
            preValue = value;
            //2.还有最后一位，也没计算上
        }
        //如果只有1位，返回即可；如果是多位，也需要把preValue加上。同时满足了1.2
        num += preValue;
        return num;
    }

    private int getValue(char ch) {
        //char用单引号表示
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("非法字符");
        }
    }
}
