import java.util.*;

public class Que_3_Greedy_BFS {
    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<>();
        graph.put("A", new String[] { "B", "C" });
        graph.put("B", new String[] { "D", "E" });
        graph.put("C", new String[] { "F", "G" });
        graph.put("D", new String[] { "H" });
        graph.put("E", new String[] { "H" });
        graph.put("F", new String[] { "H" });
        graph.put("G", new String[] { "H" });
        graph.put("H", new String[] {});

        Map<String, Integer> h = new HashMap<>();
        h.put("A", 6);
        h.put("B", 4);
        h.put("C", 3);
        h.put("D", 2);
        h.put("E", 3);
        h.put("F", 2);
        h.put("G", 1);
        h.put("H", 0);

        List<String> open = new ArrayList<>();
        Map<String, String> parent = new HashMap<>();
        open.add("A");

        System.out.print("Visited: ");
        while (!open.isEmpty()) {
            String node = open.get(0);
            for (String n : open)
                if (h.get(n) < h.get(node))
                    node = n;
            open.remove(node);
            System.out.print(node + " ");
            if (node.equals("H"))
                break;
            for (String next : graph.get(node)) {
                parent.put(next, node);
                open.add(next);
            }
        }

        List<String> path = new ArrayList<>();
        for (String n = "H"; n != null; n = parent.get(n))
            path.add(0, n);

        System.out.println();
        System.out.println("Path: " + path);
    }
}
