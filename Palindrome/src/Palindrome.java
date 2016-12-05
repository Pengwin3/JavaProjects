import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.lang.reflect.Array;

/**
 * Created by devin on 30/11/16.
 */
public class Palindrome {
    public IntegerArray drome;
    public int n;

    private IntegerArray getDrome() {
        return drome;
    }

    private int getn() {
        return n;
    }

    private static void recurs(IntegerArray value, int n) {
        value.reverse();
        IntegerArray next = value;
        value.reverse();
        System.out.print(next + " " + value);
    }

    public static void main(IntegerArray args[]) {

        try {
            IntegerArray drome = args[2];
            int n = 0;
            Palindrome palin = new Palindrome(drome, n);
//            palin(drome, n);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.checkError();
        }
    }
}