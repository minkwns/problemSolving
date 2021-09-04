import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int level = Integer.parseInt(st.nextToken());

        int[] tree = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();

        makeTreeRecursive(tree, 0, tree.length - 1, 0);

        Node[] arr = new Node[stack.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.get(i);
        }
        Comparator<Node> comparator = new Comparator<>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getLevel() - o2.getLevel();
            }
        };
        Arrays.sort(arr, comparator);

        int nowLevel = 0;

        System.out.print(arr[0].getNum());
        for (int i = 1; i < arr.length; i++) {
            if (nowLevel != arr[i].getLevel()) {
                System.out.println();
            }
            nowLevel = arr[i].getLevel();
            System.out.print(arr[i].getNum() + " ");
        }
    }

    public static Node makeTreeRecursive(int[] tree, int start, int end, int level) {
        if (start >= end) {
            Node leaf = new Node(tree[start], level);
            stack.push(leaf);
            return leaf;
        }

        int idx = (start + end) / 2;
        int parent = tree[idx];
        Node node = new Node(parent, level);

        stack.push(node);
        node.setLeft(makeTreeRecursive(tree, start, idx - 1, level + 1));
        node.setRight(makeTreeRecursive(tree, idx + 1, end, level + 1));
        return node;
    }
}

class Node {
    private int num;
    private int level;
    private Node left;
    private Node right;

    Node(int num, int level) {
        this.num = num;
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getLevel() {
        return level;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
