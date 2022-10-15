import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static boolean printYn = false;
    public static int N, K;
    public static int[][] map, backup;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        backup = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i <= N; i++) {
            map[N][i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        while (getFishDiff() > K) {
            cnt += 1;
            addFish();
            stackBowl();
            balanceFish();
            putBowl();
            foldBowl();
            balanceFish();
            putBowl();
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void addFish() {
        int minVal = Integer.MAX_VALUE;

        for (int c = 1; c <= N; c++) {
            minVal = Math.min(map[N][c], minVal);
        }

        for (int c = 1; c <= N; c++) {
            if (map[N][c] == minVal) {
                map[N][c] += 1;
            }
        }

        if (printYn) {
            System.out.println("물고기 추가");
            printMap();
        }
    }

    public static void stackBowl() {
        // 가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항의 위에 올려 놓는다.
        map[N - 1][2] = map[N][1];
        map[N][1] = 0;

        // 2개 이상 쌓여있는 어항을 모두 공중 부양시킨 다음, 전체를 시계방향으로 90도 회전시킨다.
        // 이후 공중 부양시킨 어항을 바닥에 있는 어항의 위에 올려놓는다.
        int sr = N;
        int sc = 2;
        int dr = sr;
        int dc = sc;

        while (sr - dr <= N - (dc + 1)) {
            for (int i = sr; i >= 1; i--) {
                if (map[i][sc] > 0) {
                    dr = i;
                }
            }

            for (int i = sc; i <= N; i++) {
                if (map[dr][i] > 0) {
                    dc = i;
                }
            }

            // System.out.printf("사각형은 좌측 하단(%d, %d) + 우측 상단(%d, %d)\n", sr, sc, dr, dc);

            if (sr - dr > N - (dc + 1)) {
                break;
            }

            int rpr = N - 1;
            int rpc = dc + 1;

            for (int c = dc; c >= sc; c--) {
                for (int r = sr; r >= dr; r--) {
                    map[rpr][rpc++] = map[r][c];
                    map[r][c] = 0;
                }

                rpc = dc + 1;
                rpr -= 1;
            }

            sr = N;
            sc = dc + 1;
        }

        if (printYn) {
            System.out.println("어항 쌓기");
            printMap();
        }
    }

    public static void balanceFish() {
        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};
        backup = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (map[r][c] > 0) {
                    for (int i = 0; i < 4; i++) {
                        int tr = r + dr[i];
                        int tc = c + dc[i];

                        if (visitable(tr, tc) && map[tr][tc] > 0) {
                            int d = Math.abs(map[r][c] - map[tr][tc]) / 5;

                            if (d > 0) {
                                if (map[r][c] > map[tr][tc]) {
                                    backup[r][c] -= d;
                                } else {
                                    backup[r][c] += d;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                map[r][c] += backup[r][c];
                backup[r][c] = 0;
            }
        }

        if (printYn) {
            System.out.println("물고기 수 조절");
            printMap();
        }
    }

    public static void putBowl() {
        int idx = 1;

        for (int c = 1; c <= N; c++) {
            for (int r = N; r >= 1; r--) {
                if (map[r][c] > 0) {
                    backup[N][idx++] = map[r][c];
                }
            }
        }

        map = backup;

        if (printYn) {
            System.out.println("어항 내려놓기");
            printMap();
        }
    }

    public static void foldBowl() {
        for (int c = N / 2; c >= 1; c--) {
            map[N - 1][N - c + 1] = map[N][c];
            map[N][c] = 0;
        }

        for (int c = N / 4 * 3; c > N / 2; c--) {
            for (int r = N - 1; r <= N; r++) {
                int n = N + N / 2 + 1;
                int tr = 2 * N - 3 - r;
                int tc = n - c;
                map[tr][tc] = map[r][c];
                map[r][c] = 0;
            }
        }

        if (printYn) {
            System.out.println("어항 접기");
            printMap();
        }
    }

    public static int getFishDiff() {
        int maxVal = 0;
        int minVal = Integer.MAX_VALUE;

        for (int c = 1; c <= N; c++) {
            maxVal = Math.max(map[N][c], maxVal);
            minVal = Math.min(map[N][c], minVal);
        }

        if (printYn) {
            System.out.printf("max(%d) - min(%d) = %d\n", maxVal, minVal, maxVal - minVal);
        }

        return maxVal - minVal;
    }

    public static boolean visitable(int tr, int tc) {
        return (1 <= tr) && (tr <= N) && (1 <= tc) && (tc <= N);
    }

    public static void printMap() {
        System.out.println();

        for (int r = 0; r <= N; r++) {
            for (int c = 0; c <= N; c++) {
                if (r == 0 && c == 0) {
                    System.out.printf("   ");
                    continue;
                }

                if (r == 0) {
                    System.out.printf("%-3d", c);
                    continue;
                }

                if (c == 0) {
                    System.out.printf("%-3d", r);
                    continue;
                }

                if (map[r][c] > 0) {
                    System.out.printf("%-3d", map[r][c]);
                } else {
                    System.out.printf("-  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}