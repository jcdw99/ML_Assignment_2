public class Config {

    public enum Activation {
        SIGMOID,
        RELU
    }

    public enum PsoType {
        QUANTUM,
        INTERTIA
    }

    /*
     * PSO-BASED CONSTANTS
     */
    public static int BATCHSIZE = 100;
    public static int SWARMSIZE = 30;
    public static PsoType PSO_TYPE = PsoType.QUANTUM;
    public static double CHARGE_PERCENT = 0.5;
    public static double W = 0.7;
    public static double C1 = 1.4;
    public static double C2 = 1.4;
    public static double RADIUS = 7;

    /*
     * NEURAL NET BASED CONSTANTS
     */
    public static double LEARNRATE = 0.1;
    public static double WD = 0.001;
    public static Activation AFUNC = Activation.SIGMOID;

    /*
     * VISUALIZER CONSTANTS
     */
    public static double BLACKNESS = 0.65;

    /*
     * EVALUATOR CONSTANTS
     */
    public static final int WRITE_GRANULARITY = 100;
    public static final int TRIALS = 20;
    public static final int ITERATIONS = 10_000;
}
