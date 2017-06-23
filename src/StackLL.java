/**
 * Created by falko on 20-06-17.
 */
public class StackLL<L> {

    class SNode<T> {

        T value;
        SNode<T> next;

        public SNode(T elem) {
            this.value = elem;
            this.next = null;
        }

    }

    private SNode<L> top;
    private int size;

    public StackLL() {
        this.top = null;
        this.size = 0;
    }

    public L pop() throws StackUnderflowException {
        SNode<L> temp = this.top;
        L poppedData;
        if (isEmpty()) {
            throw new StackUnderflowException("Can't pop from an empty Stack!");
        } else {
            this.top = top.next;
            temp.next = null;
            poppedData = temp.value;
            this.size--;
            return poppedData;
        }
    }

    public void push(L dataValue) {
        SNode<L> newTop = new SNode<>(dataValue);
        SNode<L> temp = this.top;
        this.top = newTop;
        top.next = temp;
        this.size++;
    }

    public int size() {
        return size;
    }

    public L peek() {
        return this.top.value;
    }

    public boolean isEmpty() {
        return (this.size <= 0);
    }


}
