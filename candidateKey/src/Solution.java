import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

class Solution {
    static Stack<Integer> stack;
    int[] visited;
    List<String> list;
    List<String> candidateKey;
    public int solution(String[][] relation) {
        int answer = 0;

        stack = new Stack<>();

        visited = new int[relation[0].length];

        candidateKey = new ArrayList<>();

        for (int i = 0; i < relation[0].length; i++) {
            list = new ArrayList<>();
            dfs(relation[0].length, i + 1, 0);
            for (int q = 0; q < list.size(); q++) {
                String key = "";
                Set<String> set = new HashSet<>();

                for (int j = 0; j < relation.length; j++) {
                    key = "";
                    for (int k = 0; k < list.get(q).length(); k++) {
                        key += relation[j][list.get(q).charAt(k) - '0'];
                    }
                    set.add(key);
                }
                if (set.size() == relation.length) {
                    int flag = 1;
                    for (var candidateKey : candidateKey) {
                        int cnt = 0;
                        for (int a = 0; a < candidateKey.length(); a++) {
                            char ch = candidateKey.charAt(a);
                            if (list.get(q).contains(String.valueOf(ch))) {
                                cnt++;
                            }
                        }
                        if (cnt == candidateKey.length()) {
                            flag = 0;

                        }
                    }
                    if (flag == 1) {
                        candidateKey.add(list.get(q));
                    }
                }
            }
        }
        System.out.println(candidateKey.size());
        return candidateKey.size();
    }


    public void dfs(int num, int k, int idx) {
        if (k == stack.size()) {
            String str = "";
            for (int i = 0; i < stack.size(); i++) {
                str += stack.get(i);
            }
            list.add(str);
            return;
        }

        for (int i = idx; i < num; i++) {
            stack.push(i);
            dfs(num, k, i + 1);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        stack = new Stack<>();
        String[][] input = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        sol.solution(input);
    }

}