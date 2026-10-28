public class Que_10_MLP_Loan {
    static double[] wa = { 0.5, -0.4, 0.3 };
    static double[] wb = { -0.3, 0.6, 0.4 };
    static double[] bh = { 0.1, -0.1, 0.2 };
    static double[] wo = { 0.4, -0.5, 0.3 };
    static double bo = 0.1;
    static double[] h = new double[3];

    static double sig(double z) {
        return 1 / (1 + Math.exp(-z));
    }

    static double forward(double x1, double x2) {
        double z = bo;
        for (int j = 0; j < 3; j++) {
            h[j] = sig(x1 * wa[j] + x2 * wb[j] + bh[j]);
            z += h[j] * wo[j];
        }
        return sig(z);
    }

    public static void main(String[] args) {
        double[] x1 = { 0, 0, 1, 1 };
        double[] x2 = { 0, 1, 0, 1 };
        double[] t = { 0, 0, 0, 1 };
        double lr = 0.5;

        System.out.println("Loan Approval MLP (2-3-1), Backpropagation, lr = " + lr);
        System.out.println("-----------------------------------------");

        for (int epoch = 1; epoch <= 10000; epoch++) {
            double error = 0;
            for (int p = 0; p < 4; p++) {
                double y = forward(x1[p], x2[p]);
                error += 0.5 * (t[p] - y) * (t[p] - y);
                double dOut = (t[p] - y) * y * (1 - y);
                for (int j = 0; j < 3; j++) {
                    double dHid = dOut * wo[j] * h[j] * (1 - h[j]);
                    wo[j] += lr * dOut * h[j];
                    wa[j] += lr * dHid * x1[p];
                    wb[j] += lr * dHid * x2[p];
                    bh[j] += lr * dHid;
                }
                bo += lr * dOut;
            }
            if (epoch == 1 || epoch % 2000 == 0)
                System.out.printf("Epoch %5d   Error = %.6f%n", epoch, error);
        }

        System.out.println("-----------------------------------------");
        System.out.println("Trained weights:");
        for (int j = 0; j < 3; j++)
            System.out.printf("Hidden %d: w(x1)=%.3f w(x2)=%.3f bias=%.3f -> output weight=%.3f%n",
                    j + 1, wa[j], wb[j], bh[j], wo[j]);
        System.out.printf("Output bias = %.3f%n", bo);

        System.out.println("-----------------------------------------");
        System.out.println("x1 x2  Target  Output   Predicted");
        for (int p = 0; p < 4; p++) {
            double y = forward(x1[p], x2[p]);
            System.out.printf("%.0f  %.0f    %.0f     %.4f     %d%n", x1[p], x2[p], t[p], y, y >= 0.5 ? 1 : 0);
        }

        double y = forward(1, 1);
        System.out.println("-----------------------------------------");
        System.out.printf("Test input (1,1): output = %.4f%n", y);
        System.out.println("Result: " + (y >= 0.5 ? "Approved (1)" : "Rejected (0)"));
    }
}
