package cmd;
import Control.TableController;
import Control.TableManager;

import java.io.FileNotFoundException;
import java.util.Set;

/**
 * removes element with given key
 *
 *
 */

public class CommandRemove implements Command {

    @Override
    public void execute(String[] args) {
        int count = 0;
        for(String key : TableController.getCurrentTable().getKey()){
            if(key.equals(args[0])){
                count++;
            }
        }
        if(count==0){
            System.out.println("No such key\nAvailable keys: " + TableController.getCurrentTable().getKey());
        }else{
            TableController.getCurrentTable().remove(args[0]);
            System.out.println("Element has been removed.");
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    public String toString(){
        return "remove_key";
    }
}
