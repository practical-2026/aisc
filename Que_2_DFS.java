import java.util.*;

public class Que_2_DFS {
    static Map<String, List<String>> graph = new HashMap<>();
    static List<String> path = new ArrayList<>();

    static boolean dfs(String node) {
        System.out.print(node + " ");
        path.add(node);
        if (node.equals("G"))
            return true;
        for (String next : graph.get(node))
            if (dfs(next))
                return true;
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D", "E"));
        graph.put("C", Arrays.asList("F", "G"));
        graph.put("D", new ArrayList<>());
        graph.put("E", Arrays.asList("H"));
        graph.put("F", new ArrayList<>());
        graph.put("G", new ArrayList<>());
        graph.put("H", new ArrayList<>());

        System.out.print("Visited: ");
        dfs("A");
        System.out.println();
        System.out.println("Path: " + path);
    }
}