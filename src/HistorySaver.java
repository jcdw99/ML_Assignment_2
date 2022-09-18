import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HistorySaver {

    static void saveHistories(String path, double[][] histories, int[] batchSizes) {
        try {
            File file = new File(path);
            file.getParentFile().mkdir();
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < histories.length; i++) {
                double[] history = histories[i];
                int batchSize = batchSizes[i];
                writer.write("" + batchSize);
                for (int j = 0; j < history.length; j++) {
                    writer.write("," + history[j]);
                }
                if (i != histories.length-1) {
                    writer.write("\n");
                }
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
