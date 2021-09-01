import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static int[][] visited;

    int[][] dp;

    int cost;

    int min;

    public int solution(int[][] board) {
        visited = new int[board.length][board[0].length];
        dp = new int[board.length][board[0].length];
        cost = 0;
        min = Integer.MAX_VALUE;
        bfs(board);
        int answer = this.min;
        return answer;
    }

    public void bfs(int[][] board) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(0, 0, 0, 0));

        int[] nx = {-1, 1, 0, 0};

        int[] ny = {0, 0, -1, 1};

        visited[0][0] = 1;
        while (!queue.isEmpty()) {
            Point next = queue.poll();
            if (next.getX() == board[0].length - 1 && next.getY() == board.length - 1) {
                if (min > next.getCost()) {
                    min = next.getCost();
                }
            }
            for (int i = 0; i < 4; i++) {
                int dx = nx[i] + next.getX();
                int dy = ny[i] + next.getY();
                if (dy >= 0 && dy < board.length && dx >= 0 && dx < board[0].length) {
                    if ((dp[dy][dx] == 0 || dp[dy][dx] >= next.getCost() + 100) && board[dy][dx] != 1) {
                        if (i == 0 || i == 1) {
                            if (next.getDirection() == -1) {
                                queue.add(new Point(dx, dy, next.getCost() + 600, 1));
                                dp[dy][dx] = next.getCost() + 600;
                            } else {
                                queue.add(new Point(dx, dy, next.getCost() + 100, 1));
                                dp[dy][dx] = next.getCost() + 100;
                            }
                        } else {
                            if (next.getDirection() == 1) {
                                queue.add(new Point(dx, dy, next.getCost() + 600, -1));
                                dp[dy][dx] = next.getCost() + 600;
                            } else {
                                queue.add(new Point(dx, dy, next.getCost() + 100, -1));
                                dp[dy][dx] = next.getCost() + 100;
                            }
                        }
                    }
                }
            }
        }
    }

    public void dfs(int[][] board, int x, int y, int direction) {
        if (min < cost) {
            return;
        }

        if (y == board.length - 1 && x == board[0].length - 1) {
            if (min > cost) {
                min = cost;
            }
        }

        if (dp[y][x] == 0) {
            dp[y][x] = cost;
        } else {
            if (cost > dp[y][x] + 500) {
                return;
            }
        }

        int[] nx = {-1, 1, 0, 0};

        int[] ny = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int dx = nx[i] + x;
            int dy = ny[i] + y;
            if (dy >= 0 && dy < board.length && dx >= 0 && dx < board[0].length) {
                if (visited[dy][dx] != 1 && board[dy][dx] != 1) {
                    int cost = 0;
                    visited[dy][dx] = 1;
                    if (i == 0 || i == 1) {
                        if (direction == -1) {
                            cost = 600;
                        } else {
                            cost = 100;
                        }
                        this.cost += cost;
                        dfs(board, dx, dy, 1);
                        this.cost -= cost;
                        visited[dy][dx] = 0;
                    } else {
                        if (direction == 1) {
                            cost = 600;
                        } else {
                            cost = 100;
                        }
                        this.cost += cost;
                        dfs(board, dx, dy, -1);
                        this.cost -= cost;
                        visited[dy][dx] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] input = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};

        int[][] input2 = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};

        int[][] input3 = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
        System.out.println(sol.solution(input2));
    }

}

class Point {
    private int x;

    private int y;

    private int direction;

    private int cost;

    Point(int x, int y, int cost ,int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.cost = cost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}