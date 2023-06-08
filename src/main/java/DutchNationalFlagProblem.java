public class DutchNationalFlagProblem {

    private static void sort(int[] a) {
        int lo = 0, mid = 0;
        int hi = a.length - 1;

        while (mid <= hi) {
            switch (a[mid]) {
                case 0 -> swap(a, lo++, mid++);
                case 1 -> mid++;
                case 2 -> swap(a, mid, hi--);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        final int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
        sort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
