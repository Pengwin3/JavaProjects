package StainedGlass;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class Reconstructor {


    private Glass[] pieces;
    private Hole window;
    private boolean solved;





    public  Reconstructor(BufferedReader input) throws IOException
    {
        solved = false;
        
        readSilhouettes(input);
    }


    private void readSilhouettes(BufferedReader input) {
        final int MAXPIECES = 8;  // At most 8 pieces
        final int MAXLINES = 8*(MAXPIECES+1);  // At most 8 pieces plus a hole, each at most 8 lines long

        String[] lines = new String[MAXLINES];
        int[] startingLine = new int[MAXPIECES+1];
        Arrays.fill(startingLine, -1);

        int numLines = 0;
        try {
            while (numLines < MAXLINES) {
                lines[numLines] = input.readLine();
                if (lines[numLines] == null) // after EOF
                    break;
                ++numLines;
            }
        } catch (IOException e)
        {
            // do nothing
        }
        char lastDescriptor = 'A';
        int holeStart = 0;
        for (int i = 0; i < numLines; ++i) {
            char c = findNonBlank(lines[i]);
            if (c == '%') {
                holeStart = i;
                break;
            } else {
                lastDescriptor = c;
                if (startingLine[c-'A'] < 0)
                    startingLine[c-'A'] = i;
            }
        }
        int numPieces = lastDescriptor - 'A' + 1;
        startingLine[numPieces] = holeStart;
        pieces = new Glass[numPieces];
        for (char piece = 'A'; piece <= lastDescriptor; ++piece) {
            int pieceNum = piece - 'A';
            String[] pieceLines = Arrays.copyOfRange(lines, startingLine[pieceNum], startingLine[pieceNum+1]);
            pieces[pieceNum] = new Glass(pieceLines);
        }
        String[] holeLines = Arrays.copyOfRange(lines, holeStart, numLines);
        window = new Hole(holeLines);
    }
    
    /**
     * Find the first non-blank character in a string
     * @param string
     * @return the first non-blank character or ' ' if all are blank
     */
    private char findNonBlank(String string) {
        for (int i = 0; i < string.length(); ++i)
            if (string.charAt(i) != ' ')
                return string.charAt(i);
        return ' ';
    }


    private void printSolution() {
        if (solved)
            System.out.print (window.toString());
        else
            System.out.println ("The window cannot be repaired.");
    }

    /**
     * Attempt to place a piece in the window, then recursively solve the remaining part of the puzzle.
     * Continue until solved or determined to be unsolvable.
     * 
     * @param pieceNum piece to place in the window
     */
    private void solve(int pieceNum) {
        if (pieceNum >= pieces.length) {
            solved = window.filled();
        } else {
            Glass p = pieces[pieceNum];
            // System.err.println ("Attempting to insert\n" + p);
            tryToInsert (p, pieceNum);
            if (!solved) {
                Glass p2 = p.flip();
                // System.err.println ("Attempting to insert flipped\n" + p2);
                tryToInsert (p2, pieceNum);
            }
        }
    }

    public void tryToInsert (Glass p, int pieceNum)
    {
        for (int xoffset = 0; xoffset <= window.getWidth() - p.getWidth() && !solved; ++xoffset)
            for (int yoffset = 0; yoffset <= window.getHeight() - p.getHeight() && !solved; ++yoffset)
                if (window.fits(p, xoffset, yoffset))
                {
                    window.insert(p, xoffset, yoffset);
                    // System.err.println ("Inserted into window\n" + window);
                    solve (pieceNum+1);
                    if (solved)
                        return;
                    window.remove(p);
                    // System.err.println ("Removed piece from window\n" + window);
                }
    }


    private class GlassCompare implements Comparator<Glass> {

        @Override
        public int compare(Glass g1, Glass g2) {
            return g2.area() - g1.area();
        }

    }


    /**
     * Attempt to solve the puzzle.
     * 
     * @param pieceNum piece to place in the window
     */
    private void solve() {
        int total = 0;
        for (int i = 0; i < pieces.length; ++i)
            total += pieces[i].area();
        if (total == window.area()) {
            // The areas match, so there's at least a possibility that the 
            // glass pieces can fill up the window. 
            
            // Sort the pieces so that we try the largest ones first - this speeds things
            // up on average because smaller pieces are more likely to "fit" temporarily into
            // incorrect positions, which can lead us to explore a lot of dead ends.
            Arrays.sort(pieces, new GlassCompare());
            
            // Start solving beginning with piece 0
            solve(0);
        }
    }
    
    

    public static void main (String[] argv) throws FileNotFoundException
    {
        BufferedReader inScan = null;
        if (argv.length > 0) {
            FileReader infile = new FileReader(argv[0]);
            inScan = new BufferedReader(infile);
        } else {
            inScan = new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            Reconstructor cw = new Reconstructor(inScan);
            cw.solve();
            cw.printSolution();
        } catch (IOException e) {
            System.err.println ("Input format error: " + e);
        }
    }


}
