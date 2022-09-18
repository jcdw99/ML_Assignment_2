import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Loader {
        
    public static DataPoint[] loadPointsFromFile(String filePath, int inputVecSize, int classes) {
        try {
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            ArrayList<DataPoint> list = new ArrayList<DataPoint>();

            while(sc.hasNextLine()) {
                String line = sc.nextLine();

                double[] vals = Arrays.stream(line.split(","))
                        .mapToDouble(Double::parseDouble).toArray();

                double[] inputVec = Arrays.copyOf(vals, vals.length-1);
                double[] outputVec = new double[classes];
                int target = (int) vals[vals.length-1];
                for (int i = 0; i < outputVec.length; i++) {
                    outputVec[i] = (i == target) ? 1.0:0.0;
                }
                list.add(new DataPoint(inputVec, outputVec, target));
            }

            return list.toArray(new DataPoint[0]);

//            while (sc.hasNext()) {
//                double[] inputVec = new double[inputVecSize];
//                for (int i = 0; i < inputVecSize; i++) {
//                    String val = sc.next().replaceAll(" ", "");
//                    inputVec[i] = Double.parseDouble(val);
//                }
//                int targetClass = Integer.parseInt(sc.next().replaceAll(" ", "").replaceAll("\n", ""));
//                double[] outputVector = new double[classes];
//                for (int i = 0; i < outputVector.length; i++) {
//                    outputVector[i] = (i == targetClass) ? 1.0:0.0;
//                }
//                list.add(new DataPoint(inputVec, outputVector, targetClass));
//            }
//            return list.toArray(new DataPoint[list.size()]);
//            System.exit(0);
//            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
