import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Map<Integer, Student> map;

    static Set<Student> set;

    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numStudent = Integer.parseInt(st.nextToken());

        int numCompare = Integer.parseInt(st.nextToken());

        map = new HashMap<>();

        set = new HashSet<>();

        list = new LinkedList<>();

        for (int i = 1; i <= numStudent; i++) {
            map.put(i, new Student(i));
        }
        int[] visited = new int[numStudent + 1];

        for (int i = 0; i < numCompare; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map.get(from).addNextStudent(map.get(to));
        }

        for (int i = 1; i <= numStudent; i++) {
            if (!set.contains(map.get(i))) {
                dfs(map.get(i));
            }
        }

        for (Integer i : list) {
            System.out.println(i);
        }
    }

    public static void dfs(Student student) {
        set.add(student);

        for (Student nextStudent : student.nextStudents) {
            if (!set.contains(nextStudent)) {
                dfs(nextStudent);
            }
        }
        list.add(0, student.num);
    }
}

class Student {
    int num;
    List<Student> nextStudents;


    public Student(int num) {
        this.num = num;
        this.nextStudents = new ArrayList<>();
    }

    public void addNextStudent(Student student) {
        nextStudents.add(student);
    }

}