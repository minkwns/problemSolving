import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        HashMap<String, Node> nodeList = new HashMap<>();

        HashMap<String, Integer> minDists = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfVertex = Integer.parseInt(st.nextToken());
        int numOfEdge = Integer.parseInt(st.nextToken());
        String startPoint = br.readLine();


         for (int i = 0; i < numOfVertex; i++) {
             nodeList.put(String.valueOf(i + 1), new Node(String.valueOf(i + 1)));
             minDists.put(String.valueOf(i + 1), Integer.MAX_VALUE);
         }



         for (int i = 0; i < numOfEdge; i++) {

             st = new StringTokenizer(br.readLine());

             String first = st.nextToken();

             String second = st.nextToken();

             int weight = Integer.parseInt(st.nextToken());

             Node firstNode;

             Node secondNode;

             firstNode = nodeList.get(first);

             secondNode = nodeList.get(second);

             if (firstNode.getRoads().containsKey(secondNode)) {
                 if (weight < firstNode.getRoads().get(secondNode)) {
                     firstNode.getRoads().replace(secondNode, weight);
                 }
             } else {
                 firstNode.addRoad(secondNode, weight);
             }
         }
         HashMap<String, Integer> answer = run(nodeList, startPoint, minDists);
         for (int i = 0;i < answer.size(); i++) {
             if (answer.get(String.valueOf(i + 1)) == Integer.MAX_VALUE) {
                 System.out.println("INF");
                 continue;
             }
             System.out.println(answer.get(String.valueOf(i + 1)));
         }

    }

    public static HashMap<String, Integer> run(final HashMap<String, Node> nodes, final String from, final HashMap<String, Integer> minDists) {

        minDists.put(from, 0);

        PriorityQueue<Candidate> open = new PriorityQueue<Candidate>();

        Node s = nodes.get(from);

        Candidate candidate = new Candidate(s, 0);

        open.add(candidate);

        while (!open.isEmpty()) {
            candidate = open.poll();

            Node n = candidate.getNode();
            String nodeName = n.getName();

            int minDist = minDists.get(nodeName);
            int dist = candidate.getDistance();

            if (minDist < dist) {
                continue;
            }

            Map<Node, Integer> roads = n.getRoads();

            for (var e : roads.entrySet()) {
                Node next = e.getKey();

                int weight = e.getValue();

                int newDist = minDist + weight;

                String nextName = next.getName();
                int nextMinDist = minDists.get(nextName);

                if (newDist >= nextMinDist) {
                    continue;
                }

                minDists.put(nextName, newDist);

                Candidate newCandidate = new Candidate(next, newDist);

                open.add(newCandidate);
            }
        }

        return minDists;
    }
}

class Candidate implements Comparable<Candidate> {

    private final Node node;
    private final int distance;

    public Candidate(Node node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public Node getNode() {
        return node;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Candidate o) {
        return this.getDistance() - o.getDistance();
    }
}


class Node {
    private final String name;
    private final HashMap<Node, Integer> roads = new HashMap<>();

    public Node(final String name) {
        this.name = name;
    }

    public void addRoad(final Node to, final int dist) {
        this.roads.put(to, dist);
    }

    public int getDistance(final Node to) {
        return this.roads.get(to);
    }

    public String getName() {
        return name;
    }

    public HashMap<Node, Integer> getRoads() {
        return roads;
    }
}