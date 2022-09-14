public class ActivationFuncs {
    
    /**
     * Accepts a value (z_i) and throws it through the activation function, returns a_i
     * @param input
     * @return
     */
    public static double Sigmoid(double input) {
        return 1.0 / (1.0 + Math.exp(-input));
    }

    public static double SigmoidDeriv(double input) {
        double sig = Sigmoid(input);
        return sig * (1 - sig);
    }
}
