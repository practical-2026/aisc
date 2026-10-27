public class Que_6_SLP_OR {
    public static void main(String[] args) {
        int[][] x = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] t = {0, 1, 1, 1};
        double w1 = 0.25, w2 = -0.25, b = -0.25, lr = 0.5;
        int errors = 1, epoch = 0;

        while (errors > 0) {
            errors = 0;
            epoch++;
            for (int i = 0; i < 4; i++) {
                double net = w1 * x[i][0] + w2 * x[i][1] + b;
                int y = net >= 0 ? 1 : 0;
                int e = t[i] - y;
                w1 += lr * e * x[i][0];
                w2 += lr * e * x[i][1];
                b += lr * e;
                if (e != 0) errors++;
            }
        }

        System.out.println("Epochs: " + epoch);
        System.out.println("Final: w1=" + w1 + " w2=" + w2 + " b=" + b);
        System.out.println("x1 x2 target predicted");
        for (int i = 0; i < 4; i++) {
            double net = w1 * x[i][0] + w2 * x[i][1] + b;
            System.out.println(x[i][0] + "  " + x[i][1] + "  " + t[i] + "      " + (net >= 0 ? 1 : 0));
        }
    }
}