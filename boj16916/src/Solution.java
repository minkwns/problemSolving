import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st  = new StringTokenizer(br.readLine());

        String originalStr = st.nextToken();

        st = new StringTokenizer(br.readLine());

        String partialStr = st.nextToken();

        if (partialStr.length() > originalStr.length()) {
            System.out.println("0");
            return;
        }

        int[] table = new int[partialStr.length() + 1];
        table[0] = -1;

        for (int i = 1; i < table.length; i++) {
            String subStr =partialStr.substring(0, i);
            int cnt = 0;
            while (subStr.length() / 2 > cnt) {
                if (subStr.charAt(cnt) == subStr.charAt(subStr.length() - 1 - cnt)) {
                    cnt ++;
                } else {
                    break;
                }
            }
            table[i] = cnt;
        }


        for (int i = 0; i < originalStr.length(); i++) {
            int cnt = 0;
            while (i + cnt < originalStr.length() &&
                    originalStr.charAt(i + cnt) == partialStr.charAt(cnt)) {

                cnt++;
                if (cnt == partialStr.length()) {
                    System.out.println("1");
                    return;
                }
            }
            i = i + cnt - table[cnt] - 1;
        }
        System.out.println("0");
        return;
    }
}
