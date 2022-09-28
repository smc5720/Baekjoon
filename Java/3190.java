import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int[] dy = {0, 1, 0, -1};
    public static final int[] dx = {1, 0, -1, 0};
    public static Queue<Point> snake;
    public static Point head;
    public static int[][] board;
    public static int N, K, L, d, moveCnt;
    public static boolean gameOver;

    public static class Moving {
        int second;
        char dir;

        public Moving(int X, char C) {
            second = X;
            dir = C;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        d = 0;
        moveCnt = 0;

        board = new int[N + 1][N + 1];
        board[1][1] = 2;

        head = new Point(1, 1);
        snake = new LinkedList<>();
        snake.add(new Point(1, 1));

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            board[y][x] = 1;
        }

        L = Integer.parseInt(br.readLine());
        Moving[] moving = new Moving[L];

        for (int l = 0; l < L; l++) {
            st = new StringTokenizer(br.readLine(), " ");
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            moving[l] = new Moving(X, C);
        }

        // printBoard();

        loop:
        for (Moving move : moving) {
            for (int i = moveCnt; i < move.second; i++) {
                // 이동
                moveSnake();
                // printBoard();

                if (gameOver) {
                    break loop;
                }
            }

            // 회전
            if (move.dir == 'L') {
                d = (d + 3) % 4;
            } else {
                d = (d + 1) % 4;
            }
        }

        while (!gameOver) {
            moveSnake();
        }

        bw.write(String.valueOf(moveCnt));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void moveSnake() {
        int ty = head.y + dy[d];
        int tx = head.x + dx[d];
        moveCnt += 1;

        if (visitable(ty, tx)) {
            // 헤드 위치 설정
            head.y = ty;
            head.x = tx;
            // 뱀이 빈 공간으로 이동한 경우
            if (board[ty][tx] == 0) {
                // snake 꼬리 빼기
                Point tail = snake.poll();
                board[tail.y][tail.x] = 0;
                // System.out.printf("꼬리: (%d, %d) = %d\n", tail.y, tail.x, board[tail.y][tail.x]);
            }
            // 보드에 헤드 표시
            board[ty][tx] = 2;
            // System.out.printf("머리: (%d, %d) = %d\n", ty, tx, board[ty][tx]);
            // snake 몸 추가
            snake.add(new Point(tx, ty));
        } else {
            // 게임 종료
            gameOver = true;
        }
    }

    public static boolean visitable(int y, int x) {
        return (1 <= y) && (y <= N) && (1 <= x) && (x <= N) && (board[y][x] <= 1);
    }

    public static void printBoard() {
        System.out.println("moveCnt = " + moveCnt);
        System.out.println();

        for (int y = 0; y <= N + 1; y++) {
            for (int x = 0; x <= N + 1; x++) {
                if (y == N + 1 || x == N + 1 || y == 0 || x == 0) {
                    System.out.printf("■ ");
                    continue;
                }

                if (board[y][x] == 0) {
                    System.out.printf("  ");
                } else if (board[y][x] == 1) {
                    System.out.printf("# ");
                } else {
                    System.out.printf("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}