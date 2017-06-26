/**
 * A generic Stack data structure implemented using a LinkedList.
 * It operates like a usual stack: Users can push to and pop from the
 * stack in a LIFO behavior.
 * @author Falko Noe
 * @version 1.0
 */
public class StackLL<L> {

  /**
   * A generic class Node, which defines the
   * behavior of the LinkedList: each Node carries
   * a data-value as well as the references to the next item,
   * which in the case of the stack is an item one step farther
   * down the Stack.
   * @param <T>: The datatype the Node is instantiated with
   */
  class SNode<T> {

    T value;
    SNode<T> next;

    /**
     * The constructor for the Node
     * @param elem: The data value of datatype T
     */
    public SNode(T elem) {
      this.value = elem;
      this.next = null;
    }

  }

  private SNode<L> top;
  private int size;

  /**
   * The Constructor for the Stack, which is instantiated
   * as empty.
   */
  public StackLL() {
    this.top = null;
    this.size = 0;
  }

  /**
   * Pop method which allows the user to remove an item from the top of the
   * Stack. Returns the data value of the Node that was just popped. Can
   * throw an exception if the operation is performed on an Empty stack
   * @return: The data value corresponding to the popped Node.
   * @throws StackUnderflowException: Thrown if the method is performed on an
   *                                  empty stack
   */
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

  /**
   * Pushes the data value to the top of the stack.
   * @param dataValue: The datum that is pushed to the top of
   *                   the Stack.
   */
  public void push(L dataValue) {
    SNode<L> newTop = new SNode<>(dataValue);
    SNode<L> temp = this.top;
    this.top = newTop;
    top.next = temp;
    this.size++;
  }

  /**
   * Return the current size of the Stack.
   * @return: The size of the Stack as a function of the
   * number of items contained within it as an integer.
   */
  public int size() {
    return size;
  }

  /**
   * Get data value corresponding to the top Node of the Stack
   * @return: The data value of type L corresponding to the
   * top of the Stack
   */
  public L peek() {
    return this.top.value;
  }

  /**
   * Checks whether the Stack is empty.
   * @return: True if the stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return (this.size <= 0);
  }


}
