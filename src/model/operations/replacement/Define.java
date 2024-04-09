package model.operations.replacement;

import model.operations.Operation;
import model.operations.exceptions.InvalidArgsException;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Operation(name = "DEFINE")
public class Define implements Replacement {
    HashMap<String, Double> _constants = new HashMap<>();

    @Override
    public Object run(Stack<Object> stack, List<Object> args) throws InvalidArgsException {
        if (args.size() != 2) {
            throw new InvalidArgsException("Args must contain at least two arguments");
        }

        if (args.getFirst() instanceof String constant && args.get(1) instanceof Double value) {
            _constants.put(constant, value);
        } else {
            throw new InvalidArgsException("First argument must be a constant string and second must be a type double value");
        }
        return null;
    }

    public HashMap<String, Double> constants() {
        return _constants;
    }
}
