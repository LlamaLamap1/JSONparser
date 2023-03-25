package bg.tu_varna.sit;

import bg.tu_varna.sit.commands.CommandLineInterpreter;

public class Application {

    public static void main(String[] args) {
        CommandLineInterpreter cli = CommandLineInterpreter.getInstance();
        cli.run();
    }
}