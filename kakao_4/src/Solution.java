import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class Solution {
    int[] visited;
    Stack<Integer> stack;
    Set<String> list;
    int chance;
    int maxScore;
    Map<String, Case> winCase;

    public int[] solution(int n, int[] info) {
        int[] answer = new int[info.length];
        visited = new int[11];
        stack = new Stack<>();
        list = new HashSet<>();
        chance = n;
        maxScore = Integer.MIN_VALUE;
        winCase = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            divideNum(chance, 0, i, 0);
            dfs(info, 0, i, 0);
            list = new HashSet<>();
        }

        if (winCase.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        Case[] allCases = new Case[winCase.size()];
        int idx = 0;

        for (var entry : winCase.entrySet()) {
            allCases[idx++] = entry.getValue();
        }
        Comparator<Case> compScore = new Comparator<Case>() {
            @Override
            public int compare(Case o1, Case o2) {
                return o2.getScoreDiff() - o1.getScoreDiff();
            }
        };

        Comparator<Case> alphabeticlaOrder = new Comparator<Case>() {
            public int compare(Case str1, Case str2) {
                int res = String.CASE_INSENSITIVE_ORDER.compare(reverseString(str2.getWinCase(), "", 1), reverseString(str1.getWinCase(), "", 1));
                if (res == 0) {
                    res = reverseString(str2.getWinCase(), "", 1).compareTo(reverseString(str1.getWinCase(), "", 1));
                }
                return res;
            }
        };
        Arrays.sort(allCases, alphabeticlaOrder);

        Arrays.sort(allCases, compScore);

        for (int i = 0; i < allCases[0].getWinCase().length(); i++) {
            answer[i] = (allCases[0].getWinCase().charAt(i) - '0');
        }

        return answer;
    }

    public void divideNum(int num, int depth ,int k, int sum) {
        if (sum > num) {
            return;
        }
        if (depth == k) {
            int total = 0;
            String numbers = "";
            if (sum != num) {
                return;
            }

            for (int i = 0; i < stack.size(); i++) {
                total += stack.get(i);
                numbers += stack.get(i);
            }
            if (num == total) {
                list.add(numbers);
            }
            return;
        }

        for (int i = 1; i <= num; i++) {
            stack.push(i);
            divideNum(num, depth + 1, k, sum + i);
            stack.pop();
        }
    }


    public void dfs(int[]  target, int depth, int k, int idx) {
        if (depth == k) {

            for (var combi : list) {
                int score = 0;
                int apeachScore = 0;

                int index = 0;
                String str = "";
                for (int i = 0; i < target.length; i++) {
                    if (visited[i] == 1) {
                        str += combi.charAt(index);
                        if (target[i] < (combi.charAt(index++) - '0')) {
                            score += (10 - i);
                        } else {
                            apeachScore += (10 - i);

                        }
                    } else {
                        if (target[i] >= 1) {
                            apeachScore += (10 - i);
                        }
                        str += "0";
                    }
                }
                if (score > apeachScore) {
                    winCase.put(str, new Case(str, score - apeachScore));
                }
            }
        }

        for (int i = idx; i < target.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(target, depth + 1, k, i + 1);
                visited[i] = 0;
            }
        }
    }

    public String reverseString(String s, String result, int idx) {
        if (s.length() == result.length()) {
            return result;
        }
        return reverseString(s, result + s.charAt(s.length() - idx), idx + 1);
    }


    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] info = {0,0,0,0,0,0,0,0,3,4,3};
        sol.solution(10, info);
        System.out.println((sol.reverseString("sex", "", 1)));
    }
}

class Case {
    private String winCase;

    private int scoreDiff;

    public Case(String winCase, int scoreDiff) {
        this.winCase = winCase;
        this.scoreDiff = scoreDiff;
    }

    public String getWinCase() {
        return winCase;
    }

    public int getScoreDiff() {
        return scoreDiff;
    }
}