import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ratings {

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public void processSurvey(BufferedReader in) throws IOException {
        String[] names = new String[50];
        int[] viewers = new int[50];
        double[] ratingsSum = new double[50];

        int nShows = readSurvey (in, names, viewers, ratingsSum);
        printReport (nShows, names, viewers, ratingsSum);
    }

    private int readSurvey(BufferedReader in, String[] names, int[] viewers, double[] ratingsSum) throws IOException {
        int i = 0;
        try {
            while (in.ready()) {
                StringBuilder sb = new StringBuilder(String.valueOf(in.readLine()));
                names[i] = sb.substring(0, sb.indexOf(":"));
                sb.delete(0 , sb.indexOf(":") + 2);
                viewers[i] = Integer.parseInt(sb.substring(0 , sb.indexOf(" ")));
                sb.delete(0, sb.indexOf(" ") + 1);
                ratingsSum[i] = Double.parseDouble(sb.toString());
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    private void printReport(int nShows, String[] names, int[] viewers, double[] ratingsSum) {
        double[] numerator = new double[50];
        numerator[0] = ratingsSum[0] * viewers[0];
        int[] denominator = new int[50];
        denominator[0] = viewers[0];
        try {
            for (int i = 0; i < nShows; i++) {
                for (int j = 1; j < nShows; j++) {
                    if (names[j].equals(names[i])) {
                        denominator[i] += viewers[j];
                        numerator[i] += ratingsSum[j] * viewers[j];
                    }
                }
            }
            int j = 0;
            int i = 0;
            System.out.println(padRight(names[j].substring(0, Math.min(names[j].length(), 24)), 24) +
                    " " +
                    padLeft(Integer.toString(denominator[j]), 4) +
                    " " +
                    round(numerator[j] / denominator[j], 1));
            boolean exists;
            while ( j < nShows-1) {
                j++;
                i=0;
                exists = false;
                for(; i < j; i++) {
                    if (names[i].equals(names[j])) {exists = true;}
                }
                if (!exists) {
                    System.out.println(padRight(names[j].substring(0, Math.min(names[j].length(), 24)), 24) +
                            " " +
                            padLeft(Integer.toString(denominator[j]), 4) +
                            " " +
                            round(numerator[j] / denominator[j], 1));
                }
            }
        } catch (Exception NullPointerException) {
            NullPointerException.printStackTrace();}
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println ("Usage: java Ratings inputFileName");
            System.exit(1);
        }

        BufferedReader input = new BufferedReader (new FileReader(args[0]));
        Ratings b = new Ratings();
        b.processSurvey(input);
    }
}
