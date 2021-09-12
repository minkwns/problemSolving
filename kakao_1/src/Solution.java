import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    Map<String, Set<String>> reportlist;

    Map<String, Set<String>> reportFrom;

    public int[] solution(String[] id_list, String[] report, int k) {
        reportlist = new HashMap<>();

        reportFrom = new HashMap<>();

        int[] answer = new int[id_list.length];

        for (int i = 0; i < report.length; i++) {
            String[] fromTo = report[i].split(" ");
            reportUser(fromTo[0], fromTo[1]);
            if (!reportFrom.containsKey(fromTo[0])) {
                reportFrom.put(fromTo[0], new HashSet<>());
                reportFrom.get(fromTo[0]).add(fromTo[1]);
            } else {
                reportFrom.get(fromTo[0]).add(fromTo[1]);
            }
        }

        Set<String> reported = new HashSet<>();

        for (var entry: reportlist.entrySet()) {
            if (entry.getValue() != null && entry.getValue().size() >= k) {
                reported.add(entry.getKey());
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            int cnt = 0;
            if (reportFrom.get(id_list[i]) != null) {
                for (var entry : reportFrom.get(id_list[i])) {
                    if (reported.contains(entry)) {
                        cnt++;
                    }
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }


    public void reportUser(String from, String to) {
        if (!reportlist.containsKey(to)) {
            reportlist.put(to, new HashSet<>());
            reportlist.get(to).add(from);
        } else {
            reportlist.get(to).add(from);
        }
    }
}