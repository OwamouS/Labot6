package cmd;
import Control.TableController;
import Control.TableManager;

import java.util.Hashtable;

/**
 * show all elements in String format
 *
 *
 */

public class CommandShow implements Command {

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 1) {
                System.out.println("There is no args for this command!");
            }
        }catch (NullPointerException e) {
            if (TableController.getCurrentTable().getSize() == 0) {
                System.out.println("Collection is empty!");
            } else {
                TableController.getCurrentTable().show();
            }
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    public String toString(){
        return "show";
    }
}
