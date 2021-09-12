class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String newNumber = toDeposition(n, k);

        String[] input = newNumber.split("0");

        for (int i = 0; i < input.length; i++) {
            if (!input[i].equals("") && isPrime(Long.parseLong(input[i]))) {
                answer++;
            }
        }
        return answer;
    }

    boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }

    public static String toDeposition(int value, int n){
        StringBuilder builder = new StringBuilder();
        while (value >= 1) { builder.insert(0, value % n); value /= n; }
        return builder.toString();

    }

}