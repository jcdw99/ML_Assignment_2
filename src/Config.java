public class Config {

    public enum Activation {
        SIGMOID,
        RELU
    }

    public enum PsoType {
        QUANTUM,
        INTERTIA
    }

    public static int batchSize = 100;
    public static int swarmSize = 30;
    public static double learnRate = 0.1;
    public static double blackness = 0.65;
    public static double WD = 0.00;
    public static double chargedPercent = 0.5;
    public static PsoType activePsoType = PsoType.QUANTUM;
    public static Activation AFUNC = Activation.SIGMOID;
    public static double w = 0.7;
    public static double c1 = 1.4;
    public static double c2 = 1.4;
    public static double radius = 7;
}
