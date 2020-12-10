package com;

import java.util.Arrays;

/**
 * @Author tdragon.
 * @Date 2020/9/28.
 * @Time 19:51
 * @Description:  二分查找 必须是有序数据查找 而且是升序 基本思想：对于给定值 x，从序列的中间位置开始比较，如果当前位置值等于 x，则查找成功；
 * 若 x 小于当前位置值，则在数列的前半段中继续折半查找；若 x 大于当前位置值则在数列的后半段中继续查找，直到找到为止。
 * 否则 返回查找失败
 * 时间复杂度 O(lgn)
 * 全部根据教材伪代码（文字形式）所写（变量名什么都一样）
 * 本程序 n为 数组A的元素个数 即长度 。x为要找的数
 * 实际上Arrays直接有二分查找方法 调用即可。但是我们得了解源码，学习牛人
 */

public class Binary_Search {
    public static int bsearch(int [] A , int n, int x) {
        long startTime=System.currentTimeMillis();
        Arrays.sort(A);
        //数组升序 性能大大下降 时间增长 因为数组要排序
            int p   = 0;
            int r = n-1;
            int count=0;
            while (p <= r) {
                count++;
                //p+(r-p)
                int q = ( p+r) / 2;
                System.out.println("第"+count+"次查找索引位置"+q);
                if (A[q] > x) {
                    r = q - 1   ;
                }
                else if (A[q] < x) {
                    p = q + 1;
                }
                else if (A[q] == x) {
                    return q;
                     }
                long endTime=System.currentTimeMillis();
                System.out.println("此次查询共花费" + (endTime - startTime) + "毫秒的时间。");
            }
        return -1;
    }
    /**
     * 普通for循环查询指定的数字例子
     * @param num
     * @return 
     */
    public static  int forExample(int num,int [] array){
        long startTime=System.currentTimeMillis();
        System.out.println("****************使用普通循环查找(线性)****************");
        int count=0;
        for (int i = 0; i < array.length; i++){
            count++;
            if(array.length - 1 == i && num != array[i]){
                System.out.println("抱歉，没有找到");
            }else if (num == array[i]){
                System.out.println(array[i] + "找到了，在数组下标为" + i + "的地方，查找了" + count + "次。");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("此次查询共花费" + (endTime - startTime) + "毫秒的时间。");
        return count;

    }
    public static void main(String[] args) {
          int [] array={1,3,4,8,9,86};
            //随机数组测试下性能
//            int  [] array=new int[10000000];
//            Random random = new Random();
//            System.out.println("****************初始化数组****************");
//            for (int i = 0; i < array.length; i++) {
//                array[i] = random.nextInt(array.length);
//            }

        int find =bsearch(array,array.length,5);
//            System.out.println(Arrays.toString(a));
            if (find==-1) {
                System.out.println("not found");
            }
            else{
                System.out.println("索引："+find);
            }
//            forExample(86,array);
    }
    }
