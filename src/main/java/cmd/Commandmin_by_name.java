package cmd;
import Control.TableController;
import Control.TableManager;
import productdata.Product;

import java.util.Iterator;
import java.util.Map;

/**
 * get element with the shortest name
 *
 *
 */

public class Commandmin_by_name implements Command {

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 1) {
                System.out.println("There is no args for this command!");
            }
        }catch (NullPointerException e) {
            String min = "";
            int i = 1;
            Product p = null;
            for (Product prod : TableController.getCurrentTable().getProducts()) {
                if (i == 1) {
                    min = prod.getName();
                }
                if (i == 0) {
                    if (min.compareTo(prod.getName()) <= 0) {
                        min = prod.getName();
                        p = prod;
                    }
                }
                i = 0;
            }
            if (p != null) {
                System.out.println(p.toString());
            } else {
                System.out.println("Empty table");
            }
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    public String toString() {
        return "min_by_name";
    }
}
