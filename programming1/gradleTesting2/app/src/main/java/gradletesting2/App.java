/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package gradletesting2;

import com.opencsv.CSVParser;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public CSVParser getCsvParser() {
        return new CSVParser();
    }


    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        new App().getCsvParser();
    }
}