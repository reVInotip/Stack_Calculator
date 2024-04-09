package model.operations.comment;

import model.operations.Operation;
import model.operations.Operations;
import java.util.List;
import java.util.Stack;

@Operation(name = "#")
public class Comment implements Operations {
    @Override
    public Object run(Stack<Object> stack, List<Object> args) {
        // now do nothing, but int can parse meta information
        return null;
    }
}
