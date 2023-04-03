import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dequeue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int count;

    private static class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }

    public Dequeue() {
        first = null;
        last = null;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> oldFirst = first;

        first = new Node<>();
        first.item = item;

        if (count == 0) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        count++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> oldLast = last;

        last = new Node<>();
        last.item = item;

        if (count == 0) {
            first = last;
        } else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first.item = null;

        if (count == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (count == 0) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        last.item = null;

        if (count == 1) {
            last = null;
            first = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        count--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Dequeue<String> q = new Dequeue<>();
        q.addFirst("1");
        q.addFirst("0");
        q.addLast("2");
        q.addLast("3");

        q.removeFirst();
        q.removeLast();

        for (String a : q) {
            StdOut.println(a);
        }

        StdOut.println(q.size());
        StdOut.println(q.isEmpty());
    }
}
