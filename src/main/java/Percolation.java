import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int OUT_OF_RANGE = -1;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final int top1D = 0;
    private final int bottom1D;
    private final boolean[] open;
    private int openCount;

    public Percolation(int n) {
        this.n = n;
        bottom1D = n * n + 1;

        int arraySize = n * n + 2;
        uf = new WeightedQuickUnionUF(arraySize);
        open = new boolean[arraySize];

        open[top1D] = true;
        open[bottom1D] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRange(row, col);

        int p = convertTo1D(row, col);

        if (open[p]) {
            return;
        }

        open[p] = true;
        openCount++;

        if (row == 1) {
            uf.union(p, top1D);
        } else if (row == n) {
            uf.union(p, bottom1D);
        }

        connectToNeighbors(row, col, p);
    }

    private void connectToNeighbors(int row, int col, int p) {
        int up = convertTo1D(row - 1, col);
        int down = convertTo1D(row + 1, col);
        int left = convertTo1D(row, col - 1);
        int right = convertTo1D(row, col + 1);

        if (up != OUT_OF_RANGE && open[up]) {
            uf.union(p, up);
        }
        if (down != OUT_OF_RANGE && open[down]) {
            uf.union(p, down);
        }
        if (left != OUT_OF_RANGE && open[left]) {
            uf.union(p, left);
        }
        if (right != OUT_OF_RANGE && open[right]) {
            uf.union(p, right);
        }
    }

    private int convertTo1D(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            return OUT_OF_RANGE;
        }
        return (row - 1) * n + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRange(row, col);

        return open[convertTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRange(row, col);

        int p = convertTo1D(row, col);

        return uf.connected(top1D, p);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top1D, bottom1D);
    }

    private void checkRange(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("wrong range");
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(3, 3);

        //percolation.open(3,2);
        //percolation.open(3,1);

        StdOut.println(percolation.numberOfOpenSites());
        StdOut.println(percolation.percolates());
    }
}
