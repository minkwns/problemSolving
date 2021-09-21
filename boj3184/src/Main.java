import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int totalWolf;
    static int totalSheep;
    static int wolf;
    static int sheep;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        wolf = 0;
        sheep = 0;
        totalWolf = 0;
        totalSheep = 0;

        char[][] board = new char[row][col];
        visited = new int[row][col];
        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < col; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == 0) {
                    bfs(board, j, i);
                }
            }
        }
        System.out.print(totalSheep + " " + totalWolf);
    }

    public static void bfs(char[][] board, int x, int y) {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while(!queue.isEmpty()) {
            Point next = queue.remove();
            if (board[next.getY()][next.getX()] == 'v') {
                wolf++;
            } else if (board[next.getY()][next.getX()] == 'o') {
                sheep++;
            }
            for (int i = 0; i < 4; i++) {
                int nx = next.getX() + dx[i];
                int ny = next.getY() + dy[i];

                if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                    if (visited[ny][nx] == 0 && board[ny][nx] != '#') {
                        visited[ny][nx] = 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
        if (wolf >= sheep) {
            totalWolf += wolf;
        } else {
            totalSheep += sheep;
        }
        wolf = 0;
        sheep =0;
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

