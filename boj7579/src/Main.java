import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfApp = Integer.parseInt(st.nextToken());

        int need = Integer.parseInt(st.nextToken());

        int[] memory = new int[numOfApp];

        int[] expense = new int[numOfApp];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < numOfApp; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < numOfApp; i++) {
            expense[i] = Integer.parseInt(st.nextToken());
        }

    }
}
