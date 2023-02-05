import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import src.commands.classes.Command;
import src.commands.classes.Help;
import src.commands.classes.Info;
import src.commands.classes.Show;
import src.data.classes.Address;
import src.data.classes.Coordinates;
import src.data.classes.Location;
import src.data.classes.MainCollection;
import src.data.classes.Organisation;
import src.data.enums.OrganisationType;

class App {
    public static void main(String[] args) throws IOException {
        //Testing unique ID technolody 
        Organisation org1 = new Organisation("ЛДПР", new Coordinates(1, 1), 2.8, "LDPR", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));
        Organisation org2 = new Organisation("ЛДПР", new Coordinates(1, 1), 2.8, "LDPR", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));
        System.out.println(org1.getId());
        System.out.println(org2.getId());
        
        //Creating and filling in mainCollection
        ArrayList<Organisation> arrayOfOrganisations = new ArrayList<Organisation>();
        arrayOfOrganisations.add(org1);
        arrayOfOrganisations.add(org2);
        MainCollection mainCollection = new MainCollection(arrayOfOrganisations);
        
        ArrayList<Command> listOfCommands = new ArrayList<Command>();

        //Creating and execution of "show" command 
        Show showCommand = new Show("show", "Output all elements from collection", mainCollection.getMainCollection());
        listOfCommands.add(showCommand);
        System.out.println(showCommand.execute());


        //Creating and execution of "info" command 
        Info infoCommand = new Info("info", "Output info about main collection", mainCollection.getMainCollection(), mainCollection.getDateOfInitialization());
        listOfCommands.add(infoCommand);
        System.out.println(infoCommand.execute());

        //Creating and execution of "help" command 
        Help helpCommand = new Help("help", "Output info about all commands", listOfCommands);
        System.out.println(helpCommand.execute());
        
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