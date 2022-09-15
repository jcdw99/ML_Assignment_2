
public class MakeSet {
    public static void main(String[] args) {


        multiSet1();
    }

    public static double[] getDub(double val1, double val2) {
        double[] set = {val1, val2};
        return set;
    }

    public static void multiSet1() {
        int counter = 0;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 1000; i++) {
                double x = Math.random() * 7;
                double y = Math.random() * 7;
                int target;
                if (inRect(getDub(0.0, 1.0), getDub(1.0, 0.0), x, y))
                    target = 0;
                else if (inRect(getDub(1.0, 2.0), getDub(2.0, 1.0), x, y))
                    target = 1;
                else if (inRect(getDub(2.0, 3.0), getDub(3.0, 2.0), x, y))
                    target = 2;
                else if (inRect(getDub(3.0, 4.0), getDub(4.0, 3.0), x, y))
                    target = 3;                
                else if (inRect(getDub(4.0, 5.0), getDub(5.0, 4.0), x, y))
                    target = 4;
                else if (inRect(getDub(5.0, 6.0), getDub(6.0, 5.0), x, y))
                    target = 5;  
                else target = 6;
                counter ++;
                System.out.print(x + ", " + y + ", " + target);
                if (counter < 7000)
                    System.out.print(",\n");
            }
        }


    }

    public static void set1() {
        for (int j = 0; j < 400; j++) {
            double point1 = (Math.random() + 1);
            double point2 = (Math.random() + 1);
            System.out.print(point1 + ", " + point2 + ", " + ((point1 + (point2 * point2) < 3) ? 1:0));
            if (j < 399)
                System.out.print(",\n");
        }
    }

    public static void set2() {
        for (int j = 0; j < 400; j++) {
            double point1 = (Math.random() * 2);
            double point2 = (Math.random() * 2);
            System.out.print(point1 + ", " + point2 + ", " + ((Math.sqrt((point2 - 1)*(point2 - 1) + (point1 - 1)*(point1 - 1)) < .75) ? 1:0));
            if (j < 399)
                System.out.print(",\n");
        }    
    }

    public static void set3() {
        for (int j = 0; j < 400; j++) {
            double point1 = (Math.random() * 2);
            double point2 = (Math.random() * 2);
            boolean inside1 = (Math.sqrt((point2 - .7)*(point2 - .7) + (point1 - .7)*(point1 - .7)) < .3);
            boolean inside2 = (Math.sqrt((point2 - 1.3)*(point2 - 1.3) + (point1 - 1.3)*(point1 - 1.3)) < .3);
            
            System.out.print(point1 + ", " + point2 + ", " + ((inside1 ^ inside2) ? 1:0));
            if (j < 399)
                System.out.print(",\n");
        }    
    }

    public static void set4() {
        double[] targetsX = {1.55 , 1.15,  .66   , .75, .5 , .75, 1  , 1.25, 1.5, 1.25, 1, .75, .5};
        double[] targetsY = {1.25, 1.5 , 1.25, 1  , .75, .5 , .75, 1   , .5 , 1.25, 1, 1.25, 1.5};
        for (int j = 0; j < 1500; j++) {
            double point1 = (Math.random() * 2);
            double point2 = (Math.random() * 2);
            boolean close1 = false;
            for (int i = 0; i < targetsX.length; i++) {
                close1 = (Math.sqrt((point2 - targetsX[i])*(point2 - targetsX[i]) + (point1 - targetsY[i])*(point1 - targetsY[i])) < .31);
                if (close1)
                    break;
            }

            boolean inside = (Math.sqrt((point2 - 1)*(point2 - 1) + (point1 - 1)*(point1 - 1)) < .3);
            if (inside)
                close1 = false;
            boolean inside2 = (Math.sqrt((point2 - 1)*(point2 - 1) + (point1 - 1)*(point1 - 1)) < .15);
            if (inside2)
                close1 = true;
            System.out.print(point1 + ", " + point2 + ", " + ((close1) ? 1:0));
            if (j < 1499)
                System.out.print(",\n");
        }    
    }

    public static boolean inRect(double[] topleft, double[] bottomright, double x, double y) {
        return (x > topleft[0] && x < bottomright[0] && y < topleft[1] && y > bottomright[1]);
    }

    public static void set5() {

        double[] pointsX = new double[1200];
        double[] pointsY = new double[1200];

        for (int j = 0; j < 1200; j++) {
            pointsX[j] = Math.random() * 2;
            pointsY[j] = Math.random() * 2;
        }    

        double[][] rects = { {.3,.6}, {1.7, .2}, {1.3, 0.8}, {1.7, 0.6}, {.3, 1.2}, {1.7, 0.8}, {.3, 1.7}, {0.7, 1.2}, {.3, 1.7}, {1.7, 1.4}};

        for (int i = 0; i < pointsX.length; i++) {
            boolean rect1 = inRect(rects[0], rects[1], pointsX[i], pointsY[i]);
            boolean rect2 = inRect(rects[2], rects[3], pointsX[i], pointsY[i]);
            boolean rect3 = inRect(rects[4], rects[5], pointsX[i], pointsY[i]);
            boolean rect4 = inRect(rects[6], rects[7], pointsX[i], pointsY[i]);
            boolean rect5 = inRect(rects[8], rects[9], pointsX[i], pointsY[i]);

            boolean all = rect1 || rect2 || rect3 || rect4 || rect5;
            System.out.print((pointsX[i]) + ", " + (pointsY[i]) + ", " + (all ? 1:0));
    
            if (i < 1199)
                System.out.print(",\n");
        }
    
    }


    
}
