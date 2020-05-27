package Control;

import Control.cmdLists.StdCommandList;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that parse the script files
 */
public class ScriptParser {
    /**
     * Creates a ArrayList of commands from script file and throws commands to interpreter
     * @param file Script file
     * @throws IOException If an I/O error occurs
     */
    public static void parseScript(String file) throws IOException {
        try {
            if (!new File(file).canRead()) throw new IllegalAccessException();
            FileReader reader = new FileReader(new File(file));
            BufferedReader buffReader = new BufferedReader(reader);
            String line;
            ArrayList<String[]> commands = new ArrayList<>();
            CommandInterpreter itr = new CommandInterpreter();
            int i = 0;

            while ((line = buffReader.readLine()) != null) {
                i++;
                String[] cmd = line.split(" ");
                if (StdCommandList.getCommand(cmd[0]) == null) {
                    System.out.println("Script contains problem at string " + i + ".");
                    return;
                }
                if (line.equals("execute_script " + file)) {
                    System.out.println("Recursion detected. If you want to continue enter \"yes\" (" +
                            "AT THE NEXT ITERATION THIS QUESTION WILL BE ASKED AGAIN):");
                    try {
                        String ans = new BufferedReader(new InputStreamReader(System.in)).readLine();
                        if (!"yes".equals(ans)) {
                            return;
                        }
                    } catch (IOException e) {
                        System.out.println("Invalid character sequence...");
                        return;
                    }
                }
                for (int k = 0; k <= cmd.length - 1; k++) {
                    if (";".equals(cmd[k])) {
                        cmd[k] = "";
                    }

                }
                commands.add(cmd);
            }
            for (String[] command : commands) {
                itr.handle(command);
                System.out.println("\n");
            }
            buffReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
        }
        catch (IllegalAccessException e){
            System.out.println("Cannot access the file, please get access rights...");
        }
    }
}
