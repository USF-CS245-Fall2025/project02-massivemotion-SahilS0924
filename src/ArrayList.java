import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable<T> {

    private T[] arr;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        arr = (T[]) new Object[10];
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings({"unchecked", "ManualArrayToCollectionCopy"})
    protected void grow_array() {
        T[] new_arr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            new_arr[i] = arr[i];
        }
        arr = new_arr;
    }

    @Override
    public boolean add(T item) {//Add at end
        if (size == arr.length - 1)
            grow_array();
        arr[size++] = item;
        return true;
    }

    @Override
    public void add(int pos, T item) {//Add at position pos
        if (pos < 0 || pos > size) {
            System.out.println("List index out of bounds");
            return;
        }
        if (size == arr.length - 1)
            grow_array();

        for (int i = size; i > pos; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos] = item;
        size++;
    }

    @Override
    public T remove(int index) {//Remove at position index
        if (index < 0 || index >= size) {
            System.out.println("List index out of bounds");
            return null;
        }

        T copy = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return copy;
    }

    @Override
    public T get(int pos) {//Get element at position pos
        if (pos < 0 || pos >= size) {
            System.out.println("List index out of bounds");
            return null;
        }
        return arr[pos];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {//Iterator class
        private int pos = 0;

        @Override
        public boolean hasNext() {//Checks for a next element
            return pos < size;
        }

        @Override
        public T next() {//Return next element
            return arr[pos++];
        }
    }
}
