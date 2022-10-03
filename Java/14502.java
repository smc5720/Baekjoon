import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static boolean comment = false;
    public static int N, M, answer;
    public static int[] wall = new int[3];
    public static final int[] dy = {0, 0, -1, 1};
    public static final int[] dx = {-1, 1, 0, 0};
    public static boolean[][] visited;
    public static int[][] baseMap, map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;

        visited = new boolean[N][M];
        baseMap = new int[N][M];
        map = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                baseMap[y][x] = Integer.parseInt(st.nextToken());
                map[y][x] = baseMap[y][x];
            }
        }

        simulate(0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void initMap() {
        if (comment) {
            System.out.println("맵을 초기화합니다.");
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                map[y][x] = baseMap[y][x];
                visited[y][x] = false;
            }
        }
    }

    public static void simulate(int idx, int start) {
        if (idx < 3 && start >= N * M) {
            return;
        }

        if (idx >= 3) {
            if (setWall(3)) {
                spreadVirus();
                checkSafeArea();
            }

            initMap();
            return;
        }

        for (int i = start; i < N * M; i++) {
            wall[idx] = i;
            simulate(idx + 1, i + 1);
        }
    }

    public static boolean setWall(int val) {
        for (int i = 0; i < 3; i++) {
            int ty = wall[i] / M;
            int tx = wall[i] % M;

            if (map[ty][tx] == 0) {
                map[ty][tx] = val;
            } else {
                return false;
            }
        }

        if (comment) {
            System.out.printf("벽을 %d(으)로 초기화합니다.\n", val);
            printMap();
        }

        return true;
    }

    public static void spreadVirus() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 2) {
                    spread(y, x);
                }
            }
        }

        if (comment) {
            System.out.println("바이러스 살포");
            printMap();
        }
    }

    public static void spread(int y, int x) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Point pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ty = pos.y + dy[i];
                int tx = pos.x + dx[i];

                if (visitable(ty, tx)) {
                    if (map[ty][tx] == 0 && !visited[ty][tx]) {
                        map[ty][tx] = 4;
                        queue.add(new Point(tx, ty));
                    }
                }
            }
        }
    }

    public static boolean visitable(int y, int x) {
        return (0 <= y) && (y < N) && (0 <= x) && (x < M);
    }

    public static void checkSafeArea() {
        int total = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0) {
                    total += 1;
                }
            }
        }

        if (comment) {
            System.out.printf("안전구역 크기: %d\n\n", total);
        }

        answer = Math.max(total, answer);
    }

    public static void printMap() {
        System.out.println();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0) {
                    System.out.printf("  ");
                } else if (map[y][x] == 1) {
                    System.out.printf("□ ");
                } else if (map[y][x] == 2) {
                    System.out.printf("* ");
                } else if (map[y][x] == 3) {
                    System.out.printf("■ ");
                } else {
                    System.out.printf("# ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}