import java.util.*;

public class Que_13_Hopfield {
    public static void main(String[] args) {
        int n = 3;
        int[] stored = { 1, -1, 1 }; // stored binary (bipolar) pattern
        int[][] w = new int[n][n];

        // Hebbian learning: w[i][j] = p[i] * p[j], no self connection
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j)
                    w[i][j] = stored[i] * stored[j];

        System.out.println("Weight matrix:");
        for (int[] row : w)
            System.out.println(Arrays.toString(row));

        // noisy version of the stored pattern (first bit flipped)
        int[] x = { -1, -1, 1 };
        System.out.println("Stored pattern:   " + Arrays.toString(stored));
        System.out.println("Noisy input:      " + Arrays.toString(x));

        // recall: update every neuron until nothing changes
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < n; i++) {
                int net = 0;
                for (int j = 0; j < n; j++)
                    net += w[i][j] * x[j];
                int y = x[i];
                if (net > 0)
                    y = 1;
                if (net < 0)
                    y = -1;
                if (y != x[i]) {
                    x[i] = y;
                    changed = true;
                }
            }
        }

        System.out.println("Recalled pattern: " + Arrays.toString(x));
        System.out.println("Recalled correctly: " + Arrays.equals(x, stored));
    }
}