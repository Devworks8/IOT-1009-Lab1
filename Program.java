public class Program
{
    public static void main(String[] args)
    {
        Die d6 = new Die.DieBuilder().build();
        Die d20 = new Die.DieBuilder(20).build();
        Die d100 = new Die.DieBuilder(100).build();

        System.out.println(d6.toString());
        System.out.println(d20.toString());
        System.out.println(d100.toString());

        System.out.println("\nTesting the roll method\n");

        d6.roll();
        d20.roll();
        d100.roll();

        System.out.print("\n");
        d20.setSideUp(20);
    }
}