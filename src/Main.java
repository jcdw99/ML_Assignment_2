public class Main {
    public static void main(String[] args) {
        int[] sizes = {2, 5, 5, 5, 2};
        Visualize2D v = new Visualize2D(800, 800, 2, 0, 2);
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile("../data/circlesect.csv", 2);
        for (int j = 0; j < 150000; j++) {
            int batchSize = 30;
            for (int i = 0; i < points.length / batchSize; i++) {
                DataPoint[] batch = getRandomBatch(points, batchSize);
                net.BackProp(batch, 0.1);
            }
            if (j % 3000 == 0) {
                v.draw(net);
                v.scatterPoints(points, 5);
                v.addError(net.Cost(points) + "");
                StdDraw.pause(500);
                StdDraw.clear();
            }

        }
        System.out.println("done and trained");
        Score(net, points);
        net.saveNet("../nets/BigNet2Circles.net");

        // NeuralNetwork net = NeuralNetwork.fromSave("../nets/Net1.net");
        // DataPoint[] points = Loader.loadPointsFromFile("../data/simple.csv", 2);
        // Visualize2D v = new Visualize2D(800, 800, 4, 0, 2);
        // v.draw(net);
        // v.scatterPoints(points, 5);
        // Score(net, points);
    }

    public static void Score(NeuralNetwork net, DataPoint[] points) {
        int correctCount = 0;
        for (int i = 0; i < points.length; i++) {
            int classification = net.Classify(points[i].inputVector);
            if (points[i].expectedOutputVector[classification] == 1.0)
                correctCount+=1;
        }
        System.out.println(((correctCount + 0.0) / points.length) * 100 + "  % accuracy");
    }
    public static DataPoint[] getRandomBatch(DataPoint[] points, int batchSize) {
        DataPoint[] batch = new DataPoint[batchSize];
        for (int i = 0; i < batch.length; i++) {
            batch[i] = points[(int)(Math.random() * points.length)];
        }
        return batch;
    }
    public static DataPoint[] getBatch(DataPoint[] points, int batchSize, int batchesDone) {
        int starting = Math.max((batchesDone * batchSize) - 1, 0);
        DataPoint[] batch = new DataPoint[batchSize];
        for (int i = starting; i < starting + batchSize; i ++) {
            batch[i - starting] = points[i];
        }
        return batch;
    }
}
