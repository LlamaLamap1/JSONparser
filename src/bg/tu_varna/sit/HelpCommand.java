package bg.tu_varna.sit;

public class HelpCommand implements Command{
    @Override
    public void execute() {
        System.out.println("The following commands are supported: ");
        System.out.println("  help          prints this information ");
        System.out.println("  exit          exists the program ");
    }
}
