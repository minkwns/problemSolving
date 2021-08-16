class Solution {

    public int solution(String s) {
        int answer = 0;

        int minLength = Integer.MAX_VALUE;

        if (s.length() == 1) {
            return 1;
        }

        for (int i = 1; i <= s.length() / 2; i++) {
            String str = compressString(s, i, "");
            if (str.length() != 0 && str.length() <= minLength) {
                minLength = str.length();
            }
        }
        answer = minLength;

        return answer;
    }

    private static String compressString(String s, int num, String result) {

        if (s.length() < num) {
            return result + s;
        }

        String subStr = s.substring(0, num);

        int cnt = 0;

        String remain = s;

        while (remain.length() >= num && subStr.equals(remain.substring(0, num))) {
            remain = remain.substring(num);
            cnt++;
        }

        if (cnt == 1) {
            return compressString(s.substring(num * cnt), num, result + subStr);
        }
        return compressString(s.substring(num * cnt), num, result + cnt + subStr);
    }
}