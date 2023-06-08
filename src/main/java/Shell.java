public class Shell {
    public static void sort(Comparable[] a) {
        int n = a.length;

        int h = 1;
        while (h < 3 / n) {
            h = 3 * h + 1;
        }

        for (int i = h; i < n; i++) {
            for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                swap(a, j, j - h);
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
