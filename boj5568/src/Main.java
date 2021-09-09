import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static HashSet<String> numbers;

    static int cnt;

    static int[] visited;

    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        numbers = new HashSet<>();

        stack = new Stack<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        visited = new int[num];

        st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        cnt = k;

        String[] arr = new String[num];
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = st.nextToken();
        }
        dfs(arr, 0);

        System.out.println(numbers.size());
    }

    public static void makeNumber(String arr[]) {
        String str = "";
        for (int i = 0; i < stack.size(); i++) {
            str += arr[stack.get(i)];
        }

        if (!numbers.contains(str)) {
            numbers.add(str);
        }
    }

    public static void dfs(String arr[], int depth) {
        if (depth == cnt) {
            makeNumber(arr);
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i] != 1) {
                visited[i] = 1;
                stack.push(i);
                dfs(arr, depth + 1);
                stack.pop();
                visited[i] = 0;
            }
        }
    }
}
