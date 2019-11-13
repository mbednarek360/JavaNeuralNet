// required libraries
import java.lang.Math;
import java.util.Random;

// shared static functions
public class Common {

    // squishification
    public static double sigmoid(double x) {
        return (1 / (1 + Math.pow(Math.E, (-1 * x))));
    }

    // random weight init
    public static double randomWeight() {
        Random r = new Random();
        return r.nextGaussian();
    }

    // rectifier
    public static double relu(double input) {
        return input < 0 ? input : input;
    }
}