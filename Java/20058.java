import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int[] dr = {-1, 1, 0, 0};
    public static final int[] dc = {0, 0, -1, 1};
    public static int N, Q, iceSize;
    public static int[][] map, melt;
    public static final boolean printYn = false;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);

        Q = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        melt = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        if (printYn) {
            printMap();
        }

        st = new StringTokenizer(br.readLine(), " ");

        for (int q = 0; q < Q; q++) {
            int L = Integer.parseInt(st.nextToken());
            storm(L);
            checkIce();
            meltIce();
        }

        bw.write(String.valueOf(getTotalIce()));
        bw.newLine();
        bw.write(String.valueOf(getMaxIce()));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void storm(int l) {
        int L = (int) Math.pow(2, l);
        int M = N / L;

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                int r = L * y + 1;
                int c = L * x + 1;
                rotate(r, c, L);
            }
        }

        if (printYn) {
            System.out.println("파이어스톰 시전");
            printMap();
        }
    }

    public static void rotate(int r, int c, int L) {
        if (L <= 0) {
            return;
        }

        int sr = r;
        int sc = c;
        int dr = r + L - 1;
        int dc = c + L - 1;
        int[] tmp = new int[L - 1];

        // UP → tmp
        for (int i = 0; i < L - 1; i++) {
            tmp[i] = map[sr][sc + i];
        }

        // LEFT → UP
        for (int i = sc; i < dc; i++) {
            int m = i - sc;
            map[sr][i] = map[dr - m][sc];
        }

        // DOWN → LEFT
        for (int i = dr; i > sr; i--) {
            int m = dr - i;
            map[i][sc] = map[dr][dc - m];
        }

        // RIGHT → DOWN
        for (int i = dc; i > sc; i--) {
            int m = dc - i;
            map[dr][i] = map[sr + m][dc];
        }

        // tmp(UP) → RIGHT
        for (int i = sr; i < dr; i++) {
            int m = i - sr;
            map[i][dc] = tmp[m];
        }

        rotate(r + 1, c + 1, L - 2);
    }

    public static void checkIce() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int ice = 0;

                for (int i = 0; i < 4; i++) {
                    int tr = r + dr[i];
                    int tc = c + dc[i];

                    if (visitable(tr, tc)) {
                        if (map[tr][tc] > 0) {
                            ice += 1;
                        }
                    }
                }

                if (ice < 3 && map[r][c] > 0) {
                    melt[r][c] -= 1;
                }
            }
        }
    }

    public static void meltIce() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                map[r][c] += melt[r][c];
                melt[r][c] = 0;
            }
        }

        if (printYn) {
            System.out.println("얼음 녹음");
            printMap();
        }
    }

    public static int getTotalIce() {
        int sum = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                sum += map[r][c];
            }
        }

        return sum;
    }

    public static int getMaxIce() {
        boolean[][] visited = new boolean[N + 1][N + 1];
        int maxVal = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (!visited[r][c] && map[r][c] > 0) {
                    iceSize = 0;
                    DFS(r, c, visited);
                }

                maxVal = Math.max(maxVal, iceSize);
            }
        }

        return maxVal;
    }

    public static void DFS(int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        iceSize += 1;

        for (int i = 0; i < 4; i++) {
            int tr = r + dr[i];
            int tc = c + dc[i];

            if (visitable(tr, tc) && !visited[tr][tc] && map[tr][tc] > 0) {
                DFS(tr, tc, visited);
            }
        }
    }

    public static boolean visitable(int tr, int tc) {
        return (1 <= tr) && (tr <= N) && (1 <= tc) && (tc <= N);
    }

    public static void printMap() {
        System.out.println();

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                System.out.printf("%4d", map[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
}