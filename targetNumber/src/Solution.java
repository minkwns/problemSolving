class Solution {
    static int cnt = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        int answer = 0;
        answer = cnt;
        return answer;
    }


    public void dfs(int[] arr, int target, int sum, int depth) {
        if (depth == arr.length) {
            if (sum == target) {
                cnt++;
            }
            return;
        }

        dfs(arr, target, sum + arr[depth], depth + 1);
        dfs(arr, target, sum - arr[depth], depth + 1);
    }
}