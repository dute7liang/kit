package com.duteliang.test.leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * leet code 每日三题
 * <br/>
 * author: zl
 * Date: 2020/3/10 14:32
 */
public class Main {

    public static void main(String[] args) {
        int[] num1 = new int[]{1,2,2,1};
        int[] num2 = new int[]{2,2};

        int[] ints = intersection(num1,num2);
        for (int i : ints) {
            System.out.println(i);
        }

    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        int[] a = new int[nums1.length];
        int jj = 0;
        Set<Integer> have = new HashSet<>();
        for (int i : nums2) {
            if(!have.contains(i) && set.contains(i)){
                a[jj++] = i;
                have.add(i);
            }
        }
        return Arrays.copyOf(a,jj);
    }

}
