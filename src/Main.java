public class Main {
    public static void main(String[] args) {

        int[] batchSizes = {10, 50, 100, 200, 500};
        for (int size: batchSizes) {
            Config.BATCHSIZE = size;
            Evaluator.do3TrivialSwarm(Config.PsoType.QUANTUM);
            Evaluator.doSwirlSwarm(Config.PsoType.QUANTUM);
            Evaluator.doPinWheelSwarm(Config.PsoType.QUANTUM);
        }
    }   
}
