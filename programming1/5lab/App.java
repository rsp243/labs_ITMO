import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import src.commands.Command;
import src.commands.Help;
import src.data.classes.Address;
import src.data.classes.Coordinates;
import src.data.classes.Location;
import src.data.classes.Organisation;
import src.data.enums.OrganisationType;
import src.fillers.Increment;

class App {
    public static void main(String[] args) throws IOException {
        //Execution of "help" command 
        ArrayList<Command> listOfCommands = new ArrayList<Command>();
        Help helpCommand = new Help("help", "Output info about all commands", listOfCommands);
        System.out.println(helpCommand.execute());
        
        //Testing unique ID technolody 
        Increment startInc = new Increment(1);
        Organisation org1 = new Organisation(startInc, "ЛДПР", new Coordinates(1, 1), 2.8, "LDPR", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));
        Organisation org2 = new Organisation(startInc, "ЛДПР", new Coordinates(1, 1), 2.8, "LDPR", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));

        System.out.println(org1.getId());
        System.out.println(org2.getId());

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