package bg.tu_varna.sit.commands;

public class HelpCommand implements Command{
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Unknown command");
        }
        else{
            System.out.println("The following commands are supported: ");
            System.out.println("  open <file>               opens <file>");
            System.out.println("  close                     closes the opened file");
            System.out.println("  validate                  checks if the content fallows JSON structure");
            System.out.println("  print                     displays the contents of the file");
            System.out.println("  search <key>              searches and displays the values of every found <key>");
            System.out.println("  set <path> <string>       set the element at <path> with the contents of the JSON <string>");
            System.out.println("  create <path> <string>    creates the element at <path> with the contents of the JSON <string>");
            System.out.println("  delete <path>             deletes the element at <path>");
            System.out.println("  move <from> <to>          moves element from path <from> to path <to> the <to> path needs to be one level lower higher");
            System.out.println("                            than <from> path so it can be moved in the correct position");
            System.out.println("  save [<path>]             saves the <path> specified in the opened file if <path> is not specified it saves the whole structure");
            System.out.println("  saveas <file> [<path>]    saves the <path> specified as <file> if <path> is not specified it saves the whole structure");
            System.out.println("  help                      prints this information ");
            System.out.println("  exit                      exists the program ");

        }
    }
}
