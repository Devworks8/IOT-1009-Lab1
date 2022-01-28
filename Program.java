public class Program
{
    public static void main(String[] args)
    {
        UserInterface.showDefaultMenuInterface();
    }

    public static void RunLab()
    {
        // Create a d6, d20 and pecentile die
        Die d6 = new Die.DieBuilder(true).build();
        Die d20 = new Die.DieBuilder(20, true).build();
        Die d100 = new Die.DieBuilder(100, true).build();

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
        final Die[] dice = new Die[]{new Die.DieBuilder(false).build(), new Die.DieBuilder(false).build(), new Die.DieBuilder(false).build(), new Die.DieBuilder(false).build(), new Die.DieBuilder(false).build()};
        System.out.println("\nCreated 5d6...");

        boolean yahtzee = false;
        int count = 1;
        while (yahtzee == false)
        {
            for (Die die : dice)
            {
                die.roll();
            }

            if (dice[0].getSideUp() == dice[1].getSideUp() && dice[1].getSideUp() == dice[2].getSideUp() && dice[2].getSideUp() == dice[3].getSideUp() && dice[3].getSideUp() == dice[4].getSideUp())
            {
                yahtzee = true;
            }
            else
            {
                count += 1;
            }
        }

        System.out.println(String.format("YAHTZEE! It took %d rolls", count));
    }
}