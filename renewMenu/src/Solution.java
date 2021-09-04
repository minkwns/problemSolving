import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class Solution {

    int k;
    int[] visited;
    Map<String, Integer> popularMenu;
    int max;

    public String[] solution(String[] orders, int[] course) {
        popularMenu = new HashMap<>();
        visited = new int[10];
        List<String> result = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {
            k = course[i];
            max = 0;
            for (int j = 0; j < orders.length; j++) {
                dfs(orders[j], 0, 0);
            }
            int[] alphabet = new int[26];
            for (var entry  : popularMenu.entrySet()) {
                if (entry.getValue() == max) {
                    result.add(entry.getKey());
                }
            }
            popularMenu = new HashMap<>();
        }

        String[] answer = result.toArray(new String[0]);
        Arrays.sort(answer);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
        return answer;
    }


    public void dfs(String str, int depth, int idx) {
        if (depth == k) {
            String result = "";
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == 1) {
                    result += str.charAt(i);
                }
            }
            char[] chars = result.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (!popularMenu.containsKey(sorted)) {
                popularMenu.put(sorted, 1);
            } else {
                int cnt = popularMenu.get(sorted) + 1;
                if (max <= cnt) {
                    max = cnt;
                }
                popularMenu.put(sorted, cnt);
            }
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(str, depth + 1, i);
                visited[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] input = {"XYZ", "XWY", "WXA"};
        int[] course = {2 ,3 ,4};
        sol.solution(input, course);
    }
}