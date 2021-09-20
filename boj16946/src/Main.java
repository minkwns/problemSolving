import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int cnt;
    static int groupNum;
    static Map<Integer, Integer> map;
    static int[][] visited;
    static Set<String> set;
    static Set<Integer> groupSet;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());

        int col = Integer.parseInt(st.nextToken());

        int[][] board = new int[row][col];

        set = new HashSet<>();

        groupSet = new HashSet<>();

        map = new HashMap<>();

        groupNum = 2;

        visited = new int[row][col];

        cnt = 0;

        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < col; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 0 && visited[i][j] == 0) {
                    bfs(board, j, i);
                    cnt = 0;
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        int[] nx = {-1, 1, 0, 0};
        int[] ny = {0, 0, -1, 1};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int num = 0;
                if (board[i][j] == 1) {
                    groupSet = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int dx = j + nx[k];
                        int dy = i + ny[k];
                        if (dx >= 0 && dx < board[0].length && dy >= 0 && dy < board.length && board[dy][dx] > 1 && !groupSet.contains(board[dy][dx])) {
                            groupSet.add(board[dy][dx]);
                            num += map.get(board[dy][dx]);
                        }
                    }
                    sb.append(String.valueOf((num + 1) % 10));
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void bfs(int[][] board, int x, int y) {
        board[y][x] = 0;
        visited[y][x] = 1;
        int[] nx = {-1, 1, 0, 0};
        int[] ny = {0, 0, -1, 1};
        Queue<Point> queue = new LinkedList<>();
        Point point = new Point(x, y);
        queue.add(point);
        board[y][x] = groupNum;
        set.add(point.toString());

        while(!queue.isEmpty()) {
            Point next = queue.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int dx = next.getX() + nx[i];
                int dy = next.getY() + ny[i];
                String str = "x is " + dx + "y is " + dy;

                if (dx >= 0 && dx < board[0].length && dy >= 0 && dy < board.length) {
                    if (!set.contains(str) && board[dy][dx] == 0) {
                        Point nextPoint = new Point(dx, dy);
                        board[dy][dx] = groupNum;
                        visited[dy][dx] = 1;
                        set.add(nextPoint.toString());
                        queue.add(nextPoint);
                    }
                }
            }
        }
        map.put(groupNum, cnt);
        groupNum++;

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

    @Override public String toString() {
        return "x is " + x + "y is " + y;
    }

}
