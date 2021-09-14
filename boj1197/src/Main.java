import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;

    static int numVertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numVertex = Integer.parseInt(st.nextToken());

        int numEdge = Integer.parseInt(st.nextToken());

        parents = new int[numVertex + 1];


        Edge[] edgeArr = new Edge[numEdge];
        for (int i = 0; i < numEdge; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edgeArr[i] = new Edge(input[0], input[1], input[2]);
        }

        int result = kruskal(edgeArr);
        System.out.println(result);
    }

    private static int kruskal(Edge[] edgeList) {

        int res = 0, cnt = 0;
        Arrays.sort(edgeList);

        make();

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                res += edge.weight;
                if (++cnt == numVertex - 1) {
                    return res;
                }
            }
        }

        return res;
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }
        parents[aRoot] = bRoot;
        return true;
    }

    private static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static void make() {
        for (int i = 1; i <= numVertex; i++) {
            parents[i] = i;
        }
    }

}

class Edge implements Comparable<Edge> {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        super();
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

}


