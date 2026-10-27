public class Que_9_MLP_PassFail {
    static double sig(double z) {
        return 1 / (1 + Math.exp(-z));
    }

    public static void main(String[] args) {
        // S1..S4: attendance, assignment -> Pass(1) / Fail(0)
        double[][] x = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        double[] t = { 0, 0, 1, 1 };
        double lr = 0.5;

        double[][] w = { { 0.5, -0.4 }, { 0.3, 0.8 } };
        double[] bh = { 0.1, -0.2 };
        double[] v = { 0.6, -0.5 }; 
        double bo = 0.2; 
        double[] h = new double[2];

        for (int epoch = 0; epoch < 10000; epoch++) {
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

        double[][] test = { { 1, 0 }, { 0, 1 } };
        for (int p = 0; p < 2; p++) {
            for (int j = 0; j < 2; j++)
                h[j] = sig(test[p][0] * w[0][j] + test[p][1] * w[1][j] + bh[j]);
            double y = sig(h[0] * v[0] + h[1] * v[1] + bo);
            System.out.printf("Input (%.0f,%.0f): output=%.3f -> %s%n", test[p][0], test[p][1], y,
                    y >= 0.5 ? "Pass (1)" : "Fail (0)");
        }
    }
}