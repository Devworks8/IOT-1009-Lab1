public class Program
{
    public static void main(String[] args)
    {
        UserInterface.showDefaultMenuInterface();
    }

    public static void RunLab()
    {
        // Create a d6, d20 and pecentile die
        Die d6 = new Die.DieBuilder().build();
        Die d20 = new Die.DieBuilder(20).build();
        Die d100 = new Die.DieBuilder(100).build();

        // Print the current side up
        System.out.println(d6.toString());
        System.out.println(d20.toString());
        System.out.println(d100.toString());

        System.out.println("\nTesting the roll method\n");

        // Roll all the dice
        d6.roll();
        d20.roll();
        d100.roll();

        System.out.print("\n");
        // Change the sideup on the d20
        d20.setSideUp(20);
    }

    public static void Yahtzee()
    {

    }
}