package com;

import java.util.Arrays;

/**
 * @Author tdragon.
 * @Date 2020/9/29.
 * @Time 22:54
 * @Description:选择排序 就是找最小/最大的数据 看你怎么排序吧 找到后，继续剩下的排，直到排完
 * （数组长度减一次 循环排）当然具体算法 嵌套 循环都是0~~~length-1
 * O（n^2）
 */

public class SelectionSort {
       public static int[] selectsort(int [] A)
       {
           long s=System.currentTimeMillis();
           if (A.length == 0) {
               return A;
           }
      for(int i=0;i<A.length;i++)
      {
          int smallest=i;
          for (int j=i;j<A.length;j++)
          {
            if (A[j]<A[smallest])
                //找到最小的数 我是这么理解的
                // 内层循环中的数据小于/（--）每一个外层循环的每一个数，
                // 或者说趋近于它 也就是等于
            {smallest=j;}
                //将最小数的索引保存
            int temp=A[smallest];
            A[smallest]=A[i];
            A[i]=temp;
          }
      }
           long e=System.currentTimeMillis();
           System.out.println("waste time:"+(e-s));
      return A;
   }


//    public static int[] selectionSort(int[] array) {
//        if (array.length == 0)
//        {
//            return array;
//        }
//        for (int i = 0; i < array.length; i++) {
//            int minIndex = i;
//            for (int j = i; j < array.length; j++) {
//                if (array[j] < array[minIndex]) //找到最小的数
//                minIndex = j; //将最小数的索引保存
//            }
//            int temp = array[minIndex];
//            array[minIndex] = array[i];
//            array[i] = temp;
//        }
//        return array;
//    }


    public static void main(String[] args) {
        int [] a={6,1,7,5,3,4};
        System.out.println(Arrays.toString(selectsort(a)));
       }
}
