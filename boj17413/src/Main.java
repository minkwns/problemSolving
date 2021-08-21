import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {





    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<') {
                String temp = "";
                while(str.charAt(i) != '>') {
                    temp += str.charAt(i);
                    i++;
                }
                temp += str.charAt(i);
                queue.add(temp);
            } else if (str.charAt(i) == ' ') {
                queue.add(" ");
            } else {
                String temp = "";
                while (i < str.length() && (str.charAt(i) != ' ' && str.charAt(i) != '<')) {
                    temp += str.charAt(i++);
                }
                queue.add(reverseString(temp));
                i--;
            }
        }
        while (!queue.isEmpty()) {
            System.out.print(queue.remove());
        }
    }

}
