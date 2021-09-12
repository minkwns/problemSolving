import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {

    Map<String, Parking> map;
    public int[] solution(int[] fees, String[] records) {
        map = new HashMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] parkInfo = records[i].split(" ");

            if (!map.containsKey(parkInfo[1])) {
                map.put(parkInfo[1], new Parking(hourToMinutes(parkInfo[0]), parkInfo[1]));
            } else {
                Parking car = map.get(parkInfo[1]);
                if (parkInfo[2].equals("OUT")) {
                    car.setOut(hourToMinutes(parkInfo[0]));
                    car.setTotalTime(car.getTotalTime() + (car.getOut() - car.getIn()));
                    car.setCalculated(true);
                } else {
                    car.setIn(hourToMinutes(parkInfo[0]));
                    car.setOut(hourToMinutes("23:59"));
                    car.setCalculated(false);
                }
            }
        }
        Parking[] cars = new Parking[map.size()];

        int idx = 0;
        for (var entry : map.entrySet()) {
            cars[idx++] = entry.getValue();
        }

        Comparator<Parking> comp = new Comparator<>() {
            @Override
            public int compare(Parking o1, Parking o2) {
                return Integer.valueOf(o1.getCarNum()) - Integer.valueOf(o2.getCarNum());
            }
        };

        Arrays.sort(cars, comp);
        int[] answer = new int[map.size()];

        int freeTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        int fee = defaultFee;
        for (int i = 0; i <answer.length; i++) {
            int parkingTime;
            if (cars[i].getCalculated()) {
                parkingTime = cars[i].getTotalTime() - freeTime;
            } else {
                parkingTime = cars[i].getTotalTime() + cars[i].getOut() - cars[i].getIn() - freeTime;
            }
            if (parkingTime >= 0) {
                if (parkingTime % unitTime != 0) {
                    fee += unitFee;
                }
                fee += (parkingTime / unitTime) * unitFee;
            }
            System.out.println(fee);
            answer[i] = fee;
            fee = defaultFee;
        }


        return answer;
    }



    public int hourToMinutes(String time) {
        String[] timeAndMinute = time.split(":");
        if (timeAndMinute[0].charAt(0) == '0') {
            timeAndMinute[0] = Character.toString(timeAndMinute[0].charAt(1));
        }

        int minute = Integer.parseInt(timeAndMinute[0]) * 60 + Integer.parseInt(timeAndMinute[1]);

        return minute;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] fees = {1, 461, 1, 10};
        String[] records =  {"00:00 1234 IN"};
        sol.solution(fees, records);
    }
}



class Parking {
    private int in;

    private int out;

    private int totalTime;

    private Boolean calculated;

    private final String carNum;

    public Parking(int in, String carNum) {
        this.in = in;
        this.out = 1439;
        this.carNum = carNum;
        this.totalTime = 0;
        this.calculated = false;
    }

    public int getIn() {
        return in;
    }

    public int getOut() {
        return out;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public String getCarNum() {
        return carNum;
    }


    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public Boolean getCalculated() {
        return calculated;
    }

    public void setCalculated(Boolean calculated) {
        this.calculated = calculated;
    }
}