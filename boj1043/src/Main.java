import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int root;
    static Map<Integer, SetNode> sets;
    static int answer;
    static Map<Integer, Node> map;
    static Set<Node> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        root = -1;
        answer = 0;

        int numPeople = Integer.parseInt(st.nextToken());
        int numParty = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sets = new HashMap<>();
        map = new HashMap<>();
        visited = new HashSet<>();

        for (int i = 1; i <= numPeople; i++) {
            sets.put(i, new SetNode(i, 1));
            map.put(i, new Node(i));
        }

        int knowTruth = Integer.parseInt(st.nextToken());
        int[] knowns = new int[knowTruth];
        for (int i = 0; i < knowTruth; i++) {
            int num = Integer.parseInt(st.nextToken());
            knowns[i] = num;
        }

        for (int i = 0; i < knowTruth; i++) {
            for (int j = 0; j < knowTruth; j++) {
                if (i != j) {
                    map.get(knowns[i]).addNeighbor(map.get(knowns[j]));
                }
            }
        }


        List<List<Integer>> records = new ArrayList<>();
        for (int i = 0; i < numParty; i++) {
            st = new StringTokenizer(br.readLine());
            int participants = Integer.parseInt(st.nextToken());
            int[] peoples = new int[participants];
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < participants; j++) {
                int people = Integer.parseInt(st.nextToken());
                peoples[j] = people;
                list.add(people);
            }
            records.add(list);
        }
        for (List item : records) {
            for (int i = 0; i < item.size(); i++) {
                for (int k = 0; k < item.size(); k++) {
                    if (i != k) {
                        map.get(item.get(i)).addNeighbor(map.get(item.get(k)));
                    }
                }
            }
        }

        for (int i = 0; i < knowTruth; i++) {
            dfs(map.get(knowns[i]));
        }

        for (List item : records) {
            int cnt = 0;
            for (int i = 0 ; i < item.size(); i++) {
                if (find((Integer) item.get(i)) != root) {
                    cnt++;
                }
            }
            if (cnt == item.size()) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void dfs(Node root) {
        if (visited.contains(root)) {
            return;
        }
        visited.add(root);
        for (int i = 0; i < root.getNeighbor().size(); i++) {
            union(root.getIdentifier(), root.getNeighbor().get(i).getIdentifier());
            dfs(root.getNeighbor().get(i));
        }
    }

    public static Boolean union(int a1, int a2) {
        int aRoot = find(a1);
        int bRoot = find(a2);

        if (aRoot == bRoot) {
            return false;
        }
        SetNode parent = sets.get(aRoot);
        SetNode child = sets.get(bRoot);

        if (parent.size < child.size) {
            SetNode temp = parent;
            parent = child;
            child = temp;
        }

        child.parent = parent.parent;
        parent.size = child.size + parent.size;
        root = parent.parent;
        return true;
    }

    public static int find(int a1) {

        SetNode n = sets.get(a1);
        int parent = n.parent;
        if (parent == a1) {
            return a1;
        }
        n.parent = find(n.parent);
        return n.parent;
    }

}

class SetNode {
    int parent;
    int size;

    public SetNode(int parent, int size) {
        this.parent = parent;
        this.size = size;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

class Node {
    private int identifier;
    private List<Node> neighbor;

    public Node(int identifier) {
        this.identifier = identifier;
        this.neighbor = new ArrayList<>();
    }

    public int getIdentifier() {
        return identifier;
    }

    public List<Node> getNeighbor() {
        return neighbor;
    }

    public void addNeighbor(Node neighbor) {
        this.neighbor.add(neighbor);
    }
}
