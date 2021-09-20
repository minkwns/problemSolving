import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int[][] visited;
    static int cnt = 0;
    static Stack<Integer> stack;
    static Set<String> set;
    static int[][] walls;
    static int min;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());

        int col = Integer.parseInt(st.nextToken());

        int[][] board = new int[row][col];
        visited = new int[row][col];
        stack = new Stack<>();
        set = new HashSet<>();
        walls = new int[row][col];
        min = Integer.MAX_VALUE;
        answer = 0;

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        makeWall(board, 0);

        System.out.println(answer);
    }

    public static void makeWall(int[][] board, int depth) {
        if (depth == 3) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 2) {
                        bfs(board, j, i);
                    }
                }
            }

            if (min > cnt) {
                min = cnt;
                answer = calculateSafetyArea(board);
            }
            visited = new int[board.length][board[0].length];
            cnt = 0;
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (walls[i][j] == 0 && board[i][j] == 0) {
                    walls[i][j] = 1;
                    makeWall(board, depth + 1);
                    walls[i][j] = 0;
                }
            }
        }
    }

    public static void bfs(int[][] board, int x, int y) {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point next = queue.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = next.getX() + dx[i];
                int ny = next.getY() + dy[i];

                if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                    if (visited[ny][nx] == 0 && board[ny][nx] == 0 && walls[ny][nx] == 0) {
                        visited[ny][nx] = 1;
                        queue.add(new Point(nx, ny));

                    }
                }
            }
        }
    }

    public static int calculateSafetyArea(int[][] board) {
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)  {
                if (board[i][j] == 0 && visited[i][j] == 0 && walls[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
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