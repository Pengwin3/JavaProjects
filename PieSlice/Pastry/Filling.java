package Pastry;

import Pastry.Pie;

/**
 * Created by devin on 16/11/16.
 */
public class Filling {
    public String filling = "rhubarb";
    public Pie myPie;

    @Override
    public String toString() {
        return "Pastry.Filling{" +
                "filling='" + filling + '\'' +
                ", myPie=" + myPie +
                '}';
    }
}
