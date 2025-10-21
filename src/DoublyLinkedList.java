public class DoublyLinkedList<T> implements List<T> {

    @SuppressWarnings("hiding")
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            data = value;
            next = null;
            prev = null;
        }
    }

    int size;
    Node<T> head;
    Node<T> tail;

    public DoublyLinkedList() {//Node Constructor
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public int size() {//Return size
        return size;
    }

    @Override
    public boolean add(T item) {//Add at end
        Node<T> newNode = new Node<>(item);
        if (size == 0) {//adding the first node
            head = newNode;
            tail = newNode;
        } else {//adding normally
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void add(int pos, T item){//Add at position pos
        if (pos < 0 || pos > size){
            System.out.println("List index out of bounds");}

        Node<T> newNode = new Node<>(item);

        if (pos == 0) {
            newNode.next = head;
            if (head != null)
                head.prev = newNode;
            head = newNode;
            if (tail == null)
                tail = newNode;
            size++;
            return;
        }

        if (pos == size) { //add at end
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < pos; i++)
            current = current.next;

        Node<T> previous = current.prev;
        previous.next = newNode;
        newNode.prev = previous;
        newNode.next = current;
        current.prev = newNode;

         size++;
    }

    @Override
    public T remove(int pos){//Remove at position pos
        if (pos < 0 || pos >= size){
            System.out.println("List index out of bounds");}

        Node<T> current = head;

        if (pos == 0) {//remove head
            T value = head.data;
            head = head.next;
            if (head != null)//not empty list
                head.prev = null;
            else//list is empty
                tail = null;
             size--;
            return value;
        }

        if (pos == size - 1) {//remove tail
            T value = tail.data;
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
            else
                head = null;
             size--;
            return value;
        }

        for (int i = 0; i < pos; i++)
            current = current.next;

        T value = current.data;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return value;
    }

    @Override
    public T get(int pos){//Get at position pos
        if (pos < 0 || pos >= size){
           System.out.println("List index out of bounds");}

        Node<T> node;
        if (pos < size / 2) {//get from head
            node = head;
            for (int i = 0; i < pos; i++)
                node = node.next;
        } else {//get from tail
            node = tail;
            for (int i = size - 1; i > pos; i--)
                node = node.prev;
        }
        return node.data;
    }
}
