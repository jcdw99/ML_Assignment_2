public class DataPoint {

    double[] inputVector;
    double[] expectedOutputVector;
    

    public DataPoint(double[] inputVector, double[] outputVector) {
        this.inputVector = inputVector;
        this.expectedOutputVector = outputVector;
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
