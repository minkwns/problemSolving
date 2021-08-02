import java.util.*;

public class Solution {

    static HashMap<Integer, Node> isVisitedBfs;

    static HashMap<Integer, Node> isVisitedDfs;

    Solution() {
    }

    public static void bfs(Node graph) {
        isVisitedBfs.put(graph.getData(), graph);

        Queue<Node> queue = new LinkedList<>();

        queue.add(graph);

        while(!queue.isEmpty()) {
            Node next = queue.poll();
            System.out.print(next.getData() + " ");

            for (int i = 0; i < next.getNeighbors().size(); i++) {
                if (!isVisitedBfs.containsKey(next.getNeighbors().get(i).getData())) {
                    isVisitedBfs.put(next.getNeighbors().get(i).getData(), next.getNeighbors().get(i));
                    queue.add(next.getNeighbors().get(i));
                }
            }
        }


    }

    public static void dfs(Node graph) {
        isVisitedDfs.put(graph.getData(), graph);

        System.out.print(graph.getData() + " ");
        for (int i = 0; i < graph.getNeighbors().size(); i++) {
            if (!isVisitedDfs.containsKey(graph.getNeighbors().get(i).getData())) {
                isVisitedDfs.put(graph.getNeighbors().get(i).getData(), graph.getNeighbors().get(i));
                dfs(graph.getNeighbors().get(i));
            }
        }

    }


    public static void main(String[] args) {
        isVisitedBfs = new HashMap<>();

        isVisitedDfs = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();

        HashMap<Integer, Node> nodeList = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();

            Node firstNode;

            Node secondNode;

            if (!nodeList.containsKey(first)) {
                firstNode = new Node(first);
                nodeList.put(first, firstNode);
            } else {
                firstNode = nodeList.get(first);
            }

            if (!nodeList.containsKey(second)) {
                secondNode = new Node(second);
                nodeList.put(second, secondNode);
            } else {
                secondNode = nodeList.get(second);
            }

            putNode(firstNode, secondNode);
        }

        for (Map.Entry<Integer, Node> entry : nodeList.entrySet()) {
            if (entry.getValue().getNeighbors().size() != 0) {
                Collections.sort(entry.getValue().getNeighbors());
            }
            // Do things with the list
        }

        if (!nodeList.containsKey(start)) {
            System.out.println(start);
            System.out.println(start);
            return;
        }

        dfs(nodeList.get(start));
        System.out.println();
        bfs(nodeList.get(start));

    }

    public static void putNode(Node node1, Node node2) {
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }
}


class Node implements  Comparable<Node> {
    private final int data;
    private ArrayList<Node> neighbors = new ArrayList<>(1000);

    Node(int data) {
        this.data = data;
    }


    public int getData() {
        return data;
    }

    public void addNeighbor(final Node node) {
        this.neighbors.add(node);
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    @Override
    public int compareTo(Node o) {
        return this.getData() - o.getData();
    }
}
