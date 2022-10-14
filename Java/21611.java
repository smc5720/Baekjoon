import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static boolean printYn = false;
    public static int N, M, R, C, answer;
    public static int[][] map, tmp;
    public static HashMap<Point, Integer> pointToIndex;
    public static HashMap<Integer, Point> indexToPoint;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = (N + 1) / 2;
        C = (N + 1) / 2;
        answer = 0;

        map = new int[N + 1][N + 1];
        tmp = new int[N + 1][N + 1];
        pointToIndex = new HashMap<>();
        indexToPoint = new HashMap<>();

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            destroy(d, s);
            fetch();

            while (boom()) {
                fetch();
            }

            change();
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
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

    public static void destroy(int d, int s) {
        int[] dr = {0, -1, 1, 0, 0};
        int[] dc = {0, 0, 0, -1, 1};

        for (int i = 1; i <= s; i++) {
            int tr = R + dr[d] * i;
            int tc = C + dc[d] * i;
            map[tr][tc] = 0;
        }

        if (printYn) {
            System.out.printf("블록 파괴(d: %d, s: %d)\n", d, s);
            printMap();
        }
    }

    public static void init() {
        int r = R;
        int c = C;
        int d = 0;
        int limit = 1;
        int moved = 0;
        int dirCnt = 0;
        int idx = 0;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {-1, 0, 1, 0};

        while (r != 1 || c != 1) {
            r += dr[d];
            c += dc[d];
            idx += 1;
            moved += 1;

            if (moved == limit) {
                d = (d + 1) % 4;
                moved = 0;
                dirCnt += 1;

                if (dirCnt == 2) {
                    limit += 1;
                    dirCnt = 0;
                }
            }

            pointToIndex.put(new Point(r, c), idx);
            indexToPoint.put(idx, new Point(r, c));
        }
    }

    public static void fetch() {
        int r = R;
        int c = C;
        int d = 0;
        int limit = 1;
        int moved = 0;
        int dirCnt = 0;
        int readPoint = 0;
        int writePoint = 0;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {-1, 0, 1, 0};

        while (r != 1 || c != 1) {
            r += dr[d];
            c += dc[d];
            moved += 1;

            if (moved == limit) {
                d = (d + 1) % 4;
                moved = 0;
                dirCnt += 1;

                if (dirCnt == 2) {
                    limit += 1;
                    dirCnt = 0;
                }
            }

            readPoint += 1;

            if (map[r][c] > 0) {
                writePoint += 1;
                Point p = indexToPoint.get(writePoint);
                map[p.x][p.y] = map[r][c];
            }
        }

        while (readPoint > writePoint) {
            writePoint += 1;
            Point p = indexToPoint.get(writePoint);
            map[p.x][p.y] = 0;
        }

        if (printYn) {
            System.out.println("구슬 땡기기");
            printMap();
        }
    }

    public static boolean boom() {
        boolean boomed = false;
        int r = R;
        int c = C;
        int d = 0;
        int limit = 1;
        int moved = 0;
        int dirCnt = 0;
        int start = 0;
        int end = 0;
        int kind = 0;
        int idx = 0;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {-1, 0, 1, 0};

        while (r != 1 || c != 1) {
            r += dr[d];
            c += dc[d];
            idx += 1;
            moved += 1;

            if (moved == limit) {
                d = (d + 1) % 4;
                moved = 0;
                dirCnt += 1;

                if (dirCnt == 2) {
                    limit += 1;
                    dirCnt = 0;
                }
            }

            if (kind != map[r][c]) {
                int balls = end - start + 1;

                if (balls >= 4) {
                    for (int i = start; i <= end; i++) {
                        Point p = indexToPoint.get(i);
                        map[p.x][p.y] = 0;
                        boomed = true;
                    }

                    answer += kind * balls;
                }

                start = idx;
                kind = map[r][c];
            }

            end += 1;
        }

        if (printYn) {
            System.out.println("구슬 폭발");
            printMap();
        }

        return boomed;
    }

    public static void change() {
        int r = R;
        int c = C;
        int d = 0;
        int limit = 1;
        int moved = 0;
        int dirCnt = 0;
        int start = 0;
        int end = 0;
        int kind = 0;
        int idx = 0;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {-1, 0, 1, 0};

        tmp = new int[N + 1][N + 1];
        int tmpIdx = 1;

        while (r != 1 || c != 1) {
            r += dr[d];
            c += dc[d];
            idx += 1;
            moved += 1;

            if (moved == limit) {
                d = (d + 1) % 4;
                moved = 0;
                dirCnt += 1;

                if (dirCnt == 2) {
                    limit += 1;
                    dirCnt = 0;
                }
            }

            if (kind != map[r][c]) {
                if (kind != 0) {
                    int A = end - start + 1;
                    int B = kind;

                    Point p = indexToPoint.get(tmpIdx++);
                    tmp[p.x][p.y] = A;

                    if (tmpIdx >= N * N) {
                        break;
                    }

                    p = indexToPoint.get(tmpIdx++);
                    tmp[p.x][p.y] = B;

                    if (tmpIdx >= N * N) {
                        break;
                    }
                }

                start = idx;
                kind = map[r][c];
            }

            end += 1;
        }

        map = tmp;

        if (printYn) {
            System.out.println("구슬 변경");
            printMap();
        }
    }
}