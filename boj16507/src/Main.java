import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int sum = 0;
    static int[][] visited;
    static int left;
    static int right;
    static int bottom;
    static int top;

    public static void bfs(int[][] board, int x, int y) {
        int[] nx = {-1, 1, 0, 0};
        int[] ny = {0, 0, -1, 1};

        Point point = new Point(x, y);
        Queue<Point> queue = new LinkedList<>();
        visited[y][x] = 1;
        queue.add(point);

        while (!queue.isEmpty()) {
            Point next = queue.poll();
            sum += board[next.getY()][next.getX()];
            for (int i = 0; i < 4; i++) {
                int dx = next.getX() + nx[i];
                int dy = next.getY() + ny[i];
                if (dy <= top - 1 && dy >= bottom - 1 && dx >= left - 1 && dx <= right - 1) {
                    if (visited[dy][dx] != 1) {
                        visited[dy][dx] = 1;
                        queue.add(new Point(dx, dy));
                    }
                }
            }
        }
    }

    public static void dfs(int[][] board, int x, int y) {

        int[] nx = {-1, 1, 0, 0};
        int[] ny = {0, 0, -1, 1};

        visited[y][x] = 1;

        sum += board[y][x];

        for (int i = 0; i < 4; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dy <= top - 1 && dy >= bottom - 1 && dx >= left - 1 && dx <= right - 1) {
                if (visited[dy][dx] != 1) {
                    dfs(board, dx, dy);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int numInput = Integer.parseInt(st.nextToken());

        int[][] board = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < numInput; i++) {
            visited = new int[row][col];
            int[] range = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            left = Math.min(range[1], range[3]);
            right = Math.max(range[1], range[3]);
            bottom = Math.min(range[0], range[2]);
            top = Math.max(range[0], range[2]);
            bfs(board, left - 1, bottom - 1);
            int width = (right - left + 1) * (top - bottom + 1);

            System.out.println(sum / width);
            sum = 0;
        }
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
