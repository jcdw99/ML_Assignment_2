public class MakeSet {
    public static void main(String[] args) {


        set2();
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


    
}
