package cmd;

/**
 * break the programm
 *
 *
 */

public class CommandExit implements Command {

    @Override
    public void execute(String[] args){
        try {
            if (args.length == 1) {
                System.out.println("There is no args for this command!");
            }
        }catch (NullPointerException e) {
            System.out.println("Program completion...");
            System.exit(0);
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "exit";
    }
}
