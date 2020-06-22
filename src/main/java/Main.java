import Control.*;
import ServSoft.ServerController;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        TableManager prodTable = new TableManager("products");
        TableController.setCurrentTable(prodTable);
        try {
            if( new File("saved.csv").createNewFile()){
                System.out.println("Save file created.");
            }
        }
        catch (Exception e ){
            System.out.println("Could not create default save file, please specify it manually\n");
        }
        if(args.length != 0) {
            //check extension
            Initializer.init(prodTable, new File(args[0]));
        }
        else {
            try {
                Initializer.init(prodTable, new File("saved.csv").exists() ? new File("saved.csv") : null);
            } catch (NullPointerException e) {
                Initializer.init(prodTable, null);
            }
        }

        ServerController.start();
        CommandController cmd = new CommandController();
        System.out.println("Enter Command \n" +
                "or Help to display a list of commands:");
        cmd.start(new CommandInterpreter());
        cmd.stop();
    }
}
