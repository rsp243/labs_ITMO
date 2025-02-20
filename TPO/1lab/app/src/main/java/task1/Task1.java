package task1;

import static task1.Taylor.expandArctgFunc;

public class Task1 {
    public static void solveTask() {
        int n = 30;
        for (double x = -1; x <= 1; x += 0.05) {
            System.err.printf("arctg(x) = arctg(%.8f) = %.8f\n", x, expandArctgFunc(x, n));
        }
    }
}
