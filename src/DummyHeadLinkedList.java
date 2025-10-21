public class DummyHeadLinkedList<T> implements List<T> {

    @SuppressWarnings("hiding")
    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T value) {//Node Constructor
            data = value;
            next = null;
        }
    }

    int size;
    Node<T> head; //dummy head

    public DummyHeadLinkedList() {
        size = 0;
        head = new Node<>(null); //dummy node
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T item) {//Add at end
        Node<T> node = head;
        while (node.next != null)
            node = node.next;

        node.next = new Node<>(item);
        size++;
        return true;
    }

    @Override
    public void add(int pos, T item){//Add at position pos
        if (pos < 0 || pos > size){
            System.out.println("List index out of bounds");}

        Node<T> prev = head;
        for (int i = 0; i < pos; i++)
            prev = prev.next;

        Node<T> newNode = new Node<>(item);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    @Override
    public T remove(int pos){//Remove at position pos
        if (pos < 0 || pos >= size){
           System.out.println("List index out of bounds");}

        Node<T> prev = head;
        for (int i = 0; i < pos; i++)
            prev = prev.next;

        Node<T> current = prev.next;
        prev.next = current.next;
        size--;
        return current.data;
    }

    @Override
    public T get(int pos){//Get element at position pos
        if (pos < 0 || pos >= size){
            System.out.println("List index out of bounds");}

        Node<T> node = head.next;
        for (int i = 0; i < pos; i++)
            node = node.next;

        return node.data;
    }
}
