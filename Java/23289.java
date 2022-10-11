import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static int R, C, K, W, chocolate;
    public static Tile[][] tiles;
    public static ArrayList<Heater> heaters;
    public static ArrayList<Point> searchPoints;
    public static boolean[][][] walls;
    /**
     * 1: 방향이 오른쪽인 온풍기가 있음
     * 2: 방향이 왼쪽인 온풍기가 있음
     * 3: 방향이 위인 온풍기가 있음
     * 4: 방향이 아래인 온풍기가 있음
     */
    public static int[][] dr = {{0, 0, 0}, {-1, 0, 1}, {1, 0, -1}, {-1, -1, -1}, {1, 1, 1}};
    public static int[][] dc = {{0, 0, 0}, {1, 1, 1}, {-1, -1, -1}, {-1, 0, 1}, {1, 0, -1}};
    public static int[] dirR = {0, 0, 0, -1, 1};
    public static int[] dirC = {0, 1, -1, 0, 0};
    public static boolean printYn = false;

    public static class Heater {
        int r, c, d;

        public Heater(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void windOn() {
            int tmpR = r + dirR[d];
            int tmpC = c + dirC[d];
            boolean[][] visited = new boolean[R + 1][C + 1];
            Queue<PointTemp> queue = new LinkedList<>();

            queue.add(new PointTemp(tmpR, tmpC, 5));
            tiles[tmpR][tmpC].temp += 5;

            while (!queue.isEmpty()) {
                PointTemp pt = queue.poll();

                for (int i = 0; i < 3; i++) {
                    int tr = pt.r + dr[d][i];
                    int tc = pt.c + dc[d][i];

                    if (visitable(tr, tc) && !visited[tr][tc]) {
                        if (!checkWall(new Point(pt.r, pt.c), new Point(tr, tc), d, i)) {
                            tiles[tr][tc].temp += pt.temp - 1;
                            visited[tr][tc] = true;

                            if (pt.temp - 1 > 1) {
                                queue.add(new PointTemp(tr, tc, pt.temp - 1));
                            }
                        }
                    }
                }
            }
        }
    }

    public static class Tile {
        int r, c, temp, backUp;

        public Tile(int r, int c) {
            this.r = r;
            this.c = c;
            this.temp = 0;
            this.backUp = 0;
        }

        public void calculateTemp() {
            for (int i = 1; i <= 4; i++) {
                // RIGHT
                if (i == 1 && walls[r][c][1]) {
                    continue;
                }

                // LEFT
                if (i == 2 && walls[r][c][3]) {
                    continue;
                }

                // DOWN
                if (i == 4 && walls[r][c][2]) {
                    continue;
                }

                // UP
                if (i == 3 && walls[r][c][0]) {
                    continue;
                }

                int tr = r + dirR[i];
                int tc = c + dirC[i];

                if (visitable(tr, tc)) {
                    int chgTemp = Math.abs(temp - tiles[tr][tc].temp) / 4;

                    if (chgTemp == 0) {
                        continue;
                    }

                    // System.out.printf("(%d, %d)는 (%d, %d)와 온도 조절(%d)을 진행합니다.\n", r, c, tr, tc, chgTemp);

                    if (temp > tiles[tr][tc].temp) {
                        backUp -= chgTemp;
                    } else {
                        backUp += chgTemp;
                    }
                }
            }
        }

        public void controlTemp() {
            temp += backUp;
            backUp = 0;

            if (r == 1 || c == 1 || r == R || c == C) {
                if (temp >= 1) {
                    temp -= 1;
                }
            }
        }
    }

    public static class PointTemp {
        int r, c, temp;

        public PointTemp(int r, int c, int temp) {
            this.r = r;
            this.c = c;
            this.temp = temp;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        chocolate = 0;

        tiles = new Tile[R + 1][C + 1];
        heaters = new ArrayList<>();
        searchPoints = new ArrayList<>();
        walls = new boolean[R + 1][C + 1][4];

        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int c = 1; c <= C; c++) {
                tiles[r][c] = new Tile(r, c);
                int n = Integer.parseInt(st.nextToken());

                if (n == 5) {
                    searchPoints.add(new Point(r, c));
                    continue;
                }

                if (n > 0) {
                    heaters.add(new Heater(r, c, n));
                }
            }
        }

        W = Integer.parseInt(br.readLine());

        for (int w = 0; w < W; w++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            /**
             * t == 0 (UP)
             * (x, y) ↔ (x - 1, y)
             *
             * t == 1 (RIGHT)
             * (x, y) ↔ (x, y + 1)
             *
             * t == 2 (DOWN)
             * (x, y) ↔ (x + 1, y)
             *
             * t == 3 (LEFT)
             * (x, y) ↔ (x, y - 1)
             */
            walls[x][y][t] = true;

            if (t == 0) {
                walls[x - 1][y][t + 2] = true;
            } else {
                walls[x][y + 1][t + 2] = true;
            }
        }

        do {
            HeaterOn();
            controlTemp();
            chocolate += 1;
            if (printYn) {
                System.out.println("chocolate = " + chocolate);
            }
        } while (!checkSearch() && chocolate < 101);

        bw.write(String.valueOf(chocolate));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void HeaterOn() {
        for (Heater heater : heaters) {
            heater.windOn();
        }

        if (printYn) {
            System.out.println("온풍기 가동");
            printMap();
        }
    }

    public static void controlTemp() {
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                tiles[r][c].calculateTemp();
            }
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                tiles[r][c].controlTemp();
            }
        }

        if (printYn) {
            System.out.println("온도 조절");
            printMap();
        }
    }

    public static boolean visitable(int r, int c) {
        return (1 <= r) && (r <= R) && (1 <= c) && (c <= C);
    }

    public static boolean checkWall(Point start, Point dest, int d, int i) {
        // d: RIGHT(1), LEFT(2), UP(3), DOWN(4)
        // t: UP(0), RIGHT(1), DOWN(2), LEFT(3)

        if (i == 0) {
            int[][] td = {{0, 0}, {0, 3}, {2, 1}, {3, 2}, {1, 0}};
            return walls[start.x][start.y][td[d][0]] || walls[dest.x][dest.y][td[d][1]];
        } else if (i == 1) {
            int[] td = {0, 1, 3, 0, 2};
            int t = td[d];
            return walls[start.x][start.y][t];
        } else {
            int[][] td = {{0, 0}, {2, 3}, {0, 1}, {1, 2}, {3, 0}};
            return walls[start.x][start.y][td[d][0]] || walls[dest.x][dest.y][td[d][1]];
        }
    }

    public static boolean checkSearch() {
        for (Point pt : searchPoints) {
            if (tiles[pt.x][pt.y].temp < K) {
                return false;
            }
        }

        return true;
    }

    public static void printMap() {
        System.out.println();

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                System.out.printf("%5d", tiles[r][c].temp);
            }
            System.out.println();
        }
        System.out.println();
    }
}