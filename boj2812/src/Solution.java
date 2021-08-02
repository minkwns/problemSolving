import java.util.Scanner;
import java.util.Stack;

public class Solution {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int strLength = sc.nextInt();

        int numOfExtract = sc.nextInt();

        String input = sc.next();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < strLength; i++) {
            char c = input.charAt(i);

            while(!stack.isEmpty() && stack.peek() < c && numOfExtract-- > 0) {
                stack.pop();
            }
            stack.add(c);
        }
        while(numOfExtract-- > 0) {
            stack.pop();
        }


        int len = stack.size();
        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            arr[i] = stack.get(i);
        }
        System.out.println(arr);

    }
}
