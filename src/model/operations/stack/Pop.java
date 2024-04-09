package model.operations.stack;

import model.operations.Operation;
import model.operations.Operations;
import model.operations.exceptions.InvalidStackException;

import java.util.List;
import java.util.Stack;

@Operation(name = "POP")
public class Pop implements Operations {
    @Override
    public Object run(Stack<Object> stack, List<Object> args) throws InvalidStackException {
        if (stack == null || stack.isEmpty()) {
            throw new InvalidStackException("Stack is undefined");
        }

        return stack.pop();
    }
}
