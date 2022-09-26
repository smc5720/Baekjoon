import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int RED = 0;
    public static final int BLUE = 1;
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static int[] dirY = {0, -1, 0, 1};
    public static int[] dirX = {-1, 0, 1, 0};
    public static char[][] map;
    public static int N, M;

    public static class Case {
        Ball[] ball;
        int count;

        public Case() {
            ball = new Ball[2];
            count = 0;
        }

        public Case(Case cs) {
            ball = new Ball[2];
            count = cs.count;
            ball[RED] = new Ball(cs.ball[RED].y, cs.ball[RED].x, RED);
            ball[BLUE] = new Ball(cs.ball[BLUE].y, cs.ball[BLUE].x, BLUE);
        }

        public void rotateMap(int dir) {
            count += 1;
            ball[RED].moved = false;
            ball[BLUE].moved = false;

            if (dir == LEFT) {
                if (ball[RED].x < ball[BLUE].x) {
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                } else {
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                }
            } else if (dir == RIGHT) {
                if (ball[RED].x > ball[BLUE].x) {
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                } else {
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                }
            } else if (dir == UP) {
                if (ball[RED].y < ball[BLUE].y) {
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                } else {
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                }
            } else if (dir == DOWN) {
                if (ball[RED].y > ball[BLUE].y) {
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                } else {
                    ball[BLUE].move(dir, ball[RED].y, ball[RED].x, ball[RED].alive);
                    ball[RED].move(dir, ball[BLUE].y, ball[BLUE].x, ball[BLUE].alive);
                }
            }
        }

        public void printMap() {
            System.out.println();

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (ball[RED].alive && ball[RED].y == y && ball[RED].x == x) {
                        System.out.printf("R");
                    } else if (ball[BLUE].alive && ball[BLUE].y == y && ball[BLUE].x == x) {
                        System.out.printf("B");
                    } else {
                        System.out.printf("%c", map[y][x]);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static class Ball {
        int y, x, dy, dx, color;
        boolean alive, moved;

        public Ball(int y, int x, int color) {
            this.y = y;
            this.x = x;
            this.color = color;
            alive = true;
        }

        public void move(int dir, int notY, int notX, boolean notAlive) {
            if (alive == false) {
                return;
            }

            checkDir(dir, notY, notX, notAlive);
            map[y][x] = '.';

            // map에서 공을 옮긴다.
            if (alive == true) {
                y = dy;
                x = dx;

                if (color == RED) {
                    // System.out.printf("빨간색 공은 %d, %d로 이동\n", dy, dx);
                } else {
                    // System.out.printf("파란색 공은 %d, %d로 이동\n", dy, dx);
                }
            }
        }

        // 현재 좌표에서 이동 가능한 가장 먼 빈 좌표를 찾는다.
        public void checkDir(int dir, int notY, int notX, boolean notAlive) {
            dy = y;
            dx = x;

            for (int i = 1; i < (dir % 2 == 0 ? M : N); i++) {
                int ty = y + dirY[dir] * i;
                int tx = x + dirX[dir] * i;

                if (visitable(ty, tx) && (map[ty][tx] == '.' || map[ty][tx] == 'O') && (!notAlive || (notY != ty || notX != tx))) {
                    dy = ty;
                    dx = tx;
                    moved = true;

                    if (map[ty][tx] == 'O') {
                        alive = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        // 해당 좌표가 맵 밖을 벗어나지 않는지 확인한다.
        public boolean visitable(int ty, int tx) {
            return (0 <= ty) && (ty < N) && (0 <= tx) && (tx < M);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Case initCase = new Case();
        map = new char[N][M];

        for (int y = 0; y < N; y++) {
            String str = br.readLine();

            for (int x = 0; x < M; x++) {
                map[y][x] = str.charAt(x);

                if (map[y][x] == 'R') {
                    map[y][x] = '.';
                    initCase.ball[RED] = new Ball(y, x, RED);
                } else if (map[y][x] == 'B') {
                    map[y][x] = '.';
                    initCase.ball[BLUE] = new Ball(y, x, BLUE);
                }
            }
        }

        Queue<Case> queue = new LinkedList<>();
        queue.add(initCase);

        int answer = -1;

        while (!queue.isEmpty() && answer == -1) {
            Case cs = queue.poll();

            for (int i = 0; i < 4; i++) {
                Case tmp = new Case(cs);
                tmp.rotateMap(i);

                // 파란 구슬이 빠졌다면 다음 경우를 생각하지 않아도 된다.
                if (!tmp.ball[BLUE].alive) {
                    continue;
                }

                // 빨간 구슬만 빠졌다면 상황이 종료된 것이다.
                if (!tmp.ball[RED].alive) {
                    answer = answer == -1 ? tmp.count : Math.min(answer, tmp.count);
                    continue;
                }

                // 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 다음 경우를 생각하지 않아도 된다.
                if (tmp.count == 10) {
                    continue;
                }

                if (!tmp.ball[RED].moved && !tmp.ball[BLUE].moved) {
                    continue;
                }

                queue.add(tmp);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}