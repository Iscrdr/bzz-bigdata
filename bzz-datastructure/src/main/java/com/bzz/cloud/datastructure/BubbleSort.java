package com.bzz.cloud.datastructure;

/**
 * @author ：Iscrdr
 * @description：冒泡排序
 * @email ：624003618@qq.com
 * @date ：2021-01-18 14:10
 * @modified By：
 * @version: 1.0.0
 */
public class BubbleSort {
    /**
     * 冒泡排序：比较相邻得两个元素,如果第二元素比第一个小,交换两个元素得位置
     *1.如果数据是正序的，只需要走一趟即可完成排序。所需的比较次数和记录移动次数均达到最小值，所以，冒泡排序最好的时间复杂度为O(n)。
     *2.如果数据是反序的，则需要进行n-1趟排序。每趟排序要进行n-i次比较(1 ≤ i ≤n-1)，且每次比较都必须移动记录三次来达到交换记录位置。
     * 在这种情况下，比较和移动次数均达到最大次数，冒泡排序的最坏时间复杂度为：O(n2) 。平均时间复杂度是O(n2)。
     */
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                //两个元素交换位置
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}
