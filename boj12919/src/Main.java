import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int flag;

    Main() {
        flag = 0;
    }
    public void dfs(String str, String target) {
        if (str.equals(target)) {
            flag = 1;
            return;
        }

        if (str.length() <= target.length()) {
            return;
        }

        if (str.charAt(str.length() - 1) == 'A') {
            dfs(str.substring(0, str.length() - 1), target);
        }
        if (str.charAt(0) == 'B') {
            dfs(reverseString(str).substring(0, str.length() - 1), target);
        }
    }

    public static String reverseString(String str){
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String first = st.nextToken();

        st = new StringTokenizer(br.readLine());

        String second = st.nextToken();

        Main sol = new Main();

        sol.dfs(second, first);

        System.out.println(flag);

    }


}
