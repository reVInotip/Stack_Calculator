package model.operations.exceptions.arithmetic;

import model.operations.exceptions.OperationException;

public class ConstantNotDefineException extends ArithmeticException {
    public ConstantNotDefineException(String message) {
        super(message);
    }
}
