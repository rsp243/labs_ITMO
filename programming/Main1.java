public class Main1 {
    public static void main(String[] args) {
        System.out.println("Задание 1 -------->");
        short[] c = new short[9];
        int j1 = 0;
        for (short i = 23; i >= 7; i--) {
            if (i % 2 != 0) {
                c[j1] = i;
                j1 += 1;
            }
        }
        for (int i = 0; i < 9; i++) {
            System.out.print(c[i] + " ");
        }  
        System.out.println();
        System.out.println("Задание 2 -------->");
        double[] x = new double[16];
        for (int i = 0; i < 16; i++) {
            x[i] = (Math.random() * 14) - 2;
        }
        for (int i = 0; i < 16; i++) {
            System.out.print(x[i] + " ");
        } 
        System.out.println();
        System.out.println("Задание 3 -------->");

        float[][] a = new float [9][16];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 16; j++) {
                if (c[i] == 9) {
                    a[i][j] = (float) Math.sin(Math.log(Math.pow(Math.abs(x[j]) / 2, 2)));
                } else if (c[i] == 7 || c[i] == 15 || c[i] == 17 || c[i] == 23) {
                    a[i][j] = (float) Math.pow(Math.asin(Math.pow(Math.E, -1 * Math.abs(x[j]))), (Math.sin(x[j]) * (Math.asin((x[j] + 4.5) / 13) - 1) / Math.tan(x[j]) + 1) / 3);
                } else {
                    a[i][j] = (float) Math.pow((2 / (Math.pow(Math.pow((x[j] - 1) / 0.25, 3), 2 / (Math.pow(Math.E, x[j]))))) * (Math.pow(Math.E, Math.log(Math.pow((Math.abs(x[j]) + 1) / 2, 2))) - Math.PI), 3);
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.printf("%.2f ", a[i][j]);
            }
            System.out.println();
        }
    }
}