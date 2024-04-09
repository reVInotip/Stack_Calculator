package model.operations.replacement;

import model.operations.Operations;
import model.operations.exceptions.OperationException;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public interface Replacement extends Operations {
    Object run(Stack<Object> stack, List<Object> args) throws OperationException;
    HashMap<String, Double> constants();
}
