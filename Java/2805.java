import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int maxHeight = 0;
    private static int[] tree;

    private static long binarySearch() {
        long start = 0;
        long end = maxHeight;
        long answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long length = getTreeLength(mid);

            if (M > length) {
                end = mid - 1;
            } else {
                answer = mid;
                start = mid + 1;
            }
        }

        return answer;
    }

    private static long getTreeLength(long H) {
        long length = 0;

        for (int i = 1; i <= N; i++) {
            if (H < tree[i]) {
                length += tree[i] - H;
            }
        }

        return length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, tree[i]);
        }

        long answer = binarySearch();

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}