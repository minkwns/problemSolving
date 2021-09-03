import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int min;

    public static void main(String[] args) throws IOException {
        min = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        int[] input = new int[num];

        int idx = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < num; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (next < 0) {
                idx++;
            }
            input[i] = next;
        }

        if (idx == 0) {
            System.out.println(input[0] + input[1]);
            return;
        }

        if (idx + 1 <= input.length - 1) {
            min = input[idx] + input[idx + 1];
        }

        if (idx - 2 >= 0) {
            if (Math.abs(min) > Math.abs(input[idx - 2] + input[idx - 1])) {
                min = input[idx - 2] + input[idx - 1];
            }
        }

        int start = idx;

        int pivot = idx - 1;

        for (int i = start; i < input.length; i++) {
            int now = input[i] + input[pivot];
            int cnt = 1;
            while (pivot - cnt >= 0) {
                if (Math.abs(now) < Math.abs(input[i] + input[pivot - cnt])) {
                    break;
                }
                now = input[i] + input[pivot - cnt];
                cnt++;
            }

            if (Math.abs(min) > Math.abs(now)) {
                min = now;
            }
            pivot -= (cnt - 1);
        }

        System.out.println(min);

    }
}