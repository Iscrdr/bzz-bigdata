package com.bzz.cloud.datastructure;

/**
 * @author ：Iscrdr
 * @description：选择排序
 * @email ：624003618@qq.com
 * @date ：2021-01-18 13:23
 * @modified By：
 * @version: 1.0.0
 */
public class SelectSort {
    /**
     * 选择排序：找到最小的元素放在第一位
     * 1,假设第一位为最小的元素,从第二位开始和第一位比较,如果比第一位小,则和第一位元素换位置
     * 时间复杂度：第一次循环比较n-1次,第二次循环比较n-2次,依次类推,最后一个元素不需要比较,因此需要进行n-1次循环,最后一次循环比较1次
     * 一共比较(n-1)+(n-2)+...+3+2+1,求和得 n*n/2 - n/2 +T,忽略系数，取最高指数项，该排序的时间复杂度为O(n2)
     * 1)忽略参数量T,
     * 2)忽略n/2,假如有10000个数,n*n = 1亿,n/2为5000,5000相对于1亿 是可以忽略得
     * 3)取指数最高项 n*n
     */
    public static int [] sort(int [] arr){

        for (int i = 0;i<arr.length;i++){
            //假设第一位是最小的元素
            int min = i;
            //从第二位元素开始找出比第一位还小的元素,直到找完剩下所有的元素
            for(int j = i+1;j<arr.length;j++){
                //寻找比第小的元素还小的元素
                if(arr[j]<arr[min]){
                    min = j;
                }
            }
            int temp =  arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

}
