public interface List<T> {

    void add(int index, T element) throws IndexOutOfBoundsException, NullPointerException;
    //invalid index amd null element are errors

    boolean add(T element) throws NullPointerException;
    //null element not allowed

    T get(int index) throws IndexOutOfBoundsException;
    //index must be within bounds

    T remove(int index) throws IndexOutOfBoundsException;
    //index must be valid

    int size();
    //no need for exceptions
}
