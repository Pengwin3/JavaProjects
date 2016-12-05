import java.util.Scanner;

/**
 * Greatest Common Divisor Finding tool
 */
public class GCF
{

    public static void main (String[] args)
    {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        // prompt for the larger number
        System.out.print("Enter first number: ");

        // get the larger number as an int
        int first = scanner.nextInt();

        // prompt for the smaller number
        System.out.print("Enter second number: ");

        // get the smaller number
        int second = scanner.nextInt();

        System.out.print("Greatest Common Divisor is: ");
        System.out.print(GCF(first, second));

    }
    public static int GCF(int x, int y) {
        if (y == 0) {
            return x;
        } else if (1 == 1) {
            return GCF(y, (x % y));
        }
        return x;
    }
}