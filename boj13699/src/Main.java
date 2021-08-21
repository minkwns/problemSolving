import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int number = Integer.parseInt(st.nextToken());

        long[] memo = new long[number + 1];

        memo[0] = 1;

        for (int i = 1; i <= number; i++) {
            for (int j = 0; j < i; j++) {
                memo[i] += memo[j] * memo[i - j - 1];
            }
        }

        System.out.println(memo[number]);


    }
}
