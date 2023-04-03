import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;
    private int n;
    private Item[] q;

    public RandomizedQueue() {
        n = 0;
        q = (Item[]) new Object[INIT_CAPACITY];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == q.length) {
            resize(n * 2);
        }
        q[n++] = item;
    }

    private void resize(int size) {
        Item[] resized = (Item[]) new Object[size];

        for (int i = 0; i < n; i++) {
            resized[i] = q[i];
        }

        q = resized;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        }

        int i = StdRandom.uniform(n);
        Item item = q[i];
        q[i] = null;
        q[i] = q[--n];

        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniform(n);
        return q[i];
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomListIterator();
    }

    private class RandomListIterator implements Iterator<Item> {
        private int current;
        private int[] order;

        public RandomListIterator() {
            current = 0;
            order = new int[n];
            for (int i = 0; i < n; i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);
        }


        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return q[order[current++]];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        System.out.println("-isempty " + rq.isEmpty());

        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");
        rq.enqueue("four");
        System.out.println("-initial " + rq.size());

        System.out.println("-dequeue " + rq.dequeue());

        for (String s : rq) {
            System.out.println("  " + s);
        }

        System.out.println("-dequeued size " + rq.size());

        System.out.println("-sample " + rq.sample());
        System.out.println("-sample " + rq.sample());
        System.out.println("-sample " + rq.sample());

        System.out.println("-random iterator");
        for (String s : rq) {
            System.out.println(s);
        }

        System.out.println("-random iterator one more time");
        for (String s : rq) {
            System.out.println(s);
        }
    }
}
