/**
 * Custom Exception class for use with the StackLL
 * data structure defined in this project. Intended to be
 * thrown for pop() functionality, to ensure that empty
 * stacks are never popped, i.e. underflow is avoided.
 * @author Falko Noe
 * @version 1.0
 */
class StackUnderflowException extends Exception {

    /**
     * Simply calls Exception constructor for
     * displaying the provided message to stderr
     * @param message: The string explaining the error
     *               and why it occurred.
     */
    StackUnderflowException(String message) {
        super(message);
    }
}
