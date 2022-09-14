import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class Loader {
    
    public static DataPoint[] loadPointsFromFile(String filePath, int inputVecSize) {
        try {
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            ArrayList<DataPoint> list = new ArrayList<DataPoint>();
            while (sc.hasNext()) {
                double[] inputVec = new double[inputVecSize];
                for (int i = 0; i < inputVecSize; i++) {
                    String val = sc.next().replaceAll(" ", "");
                    inputVec[i] = Double.parseDouble(val);
                }
                int targetClass = Integer.parseInt(sc.next().replaceAll(" ", ""));
                double[] outputVector = new double[inputVecSize];
                for (int i = 0; i < outputVector.length; i++) {
                    outputVector[i] = (i == targetClass) ? 1.0:0.0;
                }
                list.add(new DataPoint(inputVec, outputVector));
            }
            return list.toArray(new DataPoint[list.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
