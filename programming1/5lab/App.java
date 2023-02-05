import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class App {
    public static void main(String[] args) throws IOException {
        //Testing input from files and output into files
        List<String> lines = Files.readAllLines(Paths.get("README.md"), StandardCharsets.UTF_8);

        String name = "Reshetov Semjon";
        Integer age = 18;

        PrintWriter out = new PrintWriter(new FileOutputStream("me.txt"), true);
        out.print(name + " " + age);
        out.println(name + " == Cleaning");
        out.println(age + " == Cleaning");
        for (String line : lines) {
            out.println(line);
        }

        // Closing Streams & Readers
        out.close();
    }
}