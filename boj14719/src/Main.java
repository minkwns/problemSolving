import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int col = Integer.parseInt(st.nextToken());

        int row = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split(" ");

        int area = 0;

        for (int i = 0; i < arr.length; i++) {
            area += findDepth(arr, i);
        }

        System.out.println(area);
    }

    public static int findDepth(String[] arr, int idx) {
        int l1 = 0;
        int l2 = 0;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;

        while (idx - l1 >= 0) {
            int num = Integer.parseInt(arr[idx - l1]);
            if (leftMax <= num) {
                leftMax = num;
            }
            l1++;
        }

        while (idx + l2 < arr.length) {
            int num = Integer.parseInt(arr[idx + l2]);
            if (rightMax <= num) {
                rightMax = num;
            }
            l2++;
        }
        int pivot = Integer.parseInt(arr[idx]);
        if (pivot < leftMax && pivot < rightMax) {
            return Math.min(leftMax, rightMax) - pivot;
        }
        return 0;
    }
}
