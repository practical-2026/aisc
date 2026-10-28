public class Que_11_MLP_Spam {
    static double[][] w = { { 0.5, -0.4 }, { 0.3, 0.8 } };
    static double[] bh = { 0.1, -0.2 };
    static double[] v = { 0.6, -0.5 };
    static double bo = 0.2;
    static double[] h = new double[2];

    static double sig(double z) {
        return 1 / (1 + Math.exp(-z));
    }

    static double forward(double x1, double x2) {
        for (int j = 0; j < 2; j++)
            h[j] = sig(x1 * w[0][j] + x2 * w[1][j] + bh[j]);
        return sig(h[0] * v[0] + h[1] * v[1] + bo);
    }

    public static void main(String[] args) {
        // E1..E4: suspicious keywords, suspicious links -> Spam(1) / Not Spam(0)
        double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        double[] t = { 0, 0, 0, 1 };
        double lr = 0.5;

        for (int epoch = 0; epoch < 10000; epoch++) {
            for (int p = 0; p < 4; p++) {
                double y = forward(x[p][0], x[p][1]);
                double dOut = (t[p] - y) * y * (1 - y);
                for (int j = 0; j < 2; j++) {
                    double dH = dOut * v[j] * h[j] * (1 - h[j]);
                    v[j] += lr * dOut * h[j];
                    w[0][j] += lr * dH * x[p][0];
                    w[1][j] += lr * dH * x[p][1];
                    bh[j] += lr * dH;
                }
                bo += lr * dOut;
            }
        }

        System.out.println("x1 x2 target output class");
        for (int p = 0; p < 4; p++) {
            double y = forward(x[p][0], x[p][1]);
            System.out.printf("%.0f  %.0f  %.0f      %.3f  %d%n", x[p][0], x[p][1], t[p], y, y >= 0.5 ? 1 : 0);
        }

        double y = forward(1, 0);
        System.out.printf("Input (1,0): output=%.3f -> %s%n", y, y >= 0.5 ? "Spam (1)" : "Not Spam (0)");
    }
}