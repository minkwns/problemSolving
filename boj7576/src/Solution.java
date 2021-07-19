import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    static int[][] visited;

    static int day;

    Solution() {
        this.day = 0;
    }

    public static int bfs(int[][] arr, Queue<Node> queue) {

        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};

        int maxDay = 0;

        while (!queue.isEmpty()) {
            Node next = queue.remove();
            if (next.getDay() >= maxDay) {
                maxDay = next.getDay();
            }

            for (int i = 0; i < 4; i++) {
                int x = next.getX() + dx[i];
                int y = next.getY() + dy[i];
                day++;
                if (x < arr[0].length && y < arr.length && x >= 0 && y >= 0 && visited[y][x] == 0  && arr[y][x] != -1) {
                    visited[y][x] = 1;
                    queue.add(new Node(x, y, next.getDay() + 1));
                }
            }

        }
        return maxDay;

    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int col = sc.nextInt();
        int row = sc.nextInt();

        visited = new int[row][col];


        int[][] arr = new int[row][col];

        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) {
                    visited[i][j] = 1;
                    queue.add(new Node(j,i, 0));
                }
                if (arr[i][j] == -1) {
                    visited[i][j] = 1;
                }
            }
        }


        int answer = bfs(arr, queue);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == 0) {
                    answer = -1;
                }
            }
        }

        System.out.println(answer);
    }

}

class Node {
    int x;
    int y;
    int day;

    Node(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
