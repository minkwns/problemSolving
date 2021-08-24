import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        int[] schedule = new int[366];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());

            int end = Integer.parseInt(st.nextToken());

            for (int j = start; j <= end; j++) {
                schedule[j] += 1;
            }
        }

        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] >= 1) {
                int max = 1;
                int cnt = 1;
                while (i + cnt < schedule.length && schedule[i + cnt] >= 1) {
                    if (schedule[i + cnt] > max) {
                        max = schedule[i + cnt];
                    }
                    cnt++;
                }
                answer += cnt * max;
                i += cnt;
            }
        }
        System.out.println(answer);
    }
}
