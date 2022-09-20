import java.io.PrintWriter;

public class Evaluator {


    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[1;31m";
    public static final String GREEN = "\u001B[1;32m";
    public static final String YELLOW = "\u001B[1;33m";
    public static final String BLUE = "\u001B[1;34m";
    public static final String MAGENTA = "\u001B[1;35m";
    public static final String CYAN = "\u001B[1;36m";
    public static final String WHITE = "\u001B[1;37m";


    /**
     * Below we have swarm related evaluator functions. Each evaluator function defers to the respective run
     * function, passing in the correct class and dataset information
     */
    public static void do3TrivialSwarm(Config.PsoType type) {
        DataPoint[] points = Loader.loadPointsFromFile("../data/3classSimple.csv", 2, 3);
        int classes = 3;
        int[] sizes = {2, 10, classes};
        runSwarm(points, sizes, "3Trivial", type);
    }

    public static void doPinWheelSwarm(Config.PsoType type) {
        DataPoint[] points = Loader.loadPointsFromFile("../data/pinwheel.csv", 2, 5);
        int classes = 5;
        int[] sizes = {2, 10, classes};
        runSwarm(points, sizes, "Pinwheel", type);
    }

    public static void doSwirlSwarm(Config.PsoType type) {
        DataPoint[] points = Loader.loadPointsFromFile("../data/swirl.csv", 2, 2);
        int classes = 2;
        int[] sizes = {2, 10, classes};
        runSwarm(points, sizes, "Swirl", type);
    }



    /**
     * Below we have NN related evaluator functions. Each evaluator function defers to the respective run
     * function, passing in the correct class and dataset information
     */
    public static void do3Trivial() {
        DataPoint[] points = Loader.loadPointsFromFile("../data/3classSimple.csv", 2, 3);
        int classes = 3;
        int[] sizes = {2, 10, classes};
        runNN(points, sizes, "3Trivial");
    }

    public static void doPinWheel() {
        DataPoint[] points = Loader.loadPointsFromFile("../data/pinwheel.csv", 2, 5);
        int classes = 5;
        int[] sizes = {2, 10, classes};
        runNN(points, sizes, "Pinwheel");
    }

    public static void doSwirl() {
        DataPoint[] points = Loader.loadPointsFromFile("../data/swirl.csv", 2, 2);
        int classes = 2;
        int[] sizes = {2, 10, classes};
        runNN(points, sizes, "Swirl");
    }




    public static void printClassificationAccuracy(NeuralNetwork net, DataPoint[] testSet) {
        double accuracy = net.ClassifyAccuracy(testSet);
        String percent = ((accuracy * 100) + "").substring(0, Math.min(((accuracy * 100) + "").length(), 6)) + "%";
        System.out.printf("    %sTest-Data Classification Accuracy: %s%s%s\n", WHITE, RED, percent, WHITE);
    }


    /**
     * Runs a single PSO Trial. The network is trained on random batches from the TrainSet. 
     * After the training is complete, a NeuralNetwork is created from the gbestVec of the Swarm.
     * The classification accuracy, element of [0,1] is determined on this net, and the testset.
     * 
     * @param swarm
     * @param trainData
     * @param testData
     * @param sizes
     * @return a 2d double array of the form { training MSE per Granule, {classification accuracy on test set} }
     */
    public static double[][] runSwarmTrial(DataPoint[] trainData, DataPoint[] testData,  int[] sizes) {
        
        // construct the swarm for this trial
        PSO_Swarm swarm = new PSO_Swarm(Config.SWARMSIZE, sizes);

        double[] trialData = new double[(int)(Config.ITERATIONS / Config.WRITE_GRANULARITY) - 1];
        int writeCount = 0;

        // conduct the trial
        for (int iter = 0; iter < Config.ITERATIONS; iter++) {
            // get batch from training set
            DataPoint[] batch = Driver.getRandomBatch(trainData, Config.BATCHSIZE);
            swarm.doUpdate(batch);
            // if this is a granule, write to output file
            if (iter > 0 && iter % Config.WRITE_GRANULARITY == 0) {
                // create a new neural net, to evaluate this accuracy and cost, on entire training set
                NeuralNetwork net = new NeuralNetwork(sizes, swarm.gBestVec);
                trialData[writeCount++] =  net.Cost(trainData);
                if (writeCount % 10 == 0)
                    System.out.printf("\t%sIter: %s%d of %d\n", WHITE, BLUE, iter, Config.ITERATIONS);
            }
        }

        // generate NN from gbest configuration, to obtain final classification accuracy on test set
        NeuralNetwork n = new NeuralNetwork(sizes, swarm.gBestVec);
        double[][] results = {trialData, {n.ClassifyAccuracy(testData)} };
        printClassificationAccuracy(n, testData);
        return results;
    }

    /**
     * To make the comparison between SGD and PSO based algorithms more fair, we allow <SwarmSize> NN's each using SGD to run during each trial.
     * The best NN from this array is saved, and the performance recorded. Recall, the PSO training algorithms use a swarm of particles, each with its
     * own underlying Neuralnetwork structure, if we keep iterations constant, then PSO based algorithms evaluate <SwarmSize>  more NN's than standard SGD.
     * This is why we need to allow SGD to run for <Config.Trials> * <Config.SwarmSize> total trials, which is equivalent to running an array of <Config.Swarmsize>
     * NN's per trial.
     * @param trainData
     * @param testData
     * @param sizes
     * @return
     */
    public static double[][] runNNTrial(DataPoint[] trainData, DataPoint[] testData,  int[] sizes) {
        
        // construct the NN's for this trial
        NeuralNetwork[] n = new NeuralNetwork[Config.SWARMSIZE];

        // initialize all neural networks
        for (int i =0; i < n.length; i++) {
            n[i] = new NeuralNetwork(sizes);
        }

        double[] trialData = new double[(int)((Config.ITERATIONS) / (Config.WRITE_GRANULARITY)) - 1];
        int writeCount = 0;

        // conduct the trial
        for (int iter = 0; iter < Config.ITERATIONS; iter++) {

            // get batch from training set
            DataPoint[] batch = Driver.getRandomBatch(trainData, Config.BATCHSIZE);

            // backprop this data through each network in the array of networks
            for (int i = 0; i < n.length; i++) {
                n[i].BackProp(batch, Config.LEARNRATE);
            }

            // if this is a granule, write to output file
            if (iter > 0 && iter % (Config.WRITE_GRANULARITY) == 0) {

                // find best NN in array, and write its cost to file
                double minCost = n[0].Cost(batch);
                for (int i = 1; i < n.length; i++) {
                    // check cost of this network
                    double thisNetCost = n[i].Cost(batch);
                    // if the cost is lower than the best found, update best found
                    if (thisNetCost < minCost)
                        minCost = thisNetCost;
                }
                
                // write lowest cost in array of networks, to file
                trialData[writeCount++] =  minCost;
                if (writeCount % 10 == 0)
                    System.out.printf("\t%sIter: %s%d of %d\n", WHITE, BLUE, iter, Config.ITERATIONS);
            }
        }
        // lets go find the network with the lowest cost, to use in final classification accuracy
        double minCost = n[0].Cost(testData);
        int minDex = 0;
        for (int i = 1; i < n.length; i++) {
            double thisNetCost = n[i].Cost(testData);
            if (thisNetCost < minCost) {
                minCost = thisNetCost;
                minDex = i;
            }
        }

        // generate NN from gbest configuration, to obtain final classification accuracy on test set
        double[][] results = {trialData, {n[minDex].ClassifyAccuracy(testData)} };
        printClassificationAccuracy(n[minDex], testData);
        return results;
    }

    public static void runNN(DataPoint[] points, int[] sizes, String fileName) {

        // NN experiment info
        String batchSize = "_" + Config.BATCHSIZE;

        // get training and testing data
        double[][] trainingMSE = new double[Config.TRIALS][];
        double[][] testingAccs = new double[Config.TRIALS][1];
        
        // for each independent trial
        for (int trial = 0; trial < Config.TRIALS; trial++) {
            System.out.printf("%sRunning %s%s%s Trial: %s%d of %d%s\n", WHITE, GREEN, "NN" + batchSize, WHITE, GREEN, trial + 1, Config.TRIALS, RESET);

            // get this trials trainSet and testSet
            DataPoint[][] trainTest = Driver.splitSet(points, 0.7);

            // conduct the trial
            double[][] results = runNNTrial(trainTest[0], trainTest[1],  sizes);

            // record the results
            trainingMSE[trial] = results[0];
            testingAccs[trial] = results[1];
        }

        // write results to file
        writeFile(trainingMSE, fileName + "NNTraining" + batchSize);
        writeFile(testingAccs, fileName + "NNTestAccs" + batchSize);
    }

    public static void runSwarm(DataPoint[] points, int[] sizes, String fileName, Config.PsoType type) {
       
        // swarm experiment info
        String swarmType = (type == Config.PsoType.QUANTUM) ? "Q_": "";
        String batchSize = "_" + Config.BATCHSIZE;

        // get training and testing data
        double[][] trainingMSE = new double[Config.TRIALS][];
        double[][] testingAccs = new double[Config.TRIALS][1];

        // set active pso type
        Config.PSO_TYPE = type;

        // for each independent trial
        for (int trial = 0; trial < Config.TRIALS; trial++) {
            System.out.printf("%sRunning %s%s%s Trial: %s%d of %d%s\n", WHITE, GREEN, swarmType + "PSO" + batchSize, WHITE, GREEN, trial + 1, Config.TRIALS, RESET);

            // get this trials trainSet and testSet
            DataPoint[][] trainTest = Driver.splitSet(points, 0.7);

            // conduct the trial
            double[][] results = runSwarmTrial(trainTest[0], trainTest[1],  sizes);

            // record the results
            trainingMSE[trial] = results[0];
            testingAccs[trial] = results[1];
        }
        // write results to file
        writeFile(trainingMSE, fileName + swarmType + "SwarmTraining" + batchSize);
        writeFile(testingAccs, fileName + swarmType + "SwarmTestAccs" + batchSize);
    }


    /**
     * Takes the provided file data, computes a string representation of this data, and defers to an overloaded
     * function writeFile(String) which writes this data to a CSV file
     * @param data
     */
    private static void writeFile(double[][] data, String fileName) {
        // build up CSV string
        StringBuilder sb = new StringBuilder("");
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                sb.append("" + data[row][col]);
                if (col < data[row].length - 1)
                    sb.append(",");
            }
            sb.append("\n");
        }

        // now that the file string is built up, write it to the file
        writeFile(sb.toString(), fileName);
    }

    /**
     * Writes the provided string content to a file
     * @param fileData
     */
    private static void writeFile(String fileData, String fileName) {
        try (PrintWriter writer = new PrintWriter("../output/" + fileName + ".csv")) {
            writer.write(fileData);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } 
    }



}
