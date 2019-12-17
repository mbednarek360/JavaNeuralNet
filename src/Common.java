// required libraries
import java.lang.Math;
import java.util.Random;

// shared static functions
public class Common {

    // squishification
    public static double sigmoid(double x) {
        return (1 / (1 + Math.pow(Math.E, (-1 * x))));
    }

    // first derivative
    public static double sigderiv(double x) {
        return sigmoid(x) * (1 - sigmoid(x));
    }

    // random weight init
    public static double randomWeight() {
        Random r = new Random();
        return r.nextGaussian();
    }

    // rectifier
    public static double relu(double input) {
        return input < 0 ? 0 : input;
    }

    // caclulate derivative for single bias
    public static double deriveBias(float curAct, float sum, float expected) {
        return 2 * Common.sigderiv(sum) * (curAct - expected);
    }

    // calculate derivative for single weight
    public static double derviceWeight(float curAct, float lastAct, float sum, float expected) {
        return deriveBias(curAct, sum, expected) * lastAct;
    }
}