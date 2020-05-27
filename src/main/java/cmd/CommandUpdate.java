package cmd;

import Control.TableController;
import productdata.Product;
import productdata.ReaderProductBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Update whole element with given id
 *
 *
 */

public class CommandUpdate implements Command {

    /**
     * Iterates through all elements of collection and update element with given id
     *
     *
     */

    @Override
    public void execute(String[] args) {
        try {
            if (args[0] == null) {
                System.out.println("Please enter ID");
            }
            int counter = 0;
            Iterator<Map.Entry<String, Product>> it = TableController.getCurrentTable().getSet().iterator();
            int i = Integer.parseInt(args[0]);
            while (it.hasNext()) {
                Map.Entry<String, Product> map = it.next();
                if (map.getValue().getId() == i) {
                    counter++;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    TableController.getCurrentTable().replace(map.getKey(), ReaderProductBuilder.buildProduct(reader));
                }
            }
            if (counter == 0) {
                System.out.println("There is no elements with that id.");
            }
        }catch(NumberFormatException e){
            System.out.println("Argument must be a number");
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "update_id";
    }
}
