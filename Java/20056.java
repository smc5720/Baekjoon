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
    public static int N, M, K;
    public static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static Tile[][] tiles;

    public static class FireBall {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public void move() {
            int tr = r + s * dr[d];
            int tc = c + s * dc[d];

            while (tr < 0) {
                tr += N;
            }

            while (tc < 0) {
                tc += N;
            }

            tr %= N;
            tc %= N;

            if (tr == 0) {
                tr = N;
            }

            if (tc == 0) {
                tc = N;
            }

            tiles[tr][tc].movedBalls.add(new FireBall(tr, tc, m, s, d));
        }
    }

    public static class Tile {
        int r, c;
        Queue<FireBall> fireBalls, movedBalls;

        public Tile(int r, int c) {
            this.r = r;
            this.c = c;
            fireBalls = new LinkedList<>();
            movedBalls = new LinkedList<>();
        }

        public void move() {
            while (!fireBalls.isEmpty()) {
                FireBall fb = fireBalls.poll();
                fb.move();
            }
        }

        public void stop() {
            fireBalls = movedBalls;
            movedBalls = new LinkedList<>();
        }

        public void merge() {
            if (fireBalls.size() < 2) {
                return;
            }

            int tm = 0;
            int ts = 0;
            int cnt = fireBalls.size();
            boolean dir = true;
            int td = fireBalls.peek().d % 2;

            while (!fireBalls.isEmpty()) {
                FireBall fb = fireBalls.poll();
                tm += fb.m;
                ts += fb.s;

                if (td != fb.d % 2) {
                    dir = false;
                }
            }

            tm /= 5;
            ts /= cnt;

            if (tm == 0) {
                return;
            }

            if (dir) {
                fireBalls.add(new FireBall(r, c, tm, ts, 0));
                fireBalls.add(new FireBall(r, c, tm, ts, 2));
                fireBalls.add(new FireBall(r, c, tm, ts, 4));
                fireBalls.add(new FireBall(r, c, tm, ts, 6));
            } else {
                fireBalls.add(new FireBall(r, c, tm, ts, 1));
                fireBalls.add(new FireBall(r, c, tm, ts, 3));
                fireBalls.add(new FireBall(r, c, tm, ts, 5));
                fireBalls.add(new FireBall(r, c, tm, ts, 7));
            }
        }

        public int getMass() {
            int sum = 0;

            for (int i = 0; i < fireBalls.size(); i++) {
                FireBall fb = fireBalls.poll();
                sum += fb.m;
                fireBalls.add(fb);
            }

            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tiles = new Tile[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tiles[r][c] = new Tile(r, c);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            FireBall fb = new FireBall(r, c, m, s, d);
            tiles[r][c].fireBalls.add(fb);
        }

        for (int k = 0; k < K; k++) {
            commandMove();
            commandMerge();
        }

        bw.write(String.valueOf(getMass()));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void commandMove() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tiles[r][c].move();
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tiles[r][c].stop();
            }
        }
    }

    public static void commandMerge() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                tiles[r][c].merge();
            }
        }
    }

    public static int getMass() {
        int sum = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                sum += tiles[r][c].getMass();
            }
        }

        return sum;
    }
}