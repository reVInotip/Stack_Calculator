package model.operations.arithmetic;

import model.operations.Operation;
import model.operations.Operations;
import model.operations.exceptions.InvalidArgsException;
import model.operations.exceptions.InvalidStackException;
import model.operations.exceptions.arithmetic.ConstantNotDefineException;
import model.operations.exceptions.arithmetic.ExtractRootFromNegativeNumberException;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Operation(name = "SQRT", type = "arithmetic")
public class SQRT implements Operations {
    @Override
    public Object run(Stack<Object> stack, List<Object> args) throws
            InvalidStackException, InvalidArgsException, ConstantNotDefineException, ExtractRootFromNegativeNumberException {
        if (stack == null) {
            throw new InvalidStackException("Stack is undefined");
        } else if (stack.isEmpty()) {
            throw new InvalidStackException("Stack must contain at least one argument");
        } else if (args.isEmpty()) {
            throw new InvalidArgsException("Args is empty");
        }

        Object op = stack.pop();

        //noinspection unchecked
        HashMap<String, Double> constants = args.getFirst() instanceof HashMap ?
                (HashMap<String, Double>) args.getFirst() : null;

        if (constants == null) {
            throw new InvalidArgsException("First argument must be a HashMap<String, Double> type");
        }

        Double value = 0.0;
        if (op instanceof Double) {
            value = (Double) op;
        } else if (op instanceof String) {
            value = constants.get(op);
            if (value == null) {
                throw new ConstantNotDefineException("Constant " + op + " is not define");
            }
        } else {
            return null;
        }

        if (value < 0) {
            throw new ExtractRootFromNegativeNumberException("Attempt to extract the root from negative number");
        }
        stack.push(Math.sqrt(value));
        return null;
    }
}
