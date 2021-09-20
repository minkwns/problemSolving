import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int cnt;
    static int[][] visited;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int[][] board = new int[row][col];
        visited = new int[row][col];
        cnt = 0;
        max = Integer.MIN_VALUE;

        int numPoint = Integer.parseInt(st.nextToken());
        Point[] points = new Point[numPoint];
        for (int i = 0; i < numPoint; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            board[y - 1][x - 1] = 1;
            points[i] = new Point(x, y);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) {
                    bfs(board, j, i);
                    if (cnt >= max) {
                        max = cnt;
                    }
                    cnt = 0;
                }
            }
        }
        System.out.println(max);
    }

    public static void bfs(int[][] board, int x, int y) {
        int[] dx = {-1 ,1 ,0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[y][x] = 1;

        while(!queue.isEmpty()) {
            Point next = queue.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = next.getX() + dx[i];
                int ny = next.getY() + dy[i];
                if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                    if (visited[ny][nx] == 0 && board[ny][nx] == 1) {
                        visited[ny][nx] = 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
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
