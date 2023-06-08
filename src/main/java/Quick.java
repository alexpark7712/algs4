import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            swap(a, i, j);
        }

        swap(a, lo, j);

        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        StdRandom.shuffle(a);

        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);

            if (j > k) {
                hi = j - 1;
            } else if (j < k) {
                lo = j + 1;
            } else {
                return a[j];
            }
        }
        
        //마지막 partition 이 원하는 위치가 아니었으나, 다음 partition 해보려고 하니 hi, lo 가 이미 교차한 상태 그럼 그 위치가 답 일 수 밖에 없음
        return a[lo];
    }

    public static void main(String[] args) {
        Integer[] a = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        sort(a);

        for (Integer integer : a) {
            System.out.print(integer + " ");
        }

        System.out.println();
        
        Integer[] a2 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        System.out.println(select(a2, 2));
    }
}
