package com.bzz.cloud.datastructure;

/**
 * @author ：Iscrdr
 * @description：插入排序
 * @email ：624003618@qq.com
 * @date ：2021-01-18 18:36
 * @modified By：
 * @version: 1.0.0
 */
public class InsertSort {

    /**
     * [4,2,6,7,3]
     * 属于稳定的排序，适合于数据量小，部分数据有序的情况排序
     * 最好情况就是，序列已经是升序排列了，在这种情况下，需要进行的比较操作需（n-1）次即可。
     * 最坏情况就是，序列是降序排列，那么此时需要进行的比较共有n(n-1)/2次。
     * 插入排序的赋值操作是比较操作的次数加上 (n-1）次。
     * 平均来说插入排序算法的时间复杂度为O(n^2）。
     * 因而，插入排序不适合对于数据量比较大的排序应用。
     * 但是，如果需要排序的数据量很小，例如，量级小于千，那么插入排序还是一个不错的选择
     * @param arr
     * @return
     */
    public static int [] sort(int [] arr){

        for (int i = 1;i<arr.length;i++){
            for(int j=i;j>0;j--){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

}
