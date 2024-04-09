package model.utils;

import model.operations.Operation;
import model.operations.Operations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperationsScanner {
    private static final Logger _logger = LogManager.getRootLogger();
    private static List<Class<Operations>> scanFolder(File folder, File baseFolder) throws ClassNotFoundException {
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        ArrayList<Class<Operations>> result = new ArrayList<>();

        for (final File file: files) {
            if (file.isDirectory()) {
                result.addAll(scanFolder(file, baseFolder));
            } else {
                final String fileName = file.getAbsolutePath().substring(baseFolder.getAbsolutePath().length() + 1);
                int pos = fileName.toLowerCase().indexOf(".class");
                if (pos > 0) {
                    Class<?> classFile = Class.forName(fileName.replace(File.separator, ".").substring(0, pos));
                    if (!classFile.isAnnotationPresent(Operation.class)) {
                        continue;
                    }

                    //noinspection unchecked
                    result.add((Class<Operations>) classFile);
                }
            }
        }

        return result;
    }

    private static List<Class<Operations>> findAllOperations(final String element) {
        ArrayList<Class<Operations>> result = new ArrayList<>();
        final File file = new File(element);
        if (file.isDirectory()) {
            try {
                result.addAll(scanFolder(file, file));
            } catch (ClassNotFoundException exception) {
                final String message = "Class " + element + " not found";
                _logger.warn(message);
            }
        } else {
            // add scanner for jar files
        }

        return result;
    }

    public static List<Class<Operations>> findAllOperations() {
        final String classPath = System.getProperty("java.class.path", ".");
        final String[] classPathElements = classPath.split(File.pathSeparator);
        return Arrays.stream(classPathElements).map(OperationsScanner::findAllOperations).flatMap(List::stream).collect(Collectors.toList());
    }
}
