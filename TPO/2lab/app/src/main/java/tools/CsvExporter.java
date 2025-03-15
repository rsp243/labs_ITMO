package tools;

import java.io.FileWriter;
import java.io.IOException;

import com.google.common.base.Function;

public class CsvExporter {
    public static void export(Function<Double, Double> func, double start, double end, double step, String filePath, String delimiter) {
        try (FileWriter writer = new FileWriter("src/test/resources/" + filePath)) {
            writer.write("X" + delimiter + "Result of module '" + func.getClass() + "'\n");

            for (double x = start; x <= end; x += step) {
                double result = func.apply(x);
                writer.write(x + delimiter + result + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }
}
