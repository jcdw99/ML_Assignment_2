
import java.awt.Color;

public class Visualize2D {

    int canvasWidth;
    int canvasHeight;
    double lb;
    double ub;
    int pixSize;
    double blackness;

    public Visualize2D(int width, int height, double lb, double ub) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        this.pixSize = canvasWidth / 100;
        this.lb = lb;
        this.ub = ub;
        this.blackness = Config.BLACKNESS;
        if (this.pixSize % 2 != 0)
            this.pixSize += 1;
        initCanvas();
    }

    public Visualize2D(int width, int height, int pixSize, double lb, double ub, double blackness) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        this.lb = lb;
        this.ub = ub;
        if (pixSize % 2 != 0)
            pixSize += 1;
        this.pixSize = pixSize;
        this.blackness = blackness;
        initCanvas();
    }

    public void initCanvas() {
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, this.canvasWidth);
        StdDraw.setYscale(0, this.canvasHeight);
        StdDraw.filledCircle(0, 0, 30);
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.filledRectangle(this.canvasWidth/2, this.canvasHeight/2, this.canvasWidth / 2, this.canvasHeight / 2);
        StdDraw.enableDoubleBuffering();
    }

    public void draw(NeuralNetwork net, int classes) {

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(this.canvasWidth/2, this.canvasHeight/2, this.canvasWidth / 2, this.canvasHeight / 2);

        int xPixels = this.canvasHeight / this.pixSize;
        int yPixels = this.canvasWidth / this.pixSize;
        
        double intervalWidth = ub - lb;
        double stepSizeX = intervalWidth / xPixels;
        double stepSizeY = intervalWidth / yPixels;
        double floatX = lb;
        double floatY = lb;

        Color[] colors = {new Color(205, 34, 44), new Color(34, 205, 44), new Color(205, 205, 55), new Color(175, 125, 30) ,new Color(205, 0, 205), new Color(78, 173, 218), new Color(55,55,55), new Color(200, 125, 125)};

        for (int i = 0; i < xPixels; i ++) {
            for (int j = 0; j < yPixels; j ++) {
                double[] input = {floatY, floatX};
                int targetClass = net.Classify(input);
                StdDraw.setPenColor(colors[targetClass]);
                StdDraw.filledSquare(i * pixSize + ((pixSize / 2)), j * pixSize + ((pixSize / 2)), (pixSize/2) * blackness);
                floatX += stepSizeX;
            }
            floatX = lb;
            floatY += stepSizeY;
        }
    }


    public void scatterPoints(DataPoint[] points, int dotRadius) {
        StdDraw.setPenColor(StdDraw.BLACK);
        double interval = ub - lb;
        Color[] colors = {StdDraw.RED, StdDraw.GREEN, StdDraw.YELLOW, StdDraw.ORANGE, StdDraw.MAGENTA, StdDraw.BOOK_LIGHT_BLUE, StdDraw.GRAY, StdDraw.PINK};
        for (int i = 0; i < points.length; i++) {
            double[] xy = points[i].inputVector;
            double xProp = (xy[0] - lb) / interval;
            double yProp = (xy[1] - lb) / interval;
            StdDraw.setPenColor(colors[points[i].targetClass]);
            StdDraw.filledCircle((int)(canvasWidth * xProp), (int)(canvasHeight * yProp), dotRadius);
        }
    }

    public void addError(String error, String epoch, String acc) {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle((int) (.07 * this.canvasWidth), (int) (.965 * this.canvasHeight), 45, 15);
        StdDraw.filledRectangle((int) (.07 * this.canvasWidth), (int) (.915 * this.canvasHeight), 45, 15);
        StdDraw.filledRectangle((int) (.07 * this.canvasWidth), (int) (.865 * this.canvasHeight), 45, 15);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text((int) (.07 * this.canvasWidth), (int) (.965 * this.canvasHeight), error.substring(0, Math.min(6, error.length())));
        StdDraw.text((int) (.07 * this.canvasWidth), (int) (.915 * this.canvasHeight), epoch);
        StdDraw.text((int) (.07 * this.canvasWidth), (int) (.865 * this.canvasHeight), acc);
    }

}
