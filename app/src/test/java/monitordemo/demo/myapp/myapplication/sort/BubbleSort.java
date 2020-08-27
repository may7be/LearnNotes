package monitordemo.demo.myapp.myapplication.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author zhao on 2020/8/26
 */
public class BubbleSort {

    /**
     * 基础版本，经过两轮的交换
     *
     * @param arr ar
     */
    public int[] sort(int[] arr) {
        System.out.println(System.currentTimeMillis());
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int len = arr.length;
        //从小到大: len-1轮排序
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                int temp = arr[j];
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(System.currentTimeMillis());
        return arr;
    }

    /**
     * 优化1：如果没有元素移动了，说明已经有序了，无需继续下一轮
     * 增加了一个boolean：isSorted
     *
     * @param arr ar
     */
    public int[] sort1(int[] arr) {
        System.out.println(System.currentTimeMillis());
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int len = arr.length;
        //从小到大: len-1轮排序
        for (int i = 0; i < len - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < len - 1 - i; j++) {
                int temp = arr[j];
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
        System.out.println(System.currentTimeMillis());
        return arr;
    }

    /**
     * 优化2：如果没有元素移动了，说明已经有序了，无需继续下一轮; isSorted
     * 有序区的长度和排序的轮数默认是相等的，可以记录最后一次数据交换的位置，作为下一轮循环的终点; lastExchangePos, endPos
     *
     * @param arr ar
     */
    public int[] sort2(int[] arr) {
        System.out.println(System.currentTimeMillis());
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int len = arr.length;
        //从小到大: len-1轮排序
        int lastExchangePos = 0;
        int endPos = len - 1;
        for (int i = 0; i < len - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < endPos; j++) {
                int temp = arr[j];
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                    //更新位置
                    lastExchangePos = j;
                }
            }
            if (isSorted) {
                break;
            }
            endPos = lastExchangePos;
        }
        System.out.println(System.currentTimeMillis());
        return arr;
    }

    /**
     * 鸡尾酒排序
     * 优化3：如果没有元素移动了，说明已经有序了，无需继续下一轮; isSorted
     * 有序区的长度和排序的轮数默认是相等的，可以记录最后一次数据交换的位置，作为下一轮循环的终点; lastExchangePos, endPos
     * 优化1只能解决一端有序时的冗余问题，另一端有序时依然还是要排序。故采用鸡尾酒法，依次两端轮询。
     *
     * @param arr ar
     */
    public int[] sort3(int[] arr) {
        //1. 判空
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int len = arr.length;
        //2. 从小到大: 默认len-1轮排序；采用偶数轮从左到右，奇数轮从右向左
        int lastRightExchangePos = 0;
        int lastLeftExchangePos = 0;
        int endPos = len - 1;
        int startPos = 0;
        for (int i = 0; i < len - 1; i++) {
            boolean isSorted = true;
            if (i % 2 == 0) {
                //从左向右
                for (int j = startPos; j < endPos; j++) {
                    int temp = arr[j];
                    if (arr[j] > arr[j + 1]) {
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        isSorted = false;
                        //更新右位置
                        lastRightExchangePos = j;
                    }
                }
            } else {
                //从右向左
                for (int k = endPos; k > startPos; k--) {
                    int temp = arr[k];
                    if (arr[k - 1] > arr[k]) {
                        arr[k] = arr[k - 1];
                        arr[k - 1] = temp;
                        isSorted = false;
                        //更新左位置
                        lastLeftExchangePos = k;
                    }
                }
            }

            if (isSorted) {
                break;
            }
            endPos = lastRightExchangePos;
            startPos = lastLeftExchangePos;
        }
        System.out.println(System.currentTimeMillis());
        return arr;
    }

    /**
     * 简单版的鸡尾酒排序
     */
    public int[] sort4(int[] arr) {
        //1. 判空
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        //2. 进行len/2轮：每一轮从左到右，然后从右到左
        int len = arr.length;
        for (int i = 0; i < len / 2; i++) {
            //左->右
            for (int j = i; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //右->左
            for (int k = len - 1 - i; k > i; k--) {
                if (arr[k] < arr[k - 1]) {
                    int temp = arr[k];
                    arr[k] = arr[k - 1];
                    arr[k - 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 优化版的鸡尾酒排序：增加对剩下数组是否有序的判断；以及有序区的判断。
     */
    public int[] sort5(int[] arr) {
        //1. 判空
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        //2. 进行len/2轮：每一轮从左到右，然后从右到左
        int len = arr.length;
        int rightPos = len - 1;
        int leftPos = 0;
        int rightExchangePos = len - 1;
        int leftExchangePos = 0;
        for (int i = 0; i < len / 2; i++) {
            boolean isSorted = true;
            //左->右
            for (int j = leftPos; j < rightPos; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                    rightExchangePos = j;
                }
            }
            rightPos = rightExchangePos;
            //已经有序
            if (isSorted) {
                break;
            }
            isSorted = true;
            //右->左
            for (int k = rightPos; k > leftPos; k--) {
                if (arr[k] < arr[k - 1]) {
                    int temp = arr[k];
                    arr[k] = arr[k - 1];
                    arr[k - 1] = temp;
                    isSorted = false;
                    leftExchangePos = k;
                }
            }
            leftPos = leftExchangePos;
            if (isSorted) {
                break;
            }
        }
        return arr;
    }

    @Test
    public void fun() {
        int[] arr = {8, 1, 3, 5, 9, 4, 7, 2, 6, 0, -1, -1111, -111, -110, -99, -93, -90, -23, 4, 32, 5, 3, 66, 364, 64, 546, 454, 3232, 2454, 245};
//        System.out.println(Arrays.toString(sort(arr)));
//        System.out.println(Arrays.toString(sort1(arr)));
//        System.out.println(Arrays.toString(sort2(arr)));
        System.out.println(Arrays.toString(sort5(arr)));
    }
}
