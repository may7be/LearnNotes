package com.demo.algorithm;

/**
 * @author ZhaoKeqiang
 * @date 2018/6/10
 * TODO:
 */
public class Sort {
    /**
     * 冒泡排序
     *
     * @param arr a
     */
    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        boolean isSorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            /*for (int j = arr.length - 1 - i; j > i; j--) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j);
                }
            }*/
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSorted = false;
                }
            }
            if (isSorted) {
                return;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr a
     */
    private void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, minIndex, i);
            }
        }

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
