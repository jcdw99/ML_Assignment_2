public class DataPoint {

    double[] inputVector;
    double[] expectedOutputVector;
    int targetClass;
    

    public DataPoint(double[] inputVector, double[] outputVector, int targetClass) {
        this.inputVector = inputVector;
        this.expectedOutputVector = outputVector;
        this.targetClass = targetClass;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) 
            return true;
        if (!(o instanceof DataPoint)) 
            return false;
        
        DataPoint c = (DataPoint) o;
        boolean same = true;
        // compare input vectors
        for (int i = 0; i < this.inputVector.length; i++) {
            same = (c.inputVector[i] == this.inputVector[i]);
            if (!same)
                return false;
        }
        // compare output vectors
        for (int i = 0; i < this.expectedOutputVector.length; i++) {
            same = (c.expectedOutputVector[i] == this.expectedOutputVector[i]);
            if (!same)
                return false;
        }
        return (same == (this.targetClass == c.targetClass));
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
