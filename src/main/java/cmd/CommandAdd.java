package cmd;
import Control.Initializer;
import Control.TableController;
import productdata.Product;
import productdata.ReaderProductBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * get name of command
 *
 *
 */

public class CommandAdd implements Command, Preparable{



    Product product = null;
    String key = null;
    private static final long serialVersionUID = 1337000000L;

    /**
     * insert product to hashtable
     *
     * @param args is key to new product
     */
    @Override
    public String execute(String[] args) {
        if (product == null || key == null){
            prepare(args);
            execute(args);
        }
        else {
            TableController.getCurrentTable().put(key, product);
            return ("Insertion complete...");
        }
        return null;
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

    @Override
    public void prepare(String[] args) {
        if (args == null) {
            String key;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                do {
                    System.out.println(" Enter product key: ");
                    key = reader.readLine();
                    if (key == null) System.out.println("Error: null key.");
                } while (key == null);
                this.key = key;
                this.product = ReaderProductBuilder.buildProduct(reader);
            } catch (Exception e) {
                System.out.println("Key is null, please try again with valid key...");
            }
        }
        else{
            this.product = Initializer.build(args);
            this.key = args[0];
        }
    }
}