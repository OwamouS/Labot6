package ServSoft;

import cmd.Command;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {

    String[] args;
    Command command;

    public Request(Command cmd, String[] args){
        this.args = args;
        this.command = cmd;
    }

    @Override
    public String toString() {
        return Arrays.toString(args) + command.toString();
    }
}
