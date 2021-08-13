import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

    static int maxDepth;

    static HashSet<Character> isVisited;

    Solution() {
        this.maxDepth = 0;

        isVisited = new HashSet<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        char[][] arr = new char[row][col];

        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < col; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        Solution solution = new Solution();

        solution.dfs(arr, 0, 0, 1);

        System.out.println(maxDepth);
    }


    public void dfs(char[][] arr, int x, int y, int depth) {

        isVisited.add(arr[y][x]);

        if (depth >= maxDepth) {
            maxDepth = depth;
        }

        int dx[] = {-1 , 1, 0, 0};
        int dy[] = {0, 0, -1, 1};


        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= arr[0].length || ny < 0 || ny >= arr.length) {
                continue;
            }
            if (!isVisited.contains(arr[ny][nx])) {
                dfs(arr, nx, ny, depth + 1);
                isVisited.remove(arr[ny][nx]);
            }

        }


    }
}
