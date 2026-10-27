import java.util.*;

public class Que_1_BFS {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "D", "E"));
        graph.put("B", Arrays.asList("A", "C", "E"));
        graph.put("C", Arrays.asList("B", "E", "F", "G"));
        graph.put("D", Arrays.asList("A", "E"));
        graph.put("E", Arrays.asList("A", "B", "C", "D", "F"));
        graph.put("F", Arrays.asList("C", "E", "G"));
        graph.put("G", Arrays.asList("C", "F"));

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add("A");
        visited.add("A");

        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node.equals("G"))
                break;
            for (String next : graph.get(node)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, node);
                    queue.add(next);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String n = "G"; n != null; n = parent.get(n))
            path.add(0, n);

        System.out.println("Path: " + path);
    }
}