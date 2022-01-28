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
                    uInput.close();
                    break;
                case 3:
                    Program.Yahtzee();
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
        Scanner uInput = new Scanner(System.in);
        int input = 0;
        String customDice;

        do 
        {
            System.out.println("\nCustom Menu");
            System.out.println("-------------------------");
            System.out.println("(1) Create Dice");
            System.out.println("(2) Reset Dice");
            System.out.println("(3) Role Dice");
            System.out.println("(4) Return to Main Menu");
            System.out.println("(0) Quit");
            System.out.println("-------------------------");
            System.out.print("Please enter a number: ");

            input = uInput.nextInt();

            switch (input)
            {
                case 1:
                    System.out.println("\nSyntax");
                    System.out.println("---------------------------------------------");
                    System.out.println("Create one d8 die -> 1d8");
                    System.out.println("Create three d20 dice - > 3d20");
                    System.out.println("Create one d4 and five d8 dice -> 1d4;5d8");
                    System.out.println("---------------------------------------------");
                    System.out.print("How many die do you want? ");
                    customDice = uInput.next();
                    new Die.DieBuilder(customDice, true).build();
                    break;
                case 2:
                    Die.DieBuilder.resetDice();
                    break;
                case 3:
                    System.out.println("\n");
                    Die.DieBuilder.rollAll();
                    break;
                case 4:
                    showDefaultMenuInterface();
                    uInput.close();
                    break;
                default:
                    break;
            }
        } while (input != 0);

        System.out.println("\nThank you for playing...");
        uInput.close();
        System.exit(1);
    }
}
