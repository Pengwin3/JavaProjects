import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by devin on 30/11/16.
 */

public class Odometer {

    public static void main(String[] args) {
        try {
            BufferedReader input = null;

            if (args.length == 0) {
                input = new BufferedReader(new InputStreamReader(System.in));
            } else {
                input = new BufferedReader (new FileReader(args[0]));
            }

            String reading = input.readLine();

            while (!reading.equals("0")) {
                int miles = Integer.parseInt(reading);
                int prefixLength = reading.length() - Integer.toString(miles).length();
                String prefix = "";

                // Create string of n zeros to prepend miles
                for (int j = 0; j < prefixLength; j++) {
                    prefix += "0";
                }

                String rdrome = new StringBuilder(prefix + Integer.toString(miles)).reverse().toString();

                while (!(prefix + miles).equals(rdrome)) {
                    miles += 1;
                    // Adjusts prefix length based on growing numbers
                    prefix = prefix.substring(prefix.length() - (reading.length() - Integer.toString(miles).length()));
                    rdrome = new StringBuilder(prefix + Integer.toString(miles)).reverse().toString();
                }
                System.out.println(miles - Integer.parseInt(reading));

                reading = input.readLine();
            }
        } catch (IOException e) {
            System.out.println("No Input/");
        }

    }

}
