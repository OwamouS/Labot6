package cmd;
import Control.Initializer;
import Control.TableController;
import productdata.ReaderProductBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * get name of command
 *
 *
 */

public class CommandAdd implements Command{

    /**
     * insert product to hashtable
     *
     * @param args is key to new product
     */

    @Override
    public void execute(String[] args) {
        if (args == null) {
            String key;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                do {
                    System.out.println(" Enter product key: ");
                    key = reader.readLine();
                    if (key == null) System.out.println("Error: null key.");
                } while (key == null);
                TableController.getCurrentTable().put(key, ReaderProductBuilder.buildProduct(reader));
                System.out.println("Insertion complete...");
            } catch (Exception e) {
                System.out.println("Key is null, please try again with valid key...");
            }
        }
        else{
            Initializer.build(TableController.getCurrentTable(),args);
            System.out.println("Insertion complete...");
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "insert";
    }
}