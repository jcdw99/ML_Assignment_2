public class DataPoint {

    double[] inputVector;
    double[] expectedOutputVector;
    int targetClass;
    

    public DataPoint(double[] inputVector, double[] outputVector, int targetClass) {
        this.inputVector = inputVector;
        this.expectedOutputVector = outputVector;
        this.targetClass = targetClass;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < expectedOutputVector.length; i++) {
            s += inputVector[i];
            if (i < expectedOutputVector.length - 1)
                s += ", ";
        }
        return s;
    }
}
