import java.util.*;

public class Que_4_AStar {
    static Map<String, Integer> cost = new HashMap<>();

    static void edge(String a, String b, int c) {
        cost.put(a + b, c);
        cost.put(b + a, c);
    }

    public static void main(String[] args) {
        String[] nodes = { "A", "B", "C", "D", "E", "F", "G", "H" };
        int[] hv = { 5, 4, 3, 3, 3, 2, 1, 0 };

        Map<String, Integer> h = new HashMap<>();
        for (int i = 0; i < 8; i++)
            h.put(nodes[i], hv[i]);

        edge("A", "B", 2);
        edge("A", "C", 3);
        edge("B", "D", 3);
        edge("B", "E", 2);
        edge("C", "F", 2);
        edge("C", "G", 3);
        edge("D", "H", 3);
        edge("E", "H", 4);
        edge("F", "H", 3);
        edge("G", "H", 2);

        Map<String, Integer> g = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        List<String> open = new ArrayList<>();
        g.put("A", 0);
        open.add("A");

        System.out.print("Visited: ");
        while (!open.isEmpty()) {
            String best = open.get(0);
            for (String n : open)
                if (g.get(n) + h.get(n) < g.get(best) + h.get(best))
                    best = n;
            open.remove(best);
            System.out.print(best + " ");
            if (best.equals("H"))
                break;

            for (String n : nodes) {
                if (cost.containsKey(best + n)) {
                    int newG = g.get(best) + cost.get(best + n);
                    if (!g.containsKey(n) || newG < g.get(n)) {
                        g.put(n, newG);
                        parent.put(n, best);
                        if (!open.contains(n))
                            open.add(n);
                    }
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String n = "H"; n != null; n = parent.get(n))
            path.add(0, n);

        System.out.println();
        System.out.println("Path: " + path);
        System.out.println("Total cost: " + g.get("H"));
    }
}
