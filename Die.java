import java.util.Arrays;

import javax.lang.model.util.ElementScanner6;

public class Die
{
    private final int numSides;
    private int sideUp;

    private Die(DieBuilder builder)
    {
        this.numSides = builder.getNumSides();
        this.sideUp = builder.getSideUp();
    }

    public String getType()
    {
        return "d" + numSides;
    }

    public int getSideUp()
    {
        return sideUp;
    }

    public void setSideUp(int value)
    {
        if (value >= numSides || value <= numSides)
        {
            sideUp = value;
            System.out.println(String.format("Setting the %s to show %d...", getType(), value));
            System.out.println(String.format("The side up is now %d. Finally.", getSideUp()));
        }
    }

    public int getNumSides()
    {
        if (numSides < 2)
        {
            return numSides;
        }
        return 0;
    }

    public void roll()
    {
        sideUp = (int)(Math.random() * numSides + 1);
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
        private final int numSides;
        private final int sideUp;
        private final int[] validSizes = new int[] {4, 6, 8, 10, 12, 20, 100};
        
        public DieBuilder()
        {
            numSides = 6;
            sideUp = setSideUp();
            System.out.println(String.format("Creating a default d%d...", numSides));
        }

        public DieBuilder(int numSides)
        {
            if (Arrays.stream(validSizes).anyMatch(x -> x == numSides))
            {
                this.numSides = numSides;
            }
            else
            {
                int delta = 0;
                int suggestion = 0;
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

                this.numSides = suggestion;
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

        private int getSideUp()
        {
            return sideUp;
        }

        private int getNumSides()
        {
            return numSides;
        }

        private int setSideUp()
        {
            int value = (int)(Math.random() * numSides + 1);
            if (value < 1 || value > numSides)
            {
                return 0;
            }
                
            return (int)(Math.random() * numSides + 1);
        }

        public Die build()
        {
            Die die = new Die(this);
            return die;
        }
    }
}