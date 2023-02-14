import java.io.IOException;
import java.util.ArrayList;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;
import src.data.classes.LocalDatabase;

class App {
    public static void main(String[] args) throws IOException {
        LocalDatabase localDatabase = new LocalDatabase(new ArrayList<>());
        CollectionWorker dataWorker = new CollectionWorker(localDatabase); //Вероятнее всего, жестчайщая жесть. Так нельзя писать.
        CommandController commandController = new CommandController();
        String helpName = "help";
        String infoName = "info";
        ArrayList<String> extraCommandArguments = new ArrayList<>();
        System.out.println(commandController.execute(dataWorker, helpName, extraCommandArguments));
        System.out.println(commandController.execute(dataWorker, infoName, extraCommandArguments));



        /*//Testing unique ID technolody 
        Increment autoUniqueID = new Increment(1);
        Organisation org1 = new Organisation(autoUniqueID, "Группа людeй1", new Coordinates(1, 1), 2.8, "Group1", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));
        Organisation org2 = new Organisation(autoUniqueID, "Группа людeй2", new Coordinates(1, 1), 2.8, "Group2", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));
        System.out.println(org1.getId());
        System.out.println(org2.getId());
        
        //Creating and filling in mainCollection
        ArrayList<Organisation> arrayOfOrganisations = new ArrayList<Organisation>();
        arrayOfOrganisations.add(org1);
        arrayOfOrganisations.add(org2);
        MainCollection mainCollection = new MainCollection(arrayOfOrganisations);
        
        ArrayList<Command> listOfCommands = new ArrayList<Command>();

        //Creating and execution of "remove_key" command 
        Integer key1 = 2; 
        Remove_key removeCommand = new Remove_key("remove_key", "Remove an element with typed key from the main collection ", mainCollection, key1);
        listOfCommands.add(removeCommand);
        System.out.println(removeCommand.execute());

        //Creating and execution of "update" command 
        Organisation org3 = new Organisation(autoUniqueID, "Компьютер", new Coordinates(1, 1), 2.8, "", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));
        Integer key2 = 1; 
        Update updateCommand = new Update("update", "Update an element with typed key in the main collection ", mainCollection, org3, key2);
        listOfCommands.add(updateCommand);
        System.out.println(updateCommand.execute());

        //Creating and execution of "insert" command 
        Organisation org4 = new Organisation(autoUniqueID, "Земля", new Coordinates(1, 1), 2.8, "Earth", OrganisationType.GOVERNMENT, new Address("ул. Ленина", new Location(1.0, 1F, "Санкт-Петербург")));
        Integer key3 = 5; 
        Insert insertCommand = new Insert("insert", "Add an element with typed key into the main collection", mainCollection, org4, key3);
        listOfCommands.add(insertCommand);
        System.out.println(insertCommand.execute());

        //Creating and execution of "show" command 
        Show showCommand = new Show("show", "Output all elements from collection", mainCollection);
        listOfCommands.add(showCommand);
        System.out.println(showCommand.execute());


        //Creating and execution of "info" command 
        Info infoCommand = new Info("info", "Output info about main collection", mainCollection);
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
        out.close();*/
    }
}