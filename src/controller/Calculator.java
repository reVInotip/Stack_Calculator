package controller;

import model.OperationsFactory;
import model.exceptions.NotFoundOperationsException;
import model.operations.Operation;
import model.operations.Operations;
import model.operations.exceptions.OperationException;
import model.operations.replacement.Replacement;

import java.io.*;
import java.util.*;

public class Calculator {
    final private BufferedReader _inputStream;
    public static String _separator = " ";

    final OperationsFactory _factory;
    final Set<String> _availableOperations;
    Stack<Object> _stack;

    public Calculator(String fileName) throws NotFoundOperationsException, FileNotFoundException {
        _inputStream = new BufferedReader(new FileReader(fileName));
        _factory = new OperationsFactory();
        _availableOperations = _factory.operations();
        _stack = new Stack<>();
    }

    private List<Object> parseNextCommand() throws IOException {
        String currLine;
        if ((currLine = _inputStream.readLine()) == null) {
            return null;
        }

        List<Object> result = new ArrayList<>();
        for (final String arg: currLine.split(_separator)) {
            try {
                result.add(Double.parseDouble(arg));
            } catch (NumberFormatException exception) {
                result.add(arg);
            }
        }

        return result;
    }

    public Object execute() throws OperationException, ReflectiveOperationException, NotFoundOperationsException, IOException {
        List<Object> commandWithArgs;
        Object result = null;
        while ((commandWithArgs = parseNextCommand()) != null) {
            Operations op = _factory.of((String) commandWithArgs.getFirst());
            if (op == null) {
                throw new NotFoundOperationsException("Not found operation " + commandWithArgs.getFirst());
            } else if (op.getClass().getAnnotation(Operation.class).type().equals("arithmetic")) {
                String opName = commandWithArgs.size() > 1 ? (String) commandWithArgs.get(1) : "DEFINE";
                Replacement define = (Replacement) _factory.of(opName);
                result = op.run(_stack, List.of(define.constants()));
            } else {
                result = op.run(_stack, commandWithArgs.subList(1, commandWithArgs.size()));
            }
        }

        return result;
    }
}
