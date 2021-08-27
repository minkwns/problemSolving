import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int max;

    public static ArrayList<Character> operators;

    public static ArrayList<Integer> operands;

    public static int calculate(int operand1, int operand2, char op) {
        if (op == '-') {
            return operand1 - operand2;
        } else if (op == '+') {
            return operand1 + operand2;
        } else {
            return operand1 * operand2;
        }
    }

    public static void dfs(int operatorIndex, int sum) {

        if (operatorIndex >= operators.size()) {
            max = Math.max(max, sum);
            return;
        }

        if (operatorIndex < operators.size()) {
            int result = calculate(sum, operands.get(operatorIndex + 1), operators.get(operatorIndex));
            dfs(operatorIndex + 1, result);
        }

        if (operatorIndex + 1 < operators.size()) {
            int result = calculate(operands.get(operatorIndex + 1), operands.get(operatorIndex + 2), operators.get(operatorIndex + 1));
            dfs(operatorIndex + 2, calculate(sum, result, operators.get(operatorIndex)));
        }
    }



    public static void main(String[] args) throws IOException {
        operands = new ArrayList<>();

        operators = new ArrayList<>();

        max = Integer.MIN_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        String polynomial = st.nextToken();

        for (int i = 0; i < polynomial.length(); i++) {
            char ch = polynomial.charAt(i);
            if (Character.isDigit(ch)) {
                operands.add(ch - '0');
            } else {
                operators.add(ch);
            }
        }
        dfs(0, operands.get(0));

        System.out.println(max);

    }
}

