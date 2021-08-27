import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st  = new StringTokenizer(br.readLine());

        String originalStr = st.nextToken();

        st = new StringTokenizer(br.readLine());

        String partialStr = st.nextToken();


        int[] table = new int[partialStr.length()];

        int cnt = 0;

        for (int i = 1; i < table.length; i++) {

            while (cnt > 0 && partialStr.charAt(cnt) != partialStr.charAt(i)) {
                cnt = table[cnt - 1];
            }

            if (partialStr.charAt(cnt) == partialStr.charAt(i)) {
                table[i] = ++cnt;
            }
        }

        cnt = 0;

        for (int i = 0; i < originalStr.length(); i++) {
            while (cnt > 0 && originalStr.charAt(i) != partialStr.charAt(cnt)) {
                cnt = table[cnt - 1];
            }

            if (originalStr.charAt(i) == partialStr.charAt(cnt)) {
                if (cnt == partialStr.length() - 1) {
                    System.out.println("1");
                    return;
                } else {
                    cnt++;
                }
            }
        }
        System.out.println("0");
        return;
    }
}