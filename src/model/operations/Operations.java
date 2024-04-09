package model.operations;

import model.operations.exceptions.OperationException;
import java.util.List;
import java.util.Stack;

public interface Operations {
    Object run(Stack<Object> stack, List<Object> args) throws OperationException;
}
