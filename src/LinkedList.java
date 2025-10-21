import java.util.Iterator;

public class LinkedList<T> implements List<T>, Iterable<T> {

    private class Node {
        T data;
        Node next;

        public Node(T value) {//Node Constructor
            data = value;
            next = null;
        }
    }

    private int size;
    private Node head;

    public LinkedList() {
        size = 0;
        head = null;
    }

    @Override
    public int size() {//Return size
        return size;
    }

    @Override
    public boolean add(T item) {//Add at end
        if (size == 0) {//empty list
            head = new Node(item);
           size++;
            return true;
        }

        Node node = head;
        while (node.next != null)
            {node = node.next;}

        node.next = new Node(item);
        size++;
        return true;
    }

    @Override
    public void add(int pos, T item) {//Add at position pos
        if (pos < 0 || pos > size) {
            System.out.println("List index out of bounds");
            return;
        }

        Node newNode = new Node(item);

        if (pos == 0) {//Add at head
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        Node prev = head;
        for (int i = 0; i < pos - 1; i++)
            {prev = prev.next;}

        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    @Override
    public T remove(int pos) {//Remove at position pos
        if (pos < 0 || pos >= size) {
            System.out.println("List index out of bounds");
            return null;
        }

        if (pos == 0) {//Remove head
            T data = head.data;
            head = head.next;
            size--;
            return data;
        }

        Node prev = head;
        for (int i = 0; i < pos - 1; i++){
            prev = prev.next;}

        Node current = prev.next;
        prev.next = current.next;
        size--;
        return current.data;
    }

    @Override
    public T get(int pos) {//Get element at position pos
        if (pos < 0 || pos >= size) {
            System.out.println("List index out of bounds");
            return null;
        }

        Node node = head;
        for (int i = 0; i < pos; i++)
            {node = node.next;}

        return node.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {//Iterator class
        private Node current = head;

        @Override
        public boolean hasNext() {//Checks for a next element
            return current != null;
        }

        @Override
        public T next() {//Return next element
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
