package model.operations.print;

import model.operations.Operation;
import model.operations.Operations;
import model.operations.exceptions.InvalidArgsException;
import model.operations.exceptions.InvalidStackException;
import view.Printer;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

@Operation(name = "PRINT")
public class Print implements Operations {
    @Override
    public Object run(Stack<Object> stack, List<Object> args) throws InvalidStackException, InvalidArgsException {
        if (stack == null) {
            throw new InvalidStackException("Stack is undefined");
        } else if (stack.isEmpty()) {
            throw new InvalidStackException("Nothing to print");
        }

        switch (args.size()) {
            case 0:
                Printer.print(stack.getLast());
                break;
            case 1:
                final String fileName = args.getFirst() instanceof String ? null : (String) args.getFirst();
                if (fileName == null) {
                    throw new InvalidArgsException("First argument must be a file name string");
                }

                try {
                    Printer.print(fileName, stack.getLast());
                } catch (IOException exception) {
                    throw new InvalidArgsException("File not found");
                }

            default:
                return null;
        }
        return null;
    }
}
