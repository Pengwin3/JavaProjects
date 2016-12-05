/**
 * 
 */
package StainedGlass;


/**
 * A single piece of stained glass.
 * 
 * @author zeil
 *
 */
public class Glass {
    private int width;
    private int height;
    private char descriptor;
    private char[][] chars;

    /**
     * Initializes a piece of glass given a representation in lines of text.
     * 
     * For example; given lines == {"  W", " WW", "  W"}, we would get a Glass piece
     * with width 2, height 3, and a descriptor of 'W', such that charAt(0,0) == ' '
     * and charAt(0,1) == 'W' 
     * 
     * @param text  array of strings. Each represents a single line across the glass piece. Blanks indicate
     *     horizontal positions where no glass is present. A letter indicates a position where class of a "color"
     *     denoted by that letter can be found.
     */
    public Glass (String[] text)
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
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the descriptor character indicating the presence of glass
     */
    public char getDescriptor() {
        return descriptor;
    }

 

    
    
    /**
     * Produces a new piece of glass by flipping this one around its vertical axis;
     * E.g.,  AAA           AAA
     *          A   becomes A
     *         A              A
     */
    public Glass flip()
    {
        String[] flippedLines = new String[height];
        for (int y = 0; y < height; ++y) {
            StringBuffer buf = new StringBuffer();
            for (int x = width-1; x >= 0; --x)
                buf.append(chars[y][x]);
            flippedLines[y] = buf.toString();
        }
        return new Glass(flippedLines);
    }


    /**
     * The area is the total number of positions occupied by colored glass
     * 
     * @return area of this piece
     */
     public int area()
    {
         int total = 0;
         for (int h = 0; h < height; ++h)
             for (int w = 0; w < width; ++w)
                 if (chars[h][w] != ' ')
                     ++total;
         return total;
    }

    
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        for (int h = 0; h < height; ++h) {
            int w = width-1;
            while (w >= 0 && chars[h][w] == ' ')
                --w;
            for (int x = 0; x <= w; ++x)
                buf.append(chars[h][x]); 
            buf.append('\n');
        }
        return buf.toString();
    }
    
    public boolean equals (Object o)
    {
        try {
            Glass g = (Glass)o;
            if (width != g.width || height != g.height || descriptor != g.descriptor) {
                return false;
            }
            for (int h = 0; h < height; ++h)
                for (int w = 0; w < width; ++w)
                    if (charAt(w,h) != g.charAt(w,h))
                        return false;
            return true;        
        } catch (Exception e) {
            return false;
        }
    }




}
