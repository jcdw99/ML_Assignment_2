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


    public static void do3TrivialSwarm(Config.PsoType type) {
        DataPoint[] points = Loader.loadPointsFromFile("../data/3classSimple.csv", 2, 3);
        int classes = 3;
        int[] sizes = {2, 10, classes};
        runSwarm(points, sizes, type);
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
                trialData[writeCount++] =  swarm.gBestEval;
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


    public static void runSwarm(DataPoint[] points, int[] sizes, Config.PsoType type) {
        
        // get training and testing data
        double[][] trainingMSE = new double[Config.TRIALS][];
        double[][] testingAccs = new double[Config.TRIALS][1];

        // set active pso type
        Config.PSO_TYPE = type;

        // for each independent trial
        for (int trial = 0; trial < Config.TRIALS; trial++) {
            System.out.printf("%sRunning Trial: %s%d of %d%s\n", WHITE, GREEN, trial + 1, Config.TRIALS, RESET);

            // get this trials trainSet and testSet
            DataPoint[][] trainTest = Driver.splitSet(points, 0.7);

            // conduct the trial
            double[][] results = runSwarmTrial(trainTest[0], trainTest[1],  sizes);

            // record the results
            trainingMSE[trial] = results[0];
            testingAccs[trial] = results[1];
        }

        String swarmType = (type == Config.PsoType.QUANTUM) ? "Q_": "";
        writeFile(trainingMSE, "3Trivial" + swarmType + "SwarmTraining");
        writeFile(testingAccs, "3Trivial" + swarmType + "SwarmTestAccs");
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
