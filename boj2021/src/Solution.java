import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

    static int minTransfer;

    static HashMap<Integer, SubwayNode> subways;

    static HashMap<Integer, SubwayNode> isVisited;

    Solution() {
        minTransfer = Integer.MAX_VALUE;

        subways = new HashMap<>();

        isVisited = new HashMap<>();
    }

    private static void findMinTransfer(SubwayNode sub, int trasnferCnt, String end){

        Queue<SubwayNode> queue = new LinkedList<>();

        queue.add(sub);

        while(!queue.isEmpty()){
            SubwayNode nextSub = queue.poll();
            if (nextSub.getNameOfStations().contains(end)) {
                minTransfer = trasnferCnt;
                break;
            }

            for(int i = 0; i < nextSub.getStations().size(); i++){
                String station = sub.getStations().get(i);
                for (int j = 0; j < subways.size(); j++){
                    if (j + 1 != nextSub.getLine() && subways.get(j + 1).getNameOfStations().contains(station))
                    queue.add(subways.get(j + 1));

                }
            }
            trasnferCnt++;
        }
    }

    private static void findMinTransferRecursive(SubwayNode sub, int transferCnt, String end) {

        isVisited.put(sub.getLine(), sub);

        if (sub.getNameOfStations().contains(end)) {
            if (transferCnt <= minTransfer) {
                minTransfer = transferCnt;
            }
            return;
        }
        for (int i =0; i < sub.getStations().size(); i++) {
            String station = sub.getStations().get(i);
            for (int j = 0; j < subways.size(); j++) {
                if (subways.get(j + 1).getNameOfStations().contains(station) && !isVisited.containsKey(j + 1)) {
                    findMinTransfer(subways.get(j + 1), transferCnt + 1, end);
                    isVisited.remove(j + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfStations = Integer.parseInt(st.nextToken());

        int numOfLines = Integer.parseInt(st.nextToken());

        minTransfer = Integer.MAX_VALUE;

        subways = new HashMap<>();

        isVisited = new HashMap<>();

        for (int i = 0; i < numOfLines; i++){
            String strLine = br.readLine();

            String[] lines = strLine.split(" ");

            SubwayNode sub = new SubwayNode(i + 1);

            for (int j = 0; j < lines.length - 1; j++){
                sub.addStation(j, lines[j]);
                sub.addStationName(lines[j]);
            }
            subways.put(i + 1, sub);
        }

        var entry = br.readLine().split(" ");

        String start = entry[0];

        String end = entry[1];

        for (int i = 0; i < subways.size(); i++){
            if (subways.get(i + 1).getNameOfStations().contains(start)) {
                findMinTransferRecursive(subways.get(i + 1), 0, end);
                break;
            }
        }

        if (minTransfer == Integer.MAX_VALUE){
            minTransfer = -1;
        }
        System.out.println(minTransfer);
    }
}

class SubwayNode {

    private final int line;

    private HashMap<Integer, String> stations;

    private HashSet<String> nameOfStations;

    SubwayNode(final int line) {
        this.line = line;

        stations = new HashMap<>();

        nameOfStations = new HashSet<>();
    }

    public int getLine() {
        return line;
    }

    public HashMap<Integer, String> getStations() {
        return stations;
    }

    public void addStation(int index, String station) {
        this.stations.put(index, station);
    }

    public void addStationName(String station) {
        this.nameOfStations.add(station);
    }

    public HashSet<String> getNameOfStations() {
        return nameOfStations;
    }
}