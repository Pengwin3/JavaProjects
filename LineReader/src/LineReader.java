import java.io.*;
import java.util.Scanner;

/**
 * Created by devin on 21/11/16.
 */
public class LineReader {
    private File file;


    private LineReader(String fileName) {
        file = new File(fileName);
    }



    public static void main (String[] args) {
        if (args.length != 1) {
            System.err.println ("You must supply a file name (path) as a command argument.");
            return;
        }

        String fileName = args[0];
        new LineReader(fileName).listFile();

    }


    /**
     * Open the file (supplied in the constructor) and print
     * one or more lines from it.
     */
    private void listFile() {
        try {
            FileReader freader = new FileReader(file);
            System.out.println("Reading from: " + file);
            Scanner in = new Scanner (freader);
            while(in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
            in.close(); // also closes freader
        } catch (FileNotFoundException e) {
            System.err.println ("Could not find file " + file);
            System.exit(1);
        }
    }
}
