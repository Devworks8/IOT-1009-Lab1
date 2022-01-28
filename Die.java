import java.util.*;
import javax.lang.model.util.ElementScanner6;

public class Die
{
    private final int numSides;
    private int sideUp;
    private boolean showRoll;

    private Die(DieBuilder builder)
    {
        this.numSides = builder.getNumSides();
        this.sideUp = builder.getSideUp();
        this.showRoll = builder.showRoll;
    }

    // Get the die type ie: d20
    public String getType()
    {
        return "d" + numSides;
    }

    // Get the current side up
    public int getSideUp()
    {
        return sideUp;
    }

    // Set the current side up
    public void setSideUp(int value)
    {
        // Check if the value provided is within the range of the current die, if not leave it as is
        if (value >= numSides || value <= numSides)
        {
            sideUp = value;
            System.out.println(String.format("Setting the %s to show %d...", getType(), value));
            System.out.println(String.format("The side up is now %d. Finally.", getSideUp()));
        }
    }

    // Get the total number of side of the die
    public int getNumSides()
    {
        if (numSides < 2)
        {
            return numSides;
        }
        return 0;
    }

    // Roll the die
    public void roll()
    {
        sideUp = (int)(Math.random() * numSides + 1);
        if (showRoll)
        {
            rollMessage(numSides);
        }
    }
 
    // Display roll message
    private void rollMessage(int numSides)
    {
        if (numSides != 100)
        {
            System.out.println(String.format("Rolling the %s...", getType()));
        }
        else
        {
            System.out.println("Rolling the Percentile...");
        }

        if (numSides == 20)
        {
            if (sideUp == 20)
            {
                System.out.println("Nat 20! Critical success!");
                
            }
            else if (sideUp == 1)
            {
                System.out.println("Nat 1! Critical Fail!");
            }
            else
            {
                System.out.println(String.format("The new value is %d", getSideUp()));
            }
        }
        else
        {
            System.out.println(String.format("The new value is %d", getSideUp()));
        }
    }

    @Override
    public String toString()
    {
        if (numSides != 100)
        {
            return String.format("The current side up for %s is %d", getType(), getSideUp());
        }
        else
        {
            return String.format("The current side up for Percentile is %d", getSideUp());
        }
    }

    // Die Builder code
    public static class DieBuilder
    {
        private int numSides;
        private int sideUp;
        private boolean showRoll;
        private final int[] validSizes = new int[] {4, 6, 8, 10, 12, 20, 100};
        private static List<Die> customDice = new ArrayList<Die>();
        
        public DieBuilder(boolean showRoll)
        {
            numSides = 6;
            sideUp = setSideUp();
            this.showRoll = showRoll;
            System.out.println(String.format("Creating a default d%d...", numSides));
        }

        public DieBuilder(int numSides, boolean showRoll)
        {
            this.showRoll = showRoll;
            displayBuildMessage(numSides);
        }

        public DieBuilder(String customDice, boolean showRoll)
        {
            this.showRoll = showRoll;
            String[] dice = customDice.split(";");

            for (String die : dice)
            {
                String[] dieSet = die.split("d");
                for (int i=0; i < Integer.parseInt(dieSet[0]); ++i)
                {
                    displayBuildMessage(Integer.parseInt(dieSet[1]));
                    this.customDice.add(build());
                }
            }
        }

        // Get custom dice list
        public static List<Die> getCustomDice()
        {
            return customDice;
        }
        // Get the current side up
        private int getSideUp()
        {
            return sideUp;
        }

        // Get the total number of sides
        private int getNumSides()
        {
            return numSides;
        }

        // Set the current side up
        private int setSideUp()
        {
            return (int)(Math.random() * numSides + 1);
        }

        // Build and return a new die object
        public Die build()
        {
            Die die = new Die(this);
            return die;
        }

        // Find closest dice matching what was requested
        private int findClosestSides(int numSides)
        {
            int delta = 0;
            int suggestion = 0;
            // If the numSides requested isn't valid, find and provide the first die closest to numSides
            for (int size : validSizes)
            {
                if (delta == 0)
                {
                    delta = Math.abs(numSides - size);
                    suggestion = size;
                }
                else
                {
                    if (Math.abs(numSides - size) < delta)
                    {
                        delta = Math.abs(numSides - size);
                        suggestion = size;
                    }
                }
            }

            return suggestion;
        }
    
        // Display dice build message
        private void displayBuildMessage(int numSides)
        {
            if (Arrays.stream(validSizes).anyMatch(x -> x == numSides))
            {
                this.numSides = numSides;
            }
            else
            {
                this.numSides = findClosestSides(numSides);
            }

            sideUp = setSideUp();
            
            if (numSides == 100)
            {
                System.out.println("Creating percentile die (a special d10)...");
            }
            else
            {
                System.out.println(String.format("Creating a d%d...", this.numSides));
            }
        }
    
        // Roll all custom dice
        public static void rollAll()
        {
            for (Die die : getCustomDice())
            {
                die.roll();
            }
        }

        // Reset the custom dice sset
        public static void resetDice()
        {
            customDice.clear();
            System.out.println("Dice reset...");
        }
    }
}