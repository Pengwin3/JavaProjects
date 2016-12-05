/**
 *
 */
package StainedGlass;

/**
 * A hole that can contain multiple pieces of stained glass
 *
 * @author zeil
 *
 */
public class Hole {
    private int width;
    private int height;
    private char[][] chars;

    /**
     * Initializes a hole given a representation in lines of text.
     *
     * For example; given lines == {"  %", " %%", "  %"}, we would get a Hole
     * with width 2, height 3, such that charAt(0,0) == ' '
     * and charAt(0,1) == '%'
     *
     * @param text  array of strings. Each represents a single line across the glass piece. Blanks indicate
     *     horizontal positions where no glass is present. A '%' indicates a position where glass could
     *     be placed. A letter indicates a position where glass has been placed.
     */
    public Hole (String[] text)
    {
        width = 0;
        height = text.length;
        int leftOffset = Integer.MAX_VALUE;

        for (int i = 0; i < height; ++i) {
            for (int w = 0; w < text[i].length(); ++w) {
                if (text[i].charAt(w) != ' ') {
                    descriptor = text[i].charAt(w);
                    width = Math.max(width, w+1);
                    leftOffset = Math.min(leftOffset, w);
                }
            }
        }
        width -= leftOffset;

        chars = new char[height][width];
        for (int h = 0; h < height; ++h) {
            for (int w = leftOffset; w < text[h].length(); ++w)
                chars[h][w-leftOffset] = text[h].charAt(w);
            for (int w = text[h].length() - leftOffset; w < width; ++w)
                chars[h][w] = ' ';
        }
    }


    /**
     * Returns the character indicating the presence or absence of glass at position
     * (w,h) in this piece.
     *
     * If w or h are out of bounds, returns ' ' (no glass).
     *
     * @param x x position (starts at 0, increases moving to the right)
     * @param y y position (starts at 0, increases moving down the glass)
     * @return descriptor if glass is present at that location, ' ' otherwise
     */
    public char charAt(int x, int y) {
        if (y < 0 || y >= height)
            return ' ';
        if (x < 0 || x >= width)
            return ' ';
        return chars[y][x];
    }


    /**
     * @return the width
     */
    public int getWidth() { return width; }

    /**
     * @return the height
     */
    public int getHeight() { return height; }


    /**
     * Determines whether a given piece of glass would fit into the empty spaces of this
     * hole if the piece were shifted over by (xoffset,yoffset) from the upper left corner
     * of the hole.
     *
     * @param g a piece of glass
     * @param xoffset horizontal offset
     * @param yoffset vertical offset
     * @return true if the glass could be placed in empty spaces in this hole
     *
     */
    public boolean fits (Glass g, int xoffset, int yoffset)
    {
        if (g.getWidth() < getWidth() && g.getHeight() < getHeight()) {

        }
    }

    /**
     * Inserts a piece of glass into the empty spaces of this
     * hole, shifting the piece over by (xoffset,yoffset) from the upper left corner
     * of the hole.  '%' empty spots in this hole are filled with the descriptor for the glass.
     *
     * Pre-requisite: fits(g, xoffset, yoffset)
     *
     * @param g a piece of glass
     * @param xoffset horizontal offset
     * @param yoffset vertical offset
     *
     */
    public void insert (Glass g, int xoffset, int yoffset)
    {
        // your code here
    }

    /**
     * Removes a piece of glass previously fitted into this
     * hole, All spaces in this hole currently occupied by
     * descriptors for g are made empty (replaced by '%')
     *
     * @param g a piece of glass
     * @return true if the glass could be placed in empty spaces in this hole
     *
     */
    public void remove (Glass p)
    {
        // your code here
    }

    /**
     * Is the hole completely filled?
     *
     * @return true if all empty spaces have been filled with glass
     */
    public boolean filled()
    {
        // your code here
    }

    /**
     * The area is the total number of positions that are
     * occupied by colored glass are that are empty and could be
     * so filled.
     *
     * @return area of this piece
     */
    public int area()
    {
        // your code here
    }


    public String toString()
    {
        // your code here
    }

    public boolean equals (Object o)
    {
        // your code here
    }

}