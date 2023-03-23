import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int trials;
    private final double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.trials = trials;
        results = new double[trials];
        runTest();
    }

    private void runTest() {
        for (int i = 0; i < trials; i++) {
            int count = countUntilPercolate();
            results[i] = (double) count / (n * n);
        }
    }

    private int countUntilPercolate() {
        int count = 0;
        Percolation per = new Percolation(n);
        while (!per.percolates()) {
            int row;
            int col;
            do {
                row = StdRandom.uniform(n) + 1;
                col = StdRandom.uniform(n) + 1;
            } while (per.isOpen(row, col));

            per.open(row, col);

            count++;
        }
        return count;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }


    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int N = 200;
        int T = 100;
        PercolationStats ps = new PercolationStats(N, T);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}