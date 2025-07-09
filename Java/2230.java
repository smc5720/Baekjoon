import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, minVal;
    private static ArrayList<Integer> A;

    public static void twoPointer() {
        int left = 0;
        int right = 1;

        while (left < N && right < N) {
            int diff = A.get(right) - A.get(left);

            if (diff >= M) {
                minVal = Math.min(minVal, diff);
                left += 1;
            } else {
                right += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minVal = Integer.MAX_VALUE;
        A = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(A);
        twoPointer();

        bw.write(minVal + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}