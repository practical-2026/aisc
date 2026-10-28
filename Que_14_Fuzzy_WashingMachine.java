public class Que_14_Fuzzy_WashingMachine {
    static String[] types = { "Triangular", "Trapezoidal", "Gaussian" };
    static String[] names = { "Lightly Soiled", "Moderately Soiled", "Heavily Soiled" };
    static double[][] tri = { { -50, 0, 50 }, { 0, 50, 100 }, { 50, 100, 150 } };
    static double[][] trap = { { -10, 0, 20, 40 }, { 20, 40, 60, 80 }, { 60, 80, 100, 110 } };
    static double[][] gauss = { { 0, 20 }, { 50, 15 }, { 100, 20 } };

    // rises from a to b, falls from b to c
    static double triangular(double x, double a, double b, double c) {
        if (x <= a || x >= c)
            return 0;
        if (x <= b)
            return (x - a) / (b - a);
        return (c - x) / (c - b);
    }

    // rises a to b, flat (=1) b to c, falls c to d
    static double trapezoidal(double x, double a, double b, double c, double d) {
        if (x <= a || x >= d)
            return 0;
        if (x < b)
            return (x - a) / (b - a);
        if (x <= c)
            return 1;
        return (d - x) / (d - c);
    }

    // bell curve with centre m and width s
    static double gaussian(double x, double m, double s) {
        return Math.exp(-(x - m) * (x - m) / (2 * s * s));
    }

    static double membership(int type, int i, double x) {
        if (type == 0)
            return triangular(x, tri[i][0], tri[i][1], tri[i][2]);
        if (type == 1)
            return trapezoidal(x, trap[i][0], trap[i][1], trap[i][2], trap[i][3]);
        return gaussian(x, gauss[i][0], gauss[i][1]);
    }

    public static void main(String[] args) {
        double value = 35; // dirt level (%)

        for (int type = 0; type < 3; type++) {
            System.out.println("\n" + types[type] + " memberships at " + value);
            for (int i = 0; i < 3; i++)
                System.out.printf(" %s: %.2f |", names[i], membership(type, i, value));
        }
    }
}