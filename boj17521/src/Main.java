import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numInput = Integer.parseInt(st.nextToken());
        long cash = Long.parseLong(st.nextToken());

        int[] arr = new int[numInput];

        for (int i = 0; i < numInput; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i < numInput; i++) {
            if (arr[i] > arr[i - 1]) {
                cash += (long) (arr[i] - arr[i - 1]) * (cash / arr[i - 1]);
            }
        }

        System.out.println(cash);

    }
}
