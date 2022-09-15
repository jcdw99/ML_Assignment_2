public class Visualize2D {

    int canvasWidth;
    int canvasHeight;
    double lb;
    double ub;
    // pixels are squares, consider canvasWidth and canvasHeight when deciding
    int pixSize;

    public Visualize2D(int width, int height, double lb, double ub) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        this.pixSize = canvasWidth / 100;
        this.lb = lb;
        this.ub = ub;
        if (this.pixSize % 2 != 0)
            this.pixSize += 1;
        initCanvas();
    }

    public Visualize2D(int width, int height, int pixSize, double lb, double ub) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        this.lb = lb;
        this.ub = ub;
        if (pixSize % 2 != 0)
            pixSize += 1;
        this.pixSize = pixSize;
        initCanvas();
    }

    public void initCanvas() {
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, this.canvasWidth);
        StdDraw.setYscale(0, this.canvasHeight);
        StdDraw.filledCircle(0, 0, 30);
        StdDraw.enableDoubleBuffering();
    }


    public void draw(NeuralNetwork net) {
        int xPixels = this.canvasHeight / this.pixSize;
        int yPixels = this.canvasWidth / this.pixSize;
        double intervalWidth = ub - lb;
        double stepSizeX = intervalWidth / xPixels;
        double stepSizeY = intervalWidth / yPixels;
        double floatX = lb;
        double floatY = lb;
        for (int i = 0; i < xPixels; i ++) {
            for (int j = 0; j < yPixels; j ++) {
                double[] input = {floatY, floatX};
                if (net.Classify(input) == 0)
                    StdDraw.setPenColor(144, 238, 144);
                else
                    StdDraw.setPenColor(205, 92, 92);
                StdDraw.filledSquare(i * pixSize + pixSize / 2, j * pixSize + pixSize/2, pixSize/2);
                floatX += stepSizeX;
            }
            floatX = lb;
            floatY += stepSizeY;

            
        }
        StdDraw.disableDoubleBuffering();
        StdDraw.show();
    }

    public void scatterPoints(double[] x, double[] y, boolean type, int dotRadius) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        double interval = ub - lb;
        for (int i = 0; i < x.length; i++) {
            double xObs = x[i];
            double yObs = y[i];
            double xProp = (xObs - lb) / interval;
            double yProp = (yObs - lb) / interval;
            StdDraw.filledCircle((int)(canvasWidth * xProp), (int)(canvasHeight * yProp), 3);
        }
        StdDraw.show();
    }

    public void scatterPoints(DataPoint[] points, int dotRadius) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        double interval = ub - lb;
        for (int i = 0; i < points.length; i++) {
            double[] xy = points[i].inputVector;
            double xProp = (xy[0] - lb) / interval;
            double yProp = (xy[1] - lb) / interval;
            if (points[i].expectedOutputVector[1] == 1)
                StdDraw.setPenColor(StdDraw.RED);
            else 
                StdDraw.setPenColor(23,125,23);
            StdDraw.filledCircle((int)(canvasWidth * xProp), (int)(canvasHeight * yProp), dotRadius);
        }
        StdDraw.show();
    }

    public void addError(String error) {
        StdDraw.enableDoubleBuffering();
        StdDraw.text((int) .1 * this.canvasWidth, (int) .9 * this.canvasHeight, error);
    }

}
