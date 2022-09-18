import java.util.Arrays;

public class Driver {

    public static final int w = 0;
    public static final int c1 = 1;
    public static final  int c2 = 2;
    public static final int percentCharged = 3;
    public static final int radius = 4;
    public static final int batchSize = 5;
    public static final int weightDecay = 6;
    public static final int charge = 7;
    public static final int coreRadius = 8;
    public static final int perceptionRadius = 9;

    private static double[] params = {0.7, 1.4, 1.4, 0.5, 5, Constants.batchSize,
            Constants.WD, 10, 0.1, 2};

    public static void doPinWheel() {
        int classes = 5;
        int[] sizes = {2, 10,10,10,10, classes};
        String targetFile = "../data/pinwheel.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -29, 29, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "pinwheel");
    }

    public static void doSwirl() {
        int classes = 2;
        int[] sizes = {2, 20, classes};
        String targetFile = "../data/swirl.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -40, 40, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "swirl");
    }
    public static void doSwirlSwarm() {
        int classes = 2;
        int[] sizes = {2, 20, classes};
        String targetFile = "../data/swirl.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -40, 40, Constants.blackness);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, sizes, points, params,
                PSO_Particle.ParticleType.inertia);
        runSwarm(swarm, v, points, sizes, "swirlSwarm");
    }

    public static void doSwirlNoise() {
        int classes = 2;
        int[] sizes = {2, 100, classes};
        String targetFile = "../data/swirlNoise.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, -40, 40, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "swirlNoise");
    }

    public static void doCircleSect() {
        int classes = 2;
        int[] sizes = {2, 10, 10, 10, 10, classes};
        String targetFile = "../data/circlesect.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "circlesect");
    }

    public static void do6Trival() {
        int classes = 6;
        int[] sizes = {2, 10, classes};
        String targetFile = "../data/6classSimple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 6, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "6Trivial");
    }


    public static void do3Trivial() {
        int classes = 3;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/3classSimple.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 3, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "3Trivial");
    }

    public static void do3TrivialSwarm() {
        int classes = 3;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/3classSimple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 3, Constants.blackness);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, sizes, points, params,
                PSO_Particle.ParticleType.inertia);
        runSwarm(swarm, v, points, sizes, "3TrivialSwarm");
    }

    public static void doHardCircles() {
        int classes = 2;
        int[] sizes = {2, 7,7,7, classes};
        String targetFile = "../data/hardCircles.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "hardCircles");
    }

    public static void dox1Sum() {
        int classes = 2;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/x1sumx22less3.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 1, 2, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "x1sum");
    }

    public static void dox1SumSwarm() {
        int classes = 2;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/x1sumx22less3.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 1, 2, Constants.blackness);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, sizes, points, params,
                PSO_Particle.ParticleType.inertia);
        runSwarm(swarm, v, points, sizes, "x1sumSwarm");
    }


    public static void doCircle() {
        int classes = 2;
        int[] sizes = {2, 6, 6, classes};
        String targetFile = "../data/circle.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "circle");
    }

    public static void doCircleSwarm() {
        int classes = 2;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/circle.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Constants.blackness);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, sizes, points, params,
                PSO_Particle.ParticleType.inertia);
        runSwarm(swarm, v, points, sizes, "circleSwarm");

    }

    public static void doTrivial() {
        int classes = 2;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/simple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "simple");
    }

    public static void doTrivialSwarm() {
        int classes = 2;
        int[] sizes = {2, 4, classes};
        String targetFile = "../data/simple.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Constants.blackness);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, sizes, points, params,
                PSO_Particle.ParticleType.inertia);
        runSwarm(swarm, v, points, sizes, "simpleSwarm");

    }

    public static void doQSwarm() {
        int classes = 2;
        int[] sizes = {2, 4, 4, classes};
        String targetFile = "../data/circle.csv";
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2, Constants.blackness);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, sizes, points, params,
                PSO_Particle.ParticleType.quantum);
        runSwarm(swarm, v, points, sizes, "simpleSwarm");

    }

    public static void doChargedSwarm() {
        int classes = 3;
        int[] layers = {2, 8, classes};
        String targetFile = "../data/3classSimple.csv";
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 3, Constants.blackness);

        double[] ps = Arrays.copyOf(params, params.length);
        ps[Driver.charge] = 7.0;
        ps[Driver.coreRadius] = 1.0;
        ps[Driver.perceptionRadius] = 10.0;
        ps[Driver.weightDecay] = 0.0001;

        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, layers, points, params,
                PSO_Particle.ParticleType.quantum);
        runSwarm(swarm, v, points, layers, "simpleSwarm");

    }

    public static void doS1Layer() {
        int classes = 2;
        int[] sizes = {2, 100, classes};
        String targetFile = "../data/s.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "s");
    }

    public static void doS() {
        int classes = 2;
        int[] sizes = {2, 7,7,7, classes};
        String targetFile = "../data/s.csv";
        Visualize2D v = new Visualize2D(800, 800, 4, 0, 2, Constants.blackness);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);
        run(net, v, points, "sBig");
    }

    public static String Score(NeuralNetwork net, DataPoint[] points) {
        int correctCount = 0;
        for (int i = 0; i < points.length; i++) {
            int classification = net.Classify(points[i].inputVector);
            if (points[i].expectedOutputVector[classification] == 1.0)
                correctCount+=1;
        }
        String str = (((correctCount + 0.0) / points.length) * 100) + "";
        if (str.length() > 6)
            str = str.substring(0, 6);
        return str + "%";
    }

    public static void run(NeuralNetwork net, Visualize2D v, DataPoint[] points, String saveNetName) {
        for (int j = 0; j < Constants.iterations; j++) {

            DataPoint[] batch = getRandomBatch(points, Constants.batchSize);
            net.BackProp(batch, 0.1, Constants.WD);

            if (j % 200 == 0) {
                StdDraw.clear();
                v.draw(net, points[0].expectedOutputVector.length);
                v.scatterPoints(points, 5);
                v.addError(String.format("%.6f", net.Cost(points, Constants.WD)), j + "", Score(net, points));
                StdDraw.show();
                StdDraw.pause(20);
            }
        }
        net.saveNet("../nets/" + saveNetName + ".net");
    }

    public static void runSwarm(PSO_Swarm swarm, Visualize2D v, DataPoint[] points, int[] sizes, String saveNetName) {
        NeuralNetwork net = null;
        for (int iter = 0; iter < Constants.iterations; iter++) {
            DataPoint[] batch = getRandomBatch(points, Constants.batchSize);
            swarm.doUpdate(points);
            if (iter > 0 && iter % 100 == 0) {
                // update visualizer
                net = new NeuralNetwork(swarm.NNSize, swarm.gBestVec);
                StdDraw.clear();
                v.draw(net, points[0].expectedOutputVector.length);
                v.scatterPoints(points, 5);
                v.addError(String.format("%.6f", swarm.gBestEval).replace(",", "."),
                        iter + "", Score(net, points));
                StdDraw.show();
                StdDraw.pause(20);
            }
        }
        net.saveNet("../nets/" + saveNetName + ".net");
    }

    public static DataPoint[] getRandomBatch(DataPoint[] points, int batchSize) {
        DataPoint[] batch = new DataPoint[batchSize];
        for (int i = 0; i < batch.length; i++) {
            batch[i] = points[(int)(Math.random() * points.length)];
        }
        return batch;
    }

    public static void findGradientDescentParameters() {
        int classes = 3;
        int[] layers = {2, 8, classes};
        String targetFile = "../data/3classSimple.csv";
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);

        String bestConfig = "Nope";
        double bestMSE = Double.MAX_VALUE;

        double[] learningRates = {0.0001, 0.001, 0.01, 0.1};
        double[] weightDecays = {0.0001, 0.001, 0.01, 0.1};

        for (double learnRate: learningRates) {
            for (double weightDecay: weightDecays) {

                String config = "Learning rate: " + learnRate + "\n" +
                        "Weight decay: " + weightDecay;
                System.out.println(config);

                NeuralNetwork net = new NeuralNetwork(layers);
                for (int i = 0; i < Constants.iterations; i++) {
                    DataPoint[] batch = getRandomBatch(points, Constants.batchSize);
                    net.BackProp(batch, learnRate, weightDecay);
                }
                //TODO:: Find the validation MSE
                double MSE = net.Cost(points, 0.0);
                System.out.println("MSE: " + MSE + "\n");

                if (MSE < bestMSE) {
                    bestMSE = MSE;
                    bestConfig = config;
                }
            }
        }

        System.out.println("------------------------------");
        System.out.println("Best configurations: \n" + bestConfig + "\nMSE: " + bestMSE);
    }

    public static void findQuantumParameters() {
        int classes = 3;
        int[] layers = {2, 8, classes};
        String targetFile = "../data/3classSimple.csv";
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);

        String bestConfig = "Nope";
        double bestMSE = Double.MAX_VALUE;

        double[] radi = {0.01, 0.1, 1.0, 2.0, 5.0, 10.0};
        double[] weightDecays = {0.0001, 0.001, 0.01, 0.1};

        for (double radius: radi) {
            for (double weightDecay: weightDecays) {

                double[] ps = Arrays.copyOf(params, params.length);
                ps[Driver.weightDecay] = weightDecay;
                ps[Driver.radius] = radius;

                String config = "WeightDecay: " + weightDecay + "\n" +
                        "Radius: " + radius;
                System.out.println(config);

                PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, layers, points, ps,
                        PSO_Particle.ParticleType.quantum);

                for (int i = 0; i < Constants.iterations; i++) {
                    DataPoint[] batch = getRandomBatch(points, Constants.batchSize);
                    swarm.doUpdate(batch);
                }

                //TODO:: Find the validation MSE
                double MSE = (new NeuralNetwork(swarm.NNSize, swarm.gBestVec)).Cost(points, 0.0);
                System.out.println("MSE: " + MSE + "\n");

                if (MSE < bestMSE) {
                    bestMSE = MSE;
                    bestConfig = config;
                }
            }
        }

        System.out.println("------------------------------");
        System.out.println("Best configurations: \n" + bestConfig + "\nMSE: " + bestMSE);
    }

    public static void findChargeParameters() {
        int classes = 3;
        int[] layers = {2, 8, classes};
        String targetFile = "../data/3classSimple.csv";
        DataPoint[] points = Loader.loadPointsFromFile(targetFile, 2, classes);

        String bestConfig = "Nope";
        double bestMSE = Double.MAX_VALUE;

        //TODO:: create a range that is sampled from at each iteration
        double[] charges = {7, 10};
        double[] coreRadii = {0.01, 0.1, 1.0, 2};
        double[] perceptionRadii = {3, 5, 7, 10};
        double[] weightDecays = {0.0001, 0.001, 0.01, 0.1};

        for (double charge: charges) {
            for (double coreRadius: coreRadii) {
                for (double perceptionRadius: perceptionRadii) {
                    for (double weightDecay: weightDecays) {

                        double[] ps = Arrays.copyOf(params, params.length);
                        ps[Driver.charge] = charge;
                        ps[Driver.coreRadius] = coreRadius;
                        ps[Driver.perceptionRadius] = perceptionRadius;
                        ps[Driver.weightDecay] = weightDecay;

                        String config = "Charge: " + charge + "\n" +
                                "CoreRadius: " + coreRadius + "\n" +
                                "PerceptionRadius: " + perceptionRadius + "\n" +
                                "WeightDecay: " + weightDecay;

                        System.out.println(config);

                        PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, layers, points, ps,
                                PSO_Particle.ParticleType.charged);

                        for (int i = 0; i < Constants.iterations; i++) {
                            DataPoint[] batch = getRandomBatch(points, Constants.batchSize);
                            swarm.doUpdate(batch);
                        }

                        //TODO:: Find the validation MSE
                        double MSE = (new NeuralNetwork(swarm.NNSize, swarm.gBestVec)).Cost(points, 0.0);
                        System.out.println("MSE: " + MSE);

                        if (MSE < bestMSE) {
                            bestMSE = MSE;
                            bestConfig = config;
                        }
                        System.out.println();
                    }
                }
            }
        }

        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println("Best configurations: \n" + bestConfig + "\nMSE: " + bestMSE);
    }

    public static void recordGradientDescent() {
        String datasetName = Constants.heart;

        int classes = Constants.datasetClasses.get(datasetName);
        int[] sizes = Constants.datasetNNSize.get(datasetName);
        int[] layers = {sizes[0], sizes[1], classes};
        //TODO: Split the data into training and test
        DataPoint[] points = Loader.loadPointsFromFile("../data/" + datasetName + ".csv",
                sizes[0], classes);

        int[] batchSizes = {20, 50, 100, points.length};

        int numTrials = 5;
        int recordSteps = 10;

        double[][] histories = new double[batchSizes.length][Constants.iterations/recordSteps];

        for (int b = 0; b < batchSizes.length; b++) {
            int batchSize = Math.min(batchSizes[b], points.length);
            System.out.println("BatchSize: " + batchSize);
            for (int t = 0; t < numTrials; t++) {
                NeuralNetwork net = new NeuralNetwork(layers);
                for (int i = 0; i < Constants.iterations; i++) {
                    DataPoint[] batch = getRandomBatch(points, batchSize);
                    net.BackProp(batch, Constants.learnRate, Constants.WD);

                    if (i % recordSteps == 0) {
                        histories[b][i/recordSteps] += net.Cost(points, 0.0);
                    }
                }
            }
        }
        HistorySaver.saveHistories("../histories/GD-" + datasetName, histories, batchSizes);
    }

    public static void recordQuantumSwarm() {
        String datasetName = Constants.heart;

        int classes = Constants.datasetClasses.get(datasetName);
        int[] sizes = Constants.datasetNNSize.get(datasetName);
        int[] layers = {sizes[0], sizes[1], classes};
        //TODO: Split the data into training and test
        DataPoint[] points = Loader.loadPointsFromFile("../data/" + datasetName + ".csv",
                sizes[0], classes);

        int[] batchSizes = {20, 50, 100, points.length};

        int numTrials = 5;
        int recordSteps = 10;

        double[][] histories = new double[batchSizes.length][Constants.iterations/recordSteps];

        double[] ps = Arrays.copyOf(params, params.length);

        ps[Driver.radius] = 5.0;
        ps[Driver.weightDecay] = 0.0001;

        for (int b = 0; b < batchSizes.length; b++) {
            int batchSize = Math.min(batchSizes[b], points.length);
            System.out.println("BatchSize: " + batchSize);
            for (int t = 0; t < numTrials; t++) {
                PSO_Swarm swarm = new PSO_Swarm(Constants.swarmSize, layers, points, ps,
                        PSO_Particle.ParticleType.inertia);

                for (int i = 0; i < Constants.iterations; i++) {
                    DataPoint[] batch = getRandomBatch(points, Constants.batchSize);
                    swarm.doUpdate(batch);
                }

                //TODO:: Find the validation MSE
                double MSE = (new NeuralNetwork(swarm.NNSize, swarm.gBestVec)).Cost(points, 0.0);
                System.out.println("MSE: " + MSE);

                NeuralNetwork net = new NeuralNetwork(layers);
                for (int i = 0; i < Constants.iterations; i++) {
                    DataPoint[] batch = getRandomBatch(points, batchSize);
                    net.BackProp(batch, Constants.learnRate, Constants.WD);

                    if (i % recordSteps == 0) {
                        histories[b][i/recordSteps] += net.Cost(points, 0.0);
                    }
                }
            }
        }
        HistorySaver.saveHistories("../histories/GD-" + datasetName, histories, batchSizes);
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