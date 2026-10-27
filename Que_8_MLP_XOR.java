public class Que_8_MLP_XOR {
    static double sig(double z) {
        return 1 / (1 + Math.exp(-z));
    }

    public static void main(String[] args) {
        double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        double[] t = { 0, 1, 1, 0 };
        double lr = 0.5;

        // 2 inputs -> 2 hidden -> 1 output (initial weights fixed)
        double[][] w = { { 0.5, -0.4 }, { 0.3, 0.8 } }; // w[input][hidden]
        double[] bh = { 0.1, -0.2 }; // hidden biases
        double[] v = { 0.6, -0.5 }; // hidden -> output
        double bo = 0.2; // output bias
        double[] h = new double[2];

        for (int epoch = 0; epoch < 20000; epoch++) {
            for (int p = 0; p < 4; p++) {
                // forward
                for (int j = 0; j < 2; j++)
                    h[j] = sig(x[p][0] * w[0][j] + x[p][1] * w[1][j] + bh[j]);
                double y = sig(h[0] * v[0] + h[1] * v[1] + bo);

                // backward
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
            for (int j = 0; j < 2; j++)
                h[j] = sig(x[p][0] * w[0][j] + x[p][1] * w[1][j] + bh[j]);
            double y = sig(h[0] * v[0] + h[1] * v[1] + bo);
            System.out.printf("%.0f  %.0f  %.0f      %.3f  %d%n", x[p][0], x[p][1], t[p], y, y >= 0.5 ? 1 : 0);
        }
    }
}