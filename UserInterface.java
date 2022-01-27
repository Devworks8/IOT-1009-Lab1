import java.util.Scanner;

public class UserInterface 
{
    public static void showDefaultMenuInterface()
    {
        Scanner uInput = new Scanner(System.in);
        int input = 0;

        do 
        {
            System.out.println("\nMain Menu");
            System.out.println("-------------------------");
            System.out.println("(1) Run Lab");
            System.out.println("(2) Role your own dice");
            System.out.println("(3) How many roles until you Yahtzee?");
            System.out.println("(0) Quit");
            System.out.println("-------------------------");
            System.out.print("Please enter a number: ");

            input = uInput.nextInt();

            switch (input)
            {
                case 1:
                    System.out.println("\n");
                    Program.RunLab();
                    break;
                case 2:
                    System.out.println("\n");
                    showCustomMenuInterface();
                    break;
                case 3:
                    System.out.println("\nNot Implemented yet...");
                    break;
                default:
                    break;
            }
        } while (input != 0);

        System.out.println("\nThank you for playing...");
        uInput.close();
        System.exit(1);
    }

    private static void showCustomMenuInterface()
    {

    }
}
