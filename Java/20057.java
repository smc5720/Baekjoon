import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int[] dr = {0, 1, 0, -1};
    public static final int[] dc = {-1, 0, 1, 0};
    public static int[] sr = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
    public static int[] sc = {0, -1, 0, 1, -2, -1, 0, 1, 0};
    public static int[] sp = {2, 10, 7, 1, 5, 10, 7, 1, 2};
    public static int N, r, c, outSand;
    public static Tile[][] tiles;

    public static class Tile {
        int r, c, sand;

        public Tile(int r, int c, int sand) {
            this.r = r;
            this.c = c;
            this.sand = sand;
        }

        public void moveSand(int d) {
            int remain = sand;

            for (int i = 0; i < 9; i++) {
                int tr = this.r + sr[i];
                int tc = this.c + sc[i];
                int ts = sand * sp[i] / 100;

                if (visitable(tr, tc)) {
                    tiles[tr][tc].sand += ts;
                    remain -= ts;
                } else {
                    outSand += ts;
                    remain -= ts;
                }
            }

            int tr = this.r + dr[d];
            int tc = this.c + dc[d];

            if (visitable(tr, tc)) {
                tiles[tr][tc].sand += remain;
                sand = 0;
            } else {
                outSand += remain;
                sand = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        r = N / 2 + 1;
        c = N / 2 + 1;
        outSand = 0;

        int d = 0;
        int limit = 1;
        int dirCnt = 0;
        int moved = 0;

        tiles = new Tile[N + 1][N + 1];

        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int x = 1; x <= N; x++) {
                int sand = Integer.parseInt(st.nextToken());
                tiles[y][x] = new Tile(y, x, sand);
            }
        }

        // printMap();

        while (r != 1 || c != 1) {
            r = r + dr[d];
            c = c + dc[d];
            tiles[r][c].moveSand(d);
            moved += 1;
            // printMap();

            if (moved == limit) {
                dirCnt += 1;
                moved = 0;

                if (dirCnt == 2) {
                    dirCnt = 0;
                    limit += 1;
                }

                d = (d + 1) % 4;
                turnSandForm();
            }
        }

        bw.write(String.valueOf(outSand));
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean visitable(int tr, int tc) {
        return (1 <= tr) && (tr <= N) && (1 <= tc) && (tc <= N);
    }

    public static void turnSandForm() {
        for (int i = 0; i < 9; i++) {
            int tmp = sr[i];
            sr[i] = -sc[i];
            sc[i] = tmp;
        }
    }

    public static void printMap() {
        System.out.println();

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (y == r && x == c) {
                    System.out.printf("■ ");
                } else {
                    System.out.printf("□ ");
                }
            }

            System.out.printf("\t\t\t");

            for (int x = 1; x <= N; x++) {
                System.out.printf("%5d ", tiles[y][x].sand);
            }

            System.out.println();
        }
        System.out.println();
    }
}