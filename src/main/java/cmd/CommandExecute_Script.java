package cmd;

import Control.ScriptParser;

import java.io.IOException;

/**
 * execute command from script
 *
 *
 */

public class CommandExecute_Script implements Command{

    @Override
    public void execute(String[] args) throws IOException {
        ScriptParser.parseScript(args[0]);
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "execute_script";
    }
}
