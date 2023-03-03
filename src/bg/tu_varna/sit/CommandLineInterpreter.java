package bg.tu_varna.sit;

import java.util.Scanner;

public class CommandLineInterpreter {
    private Command helpCommand;

    public CommandLineInterpreter(Command helpCommand){
        this.helpCommand=helpCommand;
    }
    String command;
    public void processCommand(){
        Scanner sc=new Scanner(System.in);

        do {
            System.out.print("Enter command:\n>");
            command=sc.next();
            switch(command){
                case "help":
                    helpCommand.execute();
                    break;

                case "exit":
                    System.out.println("Exiting the program... ");
                    break;

                default:
                    System.out.println("Unknown command ");
                    break;
            }


        }while (!command.equals("exit"));
    }
}
