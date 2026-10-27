public class Que_7_SLP_AND_learningRates {
    public static void main(String[] args) {
        int[] x1 = { 0, 0, 1, 1 };
        int[] x2 = { 0, 1, 0, 1 };
        int[] t = { 0, 0, 0, 1 };
        double[] rates = { 0.1, 0.3, 0.5 };

        for (double lr : rates) {
            double w1 = 0.25, w2 = 0.25, b = -0.25; // same start for every rate
            int epoch = 0, errors = 1;

            while (errors > 0) {
                errors = 0;
                epoch++;
                for (int i = 0; i < 4; i++) {
                    int y = (w1 * x1[i] + w2 * x2[i] + b >= 0) ? 1 : 0;
                    int e = t[i] - y;
                    w1 += lr * e * x1[i];
                    w2 += lr * e * x2[i];
                    b += lr * e;
                    if (e != 0)
                        errors++;
                }
            }

            System.out.printf("lr=%.1f  epochs=%d  w1=%.2f w2=%.2f b=%.2f%n", lr, epoch, w1, w2, b);
        }
    }
}
