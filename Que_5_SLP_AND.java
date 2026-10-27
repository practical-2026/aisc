public class Que_5_SLP_AND {
    static double w1 = 0.25, w2 = 0.25, b = -0.25;

    static int predict(int a, int c) {
        return (w1 * a + w2 * c + b >= 0) ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] x1 = {0, 0, 1, 1};
        int[] x2 = {0, 1, 0, 1};
        int[] t  = {0, 0, 0, 1};
        double lr = 0.5;

        for (int epoch = 1; epoch <= 100; epoch++) {
            int errors = 0;
            for (int i = 0; i < 4; i++) {
                int e = t[i] - predict(x1[i], x2[i]);
                w1 += lr * e * x1[i];
                w2 += lr * e * x2[i];
                b  += lr * e;
                if (e != 0) errors++;
            }
            System.out.printf("Epoch %d: errors=%d w1=%.2f w2=%.2f b=%.2f%n", epoch, errors, w1, w2, b);
            if (errors == 0) break;
        }

        System.out.println("Final: w1=" + w1 + " w2=" + w2 + " b=" + b);
        for (int i = 0; i < 4; i++)
            System.out.println(x1[i] + " AND " + x2[i] + " = " + predict(x1[i], x2[i]));
    }
}
