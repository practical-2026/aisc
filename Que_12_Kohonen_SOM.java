public class Que_12_Kohonen_SOM {
    // winner = neuron closest to the point
    static int winner(double[] p, double[][] w) {
        int best = 0;
        for (int i = 1; i < w.length; i++) {
            double d = Math.pow(p[0] - w[i][0], 2) + Math.pow(p[1] - w[i][1], 2);
            double dBest = Math.pow(p[0] - w[best][0], 2) + Math.pow(p[1] - w[best][1], 2);
            if (d < dBest)
                best = i;
        }
        return best;
    }

    public static void main(String[] args) {
        // 6 input points: 3 near (1,1) and 3 near (8,8)
        double[][] x = { { 1, 1 }, { 1, 2 }, { 2, 1 }, { 8, 8 }, { 8, 9 }, { 9, 8 } };
        double[][] w = { { 0, 0 }, { 10, 10 } }; // 2 neurons = 2 clusters
        double lr = 0.5;

        for (int epoch = 0; epoch < 20; epoch++) {
            for (double[] p : x) {
                int win = winner(p, w); // only the winner is updated
                w[win][0] += lr * (p[0] - w[win][0]);
                w[win][1] += lr * (p[1] - w[win][1]);
            }
            lr = lr * 0.9; // learning rate decreases
        }

        for (int c = 0; c < 2; c++) {
            System.out.print("Cluster " + c + ":");
            for (double[] p : x)
                if (winner(p, w) == c)
                    System.out.print(" (" + (int) p[0] + "," + (int) p[1] + ")");
            System.out.printf("   weight = [%.2f %.2f]%n", w[c][0], w[c][1]);
        }
    }
}