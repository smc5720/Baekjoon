import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int[] dr = {-1, 0, 1, 0};
    public static final int[] dc = {0, 1, 0, -1};
    public static boolean isOver;
    public static int N, M, r, c, d, cnt;
    public static boolean[][] cleaned;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        isOver = false;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cleaned = new boolean[N][M];

        st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        cnt = 0;

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        while (!isOver) {
            clean();
            search();
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 현재 위치를 청소한다.
     */
    public static void clean() {
        if (!cleaned[r][c]) {
            cnt += 1;
            cleaned[r][c] = true;
        }
    }

    /**
     * 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색을 진행한다.
     */
    public static void search() {
        // 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색을 진행
        for (int i = 0; i < 4; i++) {
            // 왼쪽으로 방향 전환
            int td = (d + 3) % 4;
            int tr = r + dr[td];
            int tc = c + dc[td];

            if (visitable(tr, tc) && !cleaned[tr][tc]) {
                // 그 방향으로 회전한 다음 한 칸을 전진
                d = td;
                r = tr;
                c = tc;
                return;
            }

            // 왼쪽 방향에 청소할 공간이 없다면
            else {
                // 그 방향으로 회전한다.
                d = td;
            }
        }

        // 네 방향 모두 청소가 이미 되어있거나 벽인 경우
        int td = (d + 2) % 4;
        int tr = r + dr[td];
        int tc = c + dc[td];

        // 뒤쪽 방향이 벽이 아니라면
        if (visitable(tr, tc)) {
            // 바라보는 방향을 유지한 채로 한 칸 후진
            r = tr;
            c = tc;
        }

        // 뒤쪽 방향이 벽이라면
        else {
            // 작동을 멈춘다.
            isOver = true;
        }
    }

    public static boolean visitable(int tr, int tc) {
        return (0 <= tr) && (tr < N) && (0 <= tc) && (tc < M) && map[tr][tc] == 0;
    }
}