import java.util.ArrayList;

public class Driver {

    public static void do3TrivialSwarm(Config.PsoType type) {
        Config.PSO_TYPE = type;
        int classes = 3;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/3classSimple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 3, Config.BLACKNESS);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);
        runSwarm(swarm, v, points, sizes, "3TrivialSwarm");
    }

    public static void doSwirlNoiseSwarm(Config.PsoType type) {
        Config.PSO_TYPE = type;
        int classes = 2;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/swirlNoise.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -40, 40, Config.BLACKNESS);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);
        runSwarm(swarm, v, points, sizes, "swirlNoiseSwarm");
    }

    public static void doPinWheelSwarm(Config.PsoType type) {
        Config.PSO_TYPE = type;
        int classes = 5;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/pinwheel.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -29, 29, Config.BLACKNESS);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);
        runSwarm(swarm, v, points, sizes, "pinwheelSwarm");
    }

    public static void doSwirlSwarm(Config.PsoType type) {
        Config.PSO_TYPE = type;
        int classes = 2;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/swirl.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -40, 40, Config.BLACKNESS);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);
        runSwarm(swarm, v, points, sizes, "swirlSwarm");
    }

    public static void dox1Sum() {
        int classes = 2;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/x1sumx22less3.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 1, 2, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "x1sum");
    }

    public static void dox1SumSwarm(Config.PsoType type) {
        Config.PSO_TYPE = type;
        int classes = 2;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/x1sumx22less3.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 1, 2, Config.BLACKNESS);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);
        runSwarm(swarm, v, points, sizes, "x1sumSwarm");
    }

    public static void doCircleSwarm(Config.PsoType type) {     
        Config.PSO_TYPE = type;   
        int classes = 2;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/circle.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Config.BLACKNESS);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);
        runSwarm(swarm, v, points, sizes, "circleSwarm");
    }

    public static void doTrivialSwarm(Config.PsoType type) {
        Config.PSO_TYPE = type;
        int classes = 2;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/simple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Config.BLACKNESS);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);
        runSwarm(swarm, v, points, sizes, "simpleSwarm");
    }

    public static void doPinWheel() {
        int classes = 5;
        int[] sizes = {2, 10,10,10,10, classes};
        String targetFile = "../data/pinwheel.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -29, 29, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "pinwheel");
    }

    public static void doSwirl() {
        int classes = 2;
        int[] sizes = {2, 20, classes};
        String targetFile = "../data/swirl.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -40, 40, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "swirl");
    }

    public static void doSwirlNoise() {
        int classes = 2;
        int[] sizes = {2, 100, classes};
        String targetFile = "../data/swirlNoise.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -40, 40, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "swirlNoise");
    }

    public static void doCircleSect() {
        int classes = 2;
        int[] sizes = {2, 10, 10, 10, 10, classes};
        String targetFile = "../data/circlesect.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "circlesect");
    }

    public static void do6Trival() {
        int classes = 6;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/6classSimple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 6, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "6Trivial");
    }


    public static void do3Trivial() {
        int classes = 3;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/3classSimple.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 3, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "3Trivial");
    }

    public static void doHardCircles() {
        int classes = 2;
        int[] sizes = {2, 7,7,7, classes};
        String targetFile = "../data/hardCircles.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "hardCircles");
    }

    public static void doCircle() {
        int classes = 2;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/circle.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "circle");
    }

    public static void doTrivial() {
        int classes = 2;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/simple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "simple");
    }

    public static void doS1Layer() {
        int classes = 2;
        int[] sizes = {2, 100, classes};
        String targetFile = "../data/s.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "s");
    }

    public static void doS() {
        int classes = 2;
        int[] sizes = {2, 7,7,7, classes};
        String targetFile = "../data/s.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Config.BLACKNESS);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "sBig");
    }

    public static String Score(NeuralNetwork net, DataPoint[] points) {
        int correctCount = 0;
        for (int i = 0; i < points.length; i++) {
            int classification = net.Classify(points[i].inputVector);
            if (points[i].expectedOutputVector[classification] == 1.0)
                correctCount += 1;
        }
        String str = (((correctCount + 0.0) / points.length) * 100) + "";
        if (str.length() > 6)
            str = str.substring(0, 6);
        return str + "%";
    }

    public static void run(NeuralNetwork net, Visualize2D v, DataPoint[] points, String saveNetName) {
        for (int j = 0; j < 3000000; j++) {

            DataPoint[] batch = getRandomBatch(points, Config.BATCHSIZE);
            net.BackProp(batch, 0.1);
        
            if (j % 2000 == 0) {
                StdDraw.clear();
                v.draw(net, points[0].expectedOutputVector.length);
                v.scatterPoints(points, 5);
                v.addError(net.Cost(points) + "", j + "", Score(net, points));
                StdDraw.show();
                StdDraw.pause(20);
            }
        }
        net.saveNet("../nets/" + saveNetName + ".net");
    }

    public static void runSwarm(PSO_Swarm swarm, Visualize2D v, DataPoint[] points, int[] sizes, String saveNetName) {
        NeuralNetwork net = null;
        for (int iter = 0; iter < 50000; iter++) {
            DataPoint[] batch = getRandomBatch(points, Config.BATCHSIZE);
            swarm.doUpdate(batch);
            if (iter > 0 && iter % 100 == 0) {
                // update visualizer
                StdDraw.clear();
                net = new NeuralNetwork(sizes, swarm.gBestVec);
                v.draw(net, points[0].expectedOutputVector.length);
                v.scatterPoints(points, 5);
                v.addError(String.format("%.6f", swarm.gBestEval).replace(",", "."), iter + "", Score(net, points));
                StdDraw.show();
                StdDraw.pause(20);
            }
        }
        net.saveNet("../nets/" + saveNetName + ".net");
    }

    /**
     * Generate random batch from the provided dataset. Cannot duplicate samples.
     * @param points
     * @param batchSize
     * @return
     */
    public static DataPoint[] getRandomBatch(DataPoint[] points, int batchSize) {
        DataPoint[] batch = new DataPoint[batchSize];
        ArrayList<Integer> allowedChoices = new ArrayList<Integer>();

        // populate the allowedChoices array, with all indicies
        for (int i = 0; i < points.length; i++) {
            allowedChoices.add(i);
        }

        // populate batch set
        for (int i = 0; i < batch.length; i++) {
            // get the DataPoint from this index
            int selectedIndex = (int)(Math.random() * allowedChoices.size());
            batch[i] = points[allowedChoices.get(selectedIndex)];
            // remove this index from the options
            allowedChoices.remove(selectedIndex);
        }

        return batch;
    }

    /**
     * Determines if the provided set contains the target datapoint
     * @param set
     * @param target
     * @return
     */
    private static boolean containsDataPoint(DataPoint[] set, DataPoint target) {
        for (int i = 0; i < set.length; i++)
            if (set[i] == target)
                return true;
        return false;
    }

    /**
     * Split the provided DataPoint array. Splitting is done using a "knife chop" method, 
     * no shuffling is involved. Shuffling can be used by calling getRandomSample() on a split set.
     * @param setToSplit
     * @param splitPercentage
     * @return Split set of the form {set1, set2}
     */
    public static DataPoint[][] splitSet(DataPoint[] setToSplit, double splitPercentage) {
        
        // get size of first set
        int aSize = (int) (setToSplit.length * splitPercentage);

        // create arrays based on size of first set
        DataPoint[] a = getRandomBatch(setToSplit, aSize);
        DataPoint[] b = new DataPoint[(setToSplit.length - aSize)];

        int bDex = 0;
        // look through setToSplit, if value is not in a, put it in b
        for (int i = 0; i < setToSplit.length; i++) {
            if (!containsDataPoint(a, setToSplit[i])) 
                b[bDex++] = setToSplit[i];
        }
        DataPoint[][] result = {a, b};
        return result;
    }

    public static String printVector(double[] x) {
        StringBuilder vec = new StringBuilder("[ ");
        for (int i = 0; i < x.length; i++) {
            vec.append(x[i]);
            if (i < x.length - 1)
                vec.append(", ");
        }
        vec.append(" ]\n");
        return vec.toString();
    }
}