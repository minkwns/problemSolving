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
        if (originalStr.equals(partialStr)) {
            System.out.println("1");
            return;
        }

        int[] table = new int[partialStr.length() + 1];
        table[0] = -1;

        for (int i = 1; i < table.length; i++) {
            String subStr =partialStr.substring(0, i);
            int cnt = 0;
            while (subStr.length() >= 2) {
                if (subStr.charAt(0) == subStr.charAt(subStr.length() - 1)) {
                    cnt ++;
                }
                subStr = subStr.substring(1, subStr.length() - 1);
            }
            table[i] = cnt;
        }

        String compareStr = originalStr.substring(0, partialStr.length());

        System.out.println(compareStr);

        if (originalStr.indexOf(partialStr) >= 1) {
            System.out.println("1");
            return;
        } else {
            if (originalStr.substring(0, partialStr.length()).equals(partialStr)) {
                System.out.println("1");
                return;
            }
            System.out.println("0");
        }


    }
}
