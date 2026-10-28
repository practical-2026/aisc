public class Que_15_Fuzzy_RoomTemperature {
    static String[] types = { "Triangular", "Trapezoidal", "Gaussian" };
    static String[] names = { "Cold", "Comfortable", "Hot" };
    static double[][] tri = { { -10, 0, 20 }, { 10, 22, 34 }, { 24, 40, 56 } };
    static double[][] trap = { { -10, 0, 10, 20 }, { 15, 20, 26, 31 }, { 26, 32, 45, 50 } };
    static double[][] gauss = { { 5, 6 }, { 22, 4 }, { 38, 6 } };

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
        double value = 18; // temperature (°C)

        for (int type = 0; type < 3; type++) {
            System.out.println("\n" + types[type] + " memberships at " + value);
            for (int i = 0; i < 3; i++)
                System.out.printf(" %s: %.2f |", names[i], membership(type, i, value));
        }
    }
}