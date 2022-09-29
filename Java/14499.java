import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static int N, M, x, y, K;
    public static int[][] map;
    public static int FRONT = 0;
    public static int BACK = 0;
    public static int LEFT = 0;
    public static int RIGHT = 0;
    public static int UP = 0;
    public static int DOWN = 0;
    public static final int[] dx = {0, 0, 0, -1, 1};
    public static final int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < K; k++) {
            int cmd = Integer.parseInt(st.nextToken());
            int tx = x + dx[cmd];
            int ty = y + dy[cmd];

            if (visitable(tx, ty)) {
                x = tx;
                y = ty;
                rotate(cmd);

                if (map[x][y] == 0) {
                    map[x][y] = DOWN;
                } else {
                    DOWN = map[x][y];
                    map[x][y] = 0;
                }

                sb.append(UP + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean visitable(int tx, int ty) {
        return (0 <= tx) && (tx < N) && (0 <= ty) && (ty < M);
    }

    public static void rotate(int dir) {
        // 동 (FRONT, BACK 고정)
        if (dir == 1) {
            // DOWN → LEFT
            int tmp = LEFT;
            LEFT = DOWN;

            // RIGHT → DOWN
            DOWN = RIGHT;

            // UP → RIGHT
            RIGHT = UP;

            // LEFT → UP
            UP = tmp;
        }

        // 서 (FRONT, BACK 고정)
        else if (dir == 2) {
            // DOWN → RIGHT
            int tmp = RIGHT;
            RIGHT = DOWN;

            // LEFT → DOWN
            DOWN = LEFT;

            // UP → LEFT
            LEFT = UP;

            // RIGHT → UP
            UP = tmp;
        }

        // 북 (LEFT, RIGHT 고정)
        else if (dir == 3) {
            // UP → BACK
            int tmp = BACK;
            BACK = UP;

            // FRONT → UP
            UP = FRONT;

            // DOWN → FRONT
            FRONT = DOWN;

            // BACK → DOWN
            DOWN = tmp;
        }

        // 남 (LEFT, RIGHT 고정)
        else {
            // UP → FRONT
            int tmp = FRONT;
            FRONT = UP;

            // BACK → UP
            UP = BACK;

            // DOWN → BACK
            BACK = DOWN;

            // FRONT → DOWN
            DOWN = tmp;
        }
    }
}