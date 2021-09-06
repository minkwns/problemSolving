import java.util.*;

class Solution {

    Map<String, List<Integer>> map;

    List<String> queries;

    int[] visited;

    public void makeCombination() {
        String[] language = {"cpp", "java", "python", "-"};
        String[] domain = {"backend", "frontend", "-"};
        String[] career = {"junior", "senior", "-"};
        String[] soulFood = {"chicken", "pizza", "-"};
        for (int i = 0; i < language.length; i++) {
            for (int j = 0; j < domain.length; j++) {
                for (int k = 0; k < career.length; k++) {
                    for (int p = 0; p < soulFood.length; p++) {
                        map.put(language[i] + domain[j] + career[k] + soulFood[p], new ArrayList<Integer>());
                    }
                }
            }
        }
    }
    public void dfs(String[] info, int depth, int k, int idx, int score) {
        if (depth == k) {
            String developer = "";
            for (int i = 0; i < 4; i++) {
                if (visited[i] == 0) {
                    developer += info[i];
                } else {
                    developer += "-";
                }
            }
            map.get(developer).add(score);
        }
        for (int i = idx; i < 4; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(info, depth + 1, k,i + 1, score);
                visited[i] = 0;
            }
        }
    }

    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();

        queries = new ArrayList<>();

        makeCombination();

        visited = new int[4];

        int[] answer = new int[query.length];
        for (int i = 0; i < info.length; i++) {
            String[] infoArr = info[i].split(" ");
            int score = Integer.parseInt(infoArr[4]);
            String[] developer = new String[4];
            developer[0] = infoArr[0];
            developer[1] = infoArr[1];
            developer[2] = infoArr[2];
            developer[3] = infoArr[3];
            for (int j = 0; j <= 4; j++) {
                dfs(developer, 0, j, 0, score);
            }
        }
        for (var entry : map.entrySet()) {
            entry.getValue().sort(null);
        }


        for (int i = 0; i < query.length; i++) {
            String[] temp = query[i].split(" ");
            int score = Integer.parseInt(temp[7]);
            String condition = temp[0] + temp[2] + temp[4] + temp[6];

            int idx = indexOfSatisfied(map.get(condition), 0, map.get(condition).size(), score);

            answer[i] = map.get(condition).size()-idx;
        }

        return answer;
    }

    public static int indexOfSatisfied(List<Integer> arr, int start, int end, int num) {
        if (start >= end) {
            return start;
        }
        int mid = (start + end) / 2;

        if (num <= arr.get(mid)) {
            return indexOfSatisfied(arr, start, mid, num);
        } else {
            return indexOfSatisfied(arr, mid + 1, end, num);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        sol.solution(info, query);
    }
}
