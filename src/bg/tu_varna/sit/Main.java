package bg.tu_varna.sit;

public class Main {

    public static void main(String[] args) {
	CommandLineInterpreter commandLineInterpreter=new CommandLineInterpreter(new HelpCommand());

    commandLineInterpreter.processCommand();
    }
}
