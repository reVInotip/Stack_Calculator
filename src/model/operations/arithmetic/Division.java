package model.operations.arithmetic;

import model.operations.Operation;
import model.operations.Operations;
import model.operations.exceptions.InvalidArgsException;
import model.operations.exceptions.InvalidStackException;
import model.operations.exceptions.arithmetic.ConstantNotDefineException;
import model.operations.exceptions.arithmetic.ZeroDivisionException;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Operation(name = "/", type = "arithmetic")
public class Division implements Operations {
    @Override
    public Object run(Stack<Object> stack, List<Object> args) throws
            InvalidStackException, InvalidArgsException, ConstantNotDefineException, ZeroDivisionException {
        if (stack == null) {
            throw new InvalidStackException("Stack is undefined");
        } else if (stack.size() < 2) {
            throw new InvalidStackException("Stack must contain at least two arguments");
        } else if (args.isEmpty()) {
            throw new InvalidArgsException("Args is empty");
        }

        Object op1 = stack.pop();
        Object op2 = stack.pop();

        //noinspection unchecked
        HashMap<String, Double> constants = args.getFirst() instanceof HashMap ?
                (HashMap<String, Double>) args.getFirst() : null;

        if (constants == null) {
            throw new InvalidArgsException("First argument must be a HashMap<String, Double> type");
        }

        Double value1 = 0.0;
        Double value2 = 0.0;
        if (op1 instanceof Double && op2 instanceof Double) {
            value1 = (Double) op1;
            value2 = (Double) op2;
        } else if (op1 instanceof String && op2 instanceof Double) {
            if ((value1 = constants.get(op1)) == null) {
                throw new ConstantNotDefineException("Constant " + op1 + " is not define");
            }
            value2 = (Double) op2;
        } else if (op1 instanceof Double && op2 instanceof String) {
            if ((value2 = constants.get(op2)) == null) {
                throw new ConstantNotDefineException("Constant " + op2 + " is not define");
            }
            value1 = (Double) op1;
        } else if (op1 instanceof String && op2 instanceof String) {
            if (((value1 = constants.get(op1)) == null) || ((value2 = constants.get(op2)) == null)) {
                throw new ConstantNotDefineException("Constants " + op1 + ", " + op2 + " is not define");
            }
        } else {
            return null;
        }

        if (value2 == 0.0) {
            throw new ZeroDivisionException("Division by zero");
        }
        stack.push(value1 / value2);
        return null;
    }
}
