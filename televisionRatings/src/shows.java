import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by devin on 28/11/16.
 */



public class shows {
    private File file;
    private String name;
    private int rating;
    private double stars;
    private double total;
    public int n;

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    public shows(String name, int rating, double stars) {
        this.name = name;
        this.rating = rating;
        this.stars = stars;
    }
    private shows(String fileName) { file = new File(fileName); }
    /*
    void importFile(String[] args) {
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
    }*/

    private void lineProcessor(String line) {
        try {
            Scanner scan;
            scan = new Scanner(line);
            scan.useDelimiter(":");
            String name = scan.next();
            this.name = line.substring(0, Math.min(name.length(), 24));

            scan.useDelimiter(" ");
            String colon = scan.next();
            this.rating = scan.nextInt();
            this.stars = scan.nextDouble();

            shows Rate = new shows(name, rating, stars);

                total += this.rating * this.stars;

            System.out.print(padRight(getName(), 24) + " " + getRating() + " " + getStars() + " : " + total);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }
    public double getStars() {
        return stars;
    }

    public double addToRating() {
        HashMap<shows, name>
        rating += rating*stars;
    return rating;
    }

    public double getRating() {return rating;}

    public double addToStars(double starsToAdd) { starsToAdd += stars;
    return starsToAdd;}

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

    public static void main(String[] args) {

        if (args.length !=1) {
            System.err.println("You must supply a file name as a command argument.");
        }
            try {
                String fileName = args[0];
                new shows(fileName).importFile();
            } catch (NullPointerException e) {
                System.err.println("Null Pointer " + args[0]);
                System.exit(1);
            }

            return;
        }
}
