import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer> answer;

    private static int[] distances;

    public static void main(String[] args) throws IOException {
        answer = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfCity = Integer.parseInt(st.nextToken());

        int numOfRoad = Integer.parseInt(st.nextToken());

        int distance = Integer.parseInt(st.nextToken());

        int startNode = Integer.parseInt(st.nextToken());

        Node[] cities = new Node[numOfCity + 1];

        distances = new int[numOfCity + 1];

        distances[startNode] = 0;

        for (int i = 1; i < numOfCity + 1; i++) {
            cities[i] = new Node(i);
        }

        for (int i = 0; i < numOfRoad; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());

            int to = Integer.parseInt(st.nextToken());

            cities[from].addRoad(cities[to]);
        }
        bfs(cities[startNode], distance);

        int[] arr = answer.stream().mapToInt(i -> i).toArray();

        Arrays.sort(arr);

        if (arr.length == 0) {
            System.out.println("-1");
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }


    }

    private static void bfs(Node start, int distance) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(start);

        while (!queue.isEmpty()) {

            Node next = queue.poll();
            if (distances[next.getNum()] == distance && next.getNum() != start.getNum()) {
                answer.add(next.getNum());
            }

            for (int i = 0; i < next.getRoads().size(); i++) {
                if (distances[next.getRoads().get(i).getNum()] == 0) {
                    queue.add(next.getRoads().get(i));
                    distances[next.getRoads().get(i).getNum()] = distances[next.getNum()] + 1;
                }
            }


        }

    }
}


class Node {
    private int num;

    private ArrayList<Node> roads;

    Node(int num) {
        this.num = num;

        roads = new ArrayList<>();
    }

    public void addRoad(Node node) {
        roads.add(node);
    }

    public int getNum() {
        return num;
    }

    public ArrayList<Node> getRoads() {
        return roads;
    }
}