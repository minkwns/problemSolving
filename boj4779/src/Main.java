import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        StringTokenizer st;
        while((input = br.readLine()) != null) {
            if (input.equals("")) {
                System.out.println();
            }
            cnt = Integer.parseInt(input);
            System.out.println(makeCantoreRecursive("-", 0));
        }
    }

    public static String makeCantoreRecursive(String str, int depth) {
        if (depth == cnt) {
            return str;
        }
        String space = " ";
        for (int i = 0; i < depth * 3 - 1; i++) {
            space += " ";
        }
        return makeCantoreRecursive(str + space + str,depth + 1);
    }

    
}
