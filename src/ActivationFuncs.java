public class ActivationFuncs {
    



    public static double Activate(double input, Config.Activation e) {
        switch (e) {
            case SIGMOID:
                return Sigmoid(input);
            case RELU:
                return Relu(input);
            default:
                System.out.println("fk u");
                return -1;
        }
    }

    public static double ActivateDeriv(double input, Config.Activation e) {
        switch (e) {
            case SIGMOID:
                return SigmoidDeriv(input);
            case RELU:
                return ReluDeriv(input);
            default:
                System.out.println("fk u");
                return -1;
        }
    }
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


    public static double Relu(double input) {
        return Math.max(input, 0.0);
    }

    public static double ReluDeriv(double input) {
        return (input > 0) ? 1.0 : 0.0;
    }

}
