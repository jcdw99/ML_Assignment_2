public class MakeSet {
    public static void main(String[] args) {


        set3();
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


    
}
