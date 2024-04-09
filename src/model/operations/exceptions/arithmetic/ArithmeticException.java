package model.operations.exceptions.arithmetic;

import model.operations.exceptions.OperationException;

public abstract class ArithmeticException extends OperationException {
    public ArithmeticException(String message) {
        super(message);
    }
}
