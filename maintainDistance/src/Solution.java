import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static int flag;
    boolean[][] visited;
    public int[] solution(String[][] places) {

        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            visited = new boolean[5][5];
            char[][] arr = new char[5][5];
            flag = 0;
            for (int j = 0; j < places[0].length; j++) {
                for (int k = 0; k < places[0][0].length(); k++) {
                    arr[j][k] = places[i][j].charAt(k);
                }
            }

            for (int j = 0; j < places[0].length; j++) {
                for (int k = 0; k < places[0][0].length(); k++) {
                    if (arr[j][k] == 'P') {
                        bfs(j, k, arr);
                    }
                }
            }

            if (flag == 1) {
                answer[i] = 0;
            } else {
                answer[i] = 1;
            }
        }
        return answer;
    }

    public void bfs(int x, int y, char[][] arr) {
        int[] dx = {-1, 1, 0, 0};

        int[] dy = {0, 0, -1, 1};

        Point point = new Point(x, y, 0);

        Queue<Point> queue = new LinkedList<>();

        queue.add(point);

        while (!queue.isEmpty()) {
            Point next = queue.poll();

            if (next.getDepth() == 2) {
                return;
            }
            visited[next.getX()][next.getY()] = true;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + next.getX();
                int ny = dy[i] + next.getY();

                if (nx >= 0 && nx < arr.length && ny >= 0 && ny < arr[0].length) {

                    if (arr[nx][ny] == 'O' && !visited[nx][ny]) {
                        queue.add(new Point(nx, ny, next.getDepth() + 1));
                    }

                    if (arr[nx][ny] == 'P' && !visited[nx][ny]) {
                        flag = 1;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] input = {{"OOOOO", "OXXOX", "OOXOX", "OOOOO", "POPOO"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] answer = sol.solution(input);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

}
class Point {
    private final int x;
    private final int y;
    private final int depth;

    Point(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDepth() {
        return depth;
    }
}