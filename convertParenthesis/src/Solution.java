import java.util.Stack;

class Solution {

    public String solution(String p) {
        return convertRecursive(p);
    }

    public static String convertRecursive(String str) {
        if (str.equals("")) {
            return "";
        }
        String u = str;
        String v = "";
        for (int i = 1; i < str.length(); i++) {
            if (isBalanced(str.substring(0, i))) {
                u = str.substring(0, i);
                v = str.substring(i);
                break;
            }
        }
        if (isRightParenthesis(u)) {
            return u + convertRecursive(v);
        }
        return "(" + convertRecursive(v) + ")" + reverseParenthesis(u.substring(1, u.length() - 1));
    }

    public static String reverseParenthesis(String str) {
        char[] reverse = new char[str.length()];

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                reverse[i] = '(';
            } else {
                reverse[i] = ')';
            }
        }
        return String.valueOf(reverse);
    }

    public static boolean isBalanced(String arr) {
        int leftCnt = 0;
        int rightCnt = 0;

        for (int i = 0; i < arr.length(); i++) {
            if (arr.charAt(i) == ')') {
                leftCnt++;
            } else {
                rightCnt++;
            }
        }
        if (leftCnt == rightCnt) {
            return true;
        }
        return false;
    }

    public static boolean isRightParenthesis(String arr) {
        boolean discriminate = true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(arr.charAt(i));
            } else {
                if (arr.charAt(i) == ')') {
                    if (stack.isEmpty()) {
                        discriminate = false;
                        break;
                    }
                    stack.pop();
                } else {
                    stack.push(arr.charAt(i));
                }
            }
        }
        if (!stack.isEmpty()) {
            discriminate = false;
        }
        return discriminate;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution("(()())()");

    }
}