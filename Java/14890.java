import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int[] dy = {0, 1};
    public static final int[] dx = {1, 0};
    public static int N, L, answer;
    public static int[][] map;
    public static boolean[][][] build, finalBuild;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        answer = 0;

        map = new int[N][N];
        build = new boolean[N][N][2];
        finalBuild = new boolean[N][N][2];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        loop:
        for (int y = 0; y < N; y++) {
            int tmp = map[y][0];
            int cnt = 1;

            for (int x = 1; x < N; x++) {
                // 경사로를 놓을 필요가 없는 경우
                if (map[y][x] == tmp) {
                    cnt += 1;
                }

                // 오르막 경사로가 필요한 경우
                else if (map[y][x] > tmp) {
                    // 낮은 칸과 높은 칸의 높이 차가 1이 아닌 경우
                    if (map[y][x] - tmp > 1) {
                        initBuild(y, 0, 0);
                        continue loop;
                    }

                    // L개의 칸이 연속되지 않은 경우
                    if (cnt < L) {
                        initBuild(y, 0, 0);
                        continue loop;
                    } else {
                        for (int i = 1; i <= L; i++) {
                            if (build[y][x - i][0]) {
                                initBuild(y, 0, 0);
                                continue loop;
                            } else {
                                build[y][x - i][0] = true;
                            }
                        }
                    }

                    tmp = map[y][x];
                    cnt = 1;
                } else {
                    tmp = map[y][x];
                    cnt = 1;
                }
            }

            tmp = map[y][N - 1];
            cnt = 1;

            for (int x = N - 2; x >= 0; x--) {
                // 경사로를 놓을 필요가 없는 경우
                if (map[y][x] == tmp) {
                    cnt += 1;
                }

                // 오르막 경사로가 필요한 경우
                else if (map[y][x] > tmp) {
                    // 낮은 칸과 높은 칸의 높이 차가 1이 아닌 경우
                    if (map[y][x] - tmp > 1) {
                        initBuild(y, 0, 0);
                        continue loop;
                    }

                    // L개의 칸이 연속되지 않은 경우
                    if (cnt < L) {
                        initBuild(y, 0, 0);
                        continue loop;
                    } else {
                        for (int i = 1; i <= L; i++) {
                            if (build[y][x + i][0]) {
                                initBuild(y, 0, 0);
                                continue loop;
                            } else {
                                build[y][x + i][0] = true;
                            }
                        }
                    }

                    tmp = map[y][x];
                    cnt = 1;
                } else {
                    tmp = map[y][x];
                    cnt = 1;
                }
            }

            setFinalBuild(y, 0, 0);
            answer += 1;
        }

        loop:
        for (int x = 0; x < N; x++) {
            int tmp = map[0][x];
            int cnt = 1;

            for (int y = 1; y < N; y++) {
                // 경사로를 놓을 필요가 없는 경우
                if (map[y][x] == tmp) {
                    cnt += 1;
                }

                // 오르막 경사로가 필요한 경우
                else if (map[y][x] > tmp) {
                    // 낮은 칸과 높은 칸의 높이 차가 1이 아닌 경우
                    if (map[y][x] - tmp > 1) {
                        initBuild(0, x, 1);
                        continue loop;
                    }

                    // L개의 칸이 연속되지 않은 경우
                    if (cnt < L) {
                        initBuild(0, x, 1);
                        continue loop;
                    } else {
                        for (int i = 1; i <= L; i++) {
                            if (build[y - i][x][1]) {
                                initBuild(0, x, 1);
                                continue loop;
                            } else {
                                build[y - i][x][1] = true;
                            }
                        }
                    }

                    tmp = map[y][x];
                    cnt = 1;
                } else {
                    tmp = map[y][x];
                    cnt = 1;
                }
            }

            tmp = map[N - 1][x];
            cnt = 1;

            for (int y = N - 2; y >= 0; y--) {
                // 경사로를 놓을 필요가 없는 경우
                if (map[y][x] == tmp) {
                    cnt += 1;
                }

                // 오르막 경사로가 필요한 경우
                else if (map[y][x] > tmp) {
                    // 낮은 칸과 높은 칸의 높이 차가 1이 아닌 경우
                    if (map[y][x] - tmp > 1) {
                        initBuild(0, x, 1);
                        continue loop;
                    }

                    // L개의 칸이 연속되지 않은 경우
                    if (cnt < L) {
                        initBuild(0, x, 1);
                        continue loop;
                    } else {
                        for (int i = 1; i <= L; i++) {
                            if (build[y + i][x][1]) {
                                initBuild(0, x, 1);
                                continue loop;
                            } else {
                                build[y + i][x][1] = true;
                            }
                        }
                    }

                    tmp = map[y][x];
                    cnt = 1;
                } else {
                    tmp = map[y][x];
                    cnt = 1;
                }
            }

            setFinalBuild(0, x, 1);
            answer += 1;
        }

        // printMap();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void initBuild(int y, int x, int d) {
        for (int i = 0; i < N; i++) {
            int ty = y + dy[d] * i;
            int tx = x + dx[d] * i;
            build[ty][tx][d] = finalBuild[ty][tx][d];
        }
    }

    public static void setFinalBuild(int y, int x, int d) {
        for (int i = 0; i < N; i++) {
            int ty = y + dy[d] * i;
            int tx = x + dx[d] * i;
            finalBuild[ty][tx][d] = build[ty][tx][d];
        }
    }

    public static void printMap() {
        System.out.println();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.printf("%d ", map[y][x]);
            }

            System.out.printf("\t\t\t");

            for (int x = 0; x < N; x++) {
                System.out.printf("%s ", finalBuild[y][x][0] ? (finalBuild[y][x][1] ? "■ " : "□ ") : "□ ");
            }

            System.out.println();
        }
        System.out.println();
    }
}