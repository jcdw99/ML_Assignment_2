public class Main {
    public static void main(String[] args) {
        int[] sizes = {2, 3, 2};
        NeuralNetwork net = new NeuralNetwork(sizes);
        DataPoint[] points = Loader.loadPointsFromFile("../data/simple.csv", 2);
        for (int j = 0; j < 3000000; j++) {
            int batchSize = 20;
            for (int i = 0; i < points.length / batchSize; i++) {
                DataPoint[] batch = getBatch(points, batchSize, i);
                net.BackProp(batch, 0.1);
                System.out.println(net.Cost(points));
    
            }
        }
   
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
