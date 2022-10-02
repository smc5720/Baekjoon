import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static int N, M, answer;
    public static int[][] map;
    public static int[][] by = {{0, 0, 0, 0}, {0, 0, 1, 1}, {0, 1, 2, 2}, {0, 1, 1, 2}, {0, 0, 1, 0}};
    public static int[][] bx = {{0, 1, 2, 3}, {0, 1, 0, 1}, {0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 2}};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = -1;

        map = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int shape = 0; shape < 5; shape++) {
            calculate(shape);
            symmetry(shape);
            calculate(shape);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void rotate(int shape) {
        for (int i = 0; i < 4; i++) {
            int tmp = bx[shape][i];
            bx[shape][i] = -by[shape][i];
            by[shape][i] = tmp;
        }
    }

    public static void symmetry(int shape) {
        // 대칭
        for (int i = 0; i < 4; i++) {
            bx[shape][i] = -bx[shape][i];
        }
    }

    public static void calculate(int shape) {
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (locatable(shape, y, x)) {
                        update(shape, y, x);
                    }
                }
            }

            rotate(shape);
        }
    }

    public static boolean locatable(int sh, int y, int x) {
        for (int i = 0; i < 4; i++) {
            int ty = y + by[sh][i];
            int tx = x + bx[sh][i];

            if (!(0 <= ty && ty < N && 0 <= tx && tx < M)) {
                return false;
            }
        }

        return true;
    }

    public static void update(int sh, int y, int x) {
        int sum = 0;

        for (int i = 0; i < 4; i++) {
            int ty = y + by[sh][i];
            int tx = x + bx[sh][i];
            sum += map[ty][tx];
        }

        answer = Math.max(answer, sum);
    }
}