import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by devin on 22/11/16.
 */
public class televisionRatings {
    private File file;

    televisionRatings(String fileName) { file = new File(fileName); }

    public static void main (String[] args) {
        if (args.length !=1) {
            System.err.println("You must supply a file name as a command argument.");
            return;
        }


    }


    void importFile() {
        try {
            FileReader freader = new FileReader(file);
            Scanner in = new Scanner(freader);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                lineProcessor(line);
            }
            in.close(); // also closes freader
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file " + file);
            System.exit(1);
        }
    }


    private void lineProcessor(String line) {
        try {
            Scanner scan;
            scan = new Scanner(line);
            scan.useDelimiter(":");

            String title = scan.next();

//            String title = line.substring(0, Math.min(line.length(), 24));
//            System.out.print(title + " ");

            scan.useDelimiter(" ");
            String colon = scan.next();

//            scan.useDelimiter(" ");
            int total = scan.nextInt();
//            System.out.print(total);

//            scan.useDelimiter(" ");
            double rating = scan.nextDouble();
//            System.out.print(rating);
            Average(title, total, rating);
//            System.out.print(total * rating);
//            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void Average(String title, int total, double rating) {
        System.out.print(title + " " + total*rating);

        System.out.println();
    }
}








    /*private void importFile() {
        try {
            FileReader freader = new FileReader(file);
            System.out.println("Reading from: " + file);
            Scanner in = new Scanner (freader);
            in.useDelimiter(":");
            while(in.hasNextLine()) {
                String line = in.nextLine();
                while (in.hasNext()) {
                    String rating = line.substring(0, Math.min(line.length(), 24));
                    in.useDelimiter(" ");
                    System.out.println(rating);
                }
            }
            in.close(); // also closes freader
        } catch (FileNotFoundException e) {
            System.err.println ("Could not find file " + file);
            System.exit(1);
        }
    }*/


