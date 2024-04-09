package model.operations.stack;

import model.operations.Operation;
import model.operations.Operations;
import model.operations.exceptions.InvalidStackException;

import java.util.List;
import java.util.Stack;

@Operation(name = "PUSH")
public class Push implements Operations {
    @Override
    public Object run(Stack<Object> stack, List<Object> args) throws InvalidStackException {
        if (stack == null) {
            throw new InvalidStackException("Stack is undefined");
        }

        for (final Object obj: args) {
            stack.push(obj);
        }

        return null;
    }
}
