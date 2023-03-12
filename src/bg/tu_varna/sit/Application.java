package bg.tu_varna.sit;

public class Application {

    public static void main(String[] args) {
        CommandLineInterpreter cli = CommandLineInterpreter.getInstance();
        cli.run();
    }
}