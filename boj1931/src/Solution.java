import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        Node[] arr = new Node[num];
        for(int i = 0; i < num; i++) {

            int startTime = sc.nextInt();

            int endTime = sc.nextInt();

            arr[i] = new Node(startTime, endTime);
        }

        mergeSort2(arr, num);

        mergeSort(arr, num);

        Stack<Node> stack = new Stack<>();
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {

            if (stack.peek().endTime <= arr[i].startTime) {
                stack.push(arr[i]);
            }
        }
        System.out.println(stack.size());
    }


    public static int partition(Node[] arr, int begin, int end) {

        Node pivot = arr[end];
        int i = begin - 1;
        for (int  j = begin; j < end; j++) {
            if (arr[j].startTime <= pivot.startTime) {
                i++;

                Node swap = arr[j];
                arr[i] = arr[j];
                arr[j] = swap;
            }
        }

        Node swap = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swap;

        return i + 1;
    }

    public static void quickSort(Node[] arr, int begin, int end) {
        if (begin < end) {
            int pivot = partition(arr, begin, end);

            quickSort(arr, begin, pivot - 1);

            quickSort(arr, pivot + 1, end);
        }

    }

    public static void mergeSort(Node[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Node[] l = new Node[mid];
        Node[] r = new Node[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }


    public static void merge(
            Node[] a, Node[] l, Node[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].endTime <= r[j].endTime) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void mergeSort2(Node[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Node[] l = new Node[mid];
        Node[] r = new Node[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort2(l, mid);
        mergeSort2(r, n - mid);

        merge2(a, l, r, mid, n - mid);
    }

    public static void merge2(
            Node[] a, Node[] l, Node[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].startTime <= r[j].startTime) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}

class Node {
    int startTime;

    int endTime;

    Node(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
