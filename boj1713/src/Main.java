import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<Candidate> list;

    static Map<Integer, Candidate> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfPicture = Integer.parseInt(br.readLine());

        int numOfRecommend = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new HashSet<>();
        map = new HashMap<>();

        int[] recommended = new int[numOfRecommend];
        for (int i = 0; i < numOfRecommend; i++) {
            recommended[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < numOfRecommend; i++) {
            if (list.size() < numOfPicture) {
                if (!map.containsKey(recommended[i])) {
                    Candidate candi = new Candidate(recommended[i], 1, i);
                    map.put(recommended[i], candi);
                    list.add(candi);
                } else {
                    if (list.contains(map.get(recommended[i]))) {
                        map.get(recommended[i]).addRecommend();
                    } else {
                        list.add(map.get(recommended[i]));
                        map.get(recommended[i]).setOrder(i);
                    }
                }
            } else {
                Candidate candi;
                if (!map.containsKey(recommended[i])) {
                    candi = new Candidate(recommended[i], 1, i);
                    map.put(recommended[i], candi);
                } else {
                    candi = map.get(recommended[i]);
                    if (!list.contains(candi)) {
                        candi.setOrder(i);
                    }
                }

                if (list.contains(candi)) {
                    map.get(recommended[i]).addRecommend();
                } else {
                    Candidate[] candidates = new Candidate[list.size()];
                    int idx = 0;
                    for (var item : list) {
                        candidates[idx++] = item;
                    }
                    Arrays.sort(candidates);
                    candidates[0].removeCandidate();
                    list.remove(candidates[0]);
                    list.add(candi);
                }
            }
        }
        Comparator<Candidate> comp = new Comparator<Candidate>() {
            @Override
            public int compare(Candidate o1, Candidate o2) {
                return o1.getNum() - o2.getNum();
            }
        };
        Candidate[] candidates = new Candidate[list.size()];
        int idx = 0;
        for (var item : list) {
            candidates[idx++] = item;
        }
        Arrays.sort(candidates, comp);
        for (int i = 0; i < candidates.length; i++) {
            System.out.print(candidates[i].getNum() + " ");
        }
    }
}

class Candidate implements Comparable<Candidate> {
    private int num;
    private int numRecommended;
    private int order;

    public Candidate(int num, int numRecommended, int order) {
        this.num = num;
        this.numRecommended = numRecommended;
        this.order = order;
    }

    public int getNum() {
        return num;
    }

    public int getNumRecommended() {
        return numRecommended;
    }

    public int getOrder() {
        return order;
    }

    public void addRecommend() {
        this.numRecommended++;
    }
    public void removeCandidate() {
        this.numRecommended = 1;
        this.order = 0;
    }

    @Override
    public int compareTo(Candidate o) {
        if (this.numRecommended != o.numRecommended) {
            return this.getNumRecommended() - o.getNumRecommended();
        } else {
            return this.getOrder() - o.getOrder();
        }
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
