import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        int[] times = new int[num];

        int[] values = new int[num];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            times[i] = time;
            values[i] = value;
        }

        int[] memo = new int[num + 1];
        
        for (int i = num - 1; i >= 0; i--) {
            int endDay = i + times[i];
            if (endDay > num) {
                memo[i] = memo[i + 1];
            } else {
                memo[i] = Math.max(values[i] + memo[endDay], memo[i + 1]);
            }
        }
        System.out.println(memo[0]);
    }
}
