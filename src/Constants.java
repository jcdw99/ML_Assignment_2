import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final int batchSize = 100;
    public static final int swarmSize = 30;
    public static final double learnRate = 0.1;
    public static final double blackness = 0.65;
    public static final double WD = 0.0001;
    public static final int iterations = 20000;

    public static final String heart = "heart";
    public static final String wine = "wine";
    public static final String cancer = "breast";

    public static final String[] dataPaths = {heart, wine, cancer};
    public static final Map<String, Integer> datasetClasses = new HashMap<>(){{
        put(heart, 2);
        put(wine, 3);
        put(cancer, 2);
    }};
    public static final Map<String, int[]> datasetNNSize = new HashMap<>(){{
        put(heart, new int[]{13, 6});
        put(wine, new int[]{13, 10});
        put(cancer, new int[]{30, 10});
    }};
}
