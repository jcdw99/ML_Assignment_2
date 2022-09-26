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
    public static final int SWARMSIZE = 30;
    public static PsoType PSO_TYPE = PsoType.QUANTUM;
    public static final double CHARGE_PERCENT = 0.5;
    public static final double W = 0.7;
    public static final double C1 = 1.4;
    public static final double C2 = 1.4;
    public static final double RADIUS = 20;

    /*
     * NEURAL NET BASED CONSTANTS
     */
    public static final double LEARNRATE = 0.1;
    public static final double WD = 0.01;
    public static final Activation AFUNC = Activation.SIGMOID;

    /*
     * VISUALIZER CONSTANTS
     */
    public static final double BLACKNESS = 0.65;

    /*
     * EVALUATOR CONSTANTS
     */
    public static int BATCHSIZE = 100;
    public static final int WRITE_GRANULARITY = 100;
    public static final int TRIALS = 20;
    // Iterations refers to swarm iterations. NN does (ITERATIONS * SWARMSIZE) backprop iterations
    public static final int ITERATIONS = 10_000;
}
