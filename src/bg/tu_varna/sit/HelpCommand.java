package bg.tu_varna.sit;

public class HelpCommand implements Command{
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Unknown command");
        }
        else{
            System.out.println("The following commands are supported: ");
            System.out.println("  help          prints this information ");
            System.out.println("  exit          exists the program ");
        }
    }
}
