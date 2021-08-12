import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfProduct = Integer.parseInt(st.nextToken());
        int maximumWeight  = Integer.parseInt(st.nextToken());

        int [][] memo = new int[numOfProduct][maximumWeight + 1];

        ArrayList<Product> productList = new ArrayList<>();

        for (int i = 0; i < numOfProduct; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value  = Integer.parseInt(st.nextToken());
            productList.add(new Product(weight, value));
        }

        for (int i = 0; i < numOfProduct; i++) {
            Product product = productList.get(i);

            for (int j = 0; j <= maximumWeight; j++) {
                int capability = j;

                if (i == 0) {
                    if (capability >= product.getWeight()) {
                        memo[i][j] = product.getValue();
                    }
                    continue;
                }
                if (capability >= product.getWeight()) {
                    if (product.getValue() >= memo[i - 1][j]) {
                        memo[i][j] = product.getValue();
                    } else {
                        memo[i][j] = memo[i - 1][j];
                    }
                    if (product.getValue() + memo[i - 1][capability - product.getWeight()] >= memo[i - 1][j]) {
                        memo[i][j] = product.getValue() + memo[i - 1][capability - product.getWeight()];
                    }
                } else {
                    memo[i][j] = memo[i - 1][j];
                }

            }
        }

        System.out.println(memo[numOfProduct - 1][maximumWeight]);

    }
}

class Product {

    private final int weight;

    private final int value;

    Product(int weight, int value) {

        this.weight = weight;

        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}
