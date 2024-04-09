package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Printer {
    public static void print(final Object data) {
        System.out.println(data);
    };

    public static void print(final String fileName, Object data) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(String.valueOf(data));
    }
}
