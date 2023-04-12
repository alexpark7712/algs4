/*
 * Copyright (c) 2023 Kakao Corporation. All rights reserved.
 * Kakao Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

public class Insertion {
    public static void sort(Comparable[] a) {
        int size = a.length;
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    swap(a, j, j - 1);
                }
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        sort(a);

        for (Integer integer : a) {
            System.out.print(integer + " ");
        }
    }
}
