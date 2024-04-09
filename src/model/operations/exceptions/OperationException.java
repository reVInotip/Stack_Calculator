package model.operations.exceptions;

public abstract class OperationException extends Exception {
    public OperationException(String message) {
        super(message);
    }
}
