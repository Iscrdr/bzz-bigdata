package com.bzz.cloud.datastructure;

/**
 * @author ：Iscrdr
 * @description：归并排序
 * @email ：624003618@qq.com
 * @date ：2021-01-22 03:04
 * @modified By：
 * @version: 1.0.0
 */
public class MergeSort {

    public static void sort(int [] arr,int left,int right){
        if(left == right){
            return ;
        }
        //分成两半,定义中间值
        int mid = (left+right)/2;
        //左边排序
        sort(arr,left,mid-1);
        //右边排序
        sort(arr,left,mid-1);

        merge(arr, left, mid + 1, right);

    }
    /**
     *
     * @param arr
     * @return
     */
    public static void merge(int [] arr,int leftPtr,int rightPtr,int rightBound){
        int mid = rightPtr - 1;
        int [] temp  = new int[rightBound-leftPtr+1];
        int i=leftPtr;
        int j = rightPtr;
        int k = 0;

        while(i<=mid && j<=rightBound){
            if(arr[i]<arr[j]){
                temp[k++] = arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }
        while(i<=mid){
            temp[k++] = arr[i++];
        }
        while (j<=rightBound){
            temp[k++] = arr[j++];
        }


        for(int m=0;m<temp.length;m++){
            arr[leftPtr+m] = temp[m];
        }
    }


    public static int [] sort2(int [] arr){
        int mid = arr.length/2 ;
        int [] temp = new int[arr.length];


        int j = mid + 1;
        int k = 0;
        int i = 0;
        while(i<=mid && j<arr.length){
            if(arr[i]<arr[j]){
                temp[k] = arr[i];
                i++;
            }else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i<=mid){
            temp[k] = arr[i];
            k++;
            i++;
        }
        while (j<arr.length){
            temp[k] = arr[j];
            k++;
            j++;
        }

        return temp;
    }
}
