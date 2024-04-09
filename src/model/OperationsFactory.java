package model;

import model.exceptions.NotFoundOperationsException;
import model.operations.Operations;
import model.operations.Operation;
import model.utils.OperationsScanner;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class OperationsFactory {
    final private HashMap<String, Class<Operations>> _operationClasses;
    final private HashMap<String, Operations> _operations;

    public OperationsFactory() throws NotFoundOperationsException {
        _operations = new HashMap<>();
        _operationClasses = new HashMap<>();
        List<Class<Operations>> operations = OperationsScanner.findAllOperations();
        if (operations == null || operations.isEmpty()) {
            throw new NotFoundOperationsException("Not found operations");
        }

        for (final Class<Operations> operation: operations) {
            _operationClasses.put(operation.getAnnotation(Operation.class).name(), operation);
        }
    }

    public Set<String> operations() {
        return _operationClasses.keySet();
    }

    public Operations of(String opName) throws ReflectiveOperationException {
        final Class<Operations> operation = _operationClasses.get(opName);
        if (_operations.get(opName) == null) {
            _operations.put(opName, operation == null ? null : operation.getConstructor().newInstance());
        }
        return _operations.get(opName);
    }
}
