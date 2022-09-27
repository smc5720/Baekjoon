import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int[] dy = {0, 0, -1, 1};
    public static final int[] dx = {-1, 1, 0, 0};
    public static int N;

    public static class Board {
        int[][] map;
        boolean isMoved;
        int moveCnt;

        public Board() {
            map = new int[N][N];
            moveCnt = 0;
        }

        public Board(Board bd) {
            map = new int[N][N];
            moveCnt = bd.moveCnt;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    map[y][x] = bd.map[y][x];
                }
            }
        }

        public int getMaxVal() {
            int maxVal = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] > 0) {
                        maxVal = Math.max(maxVal, map[y][x]);
                    }
                }
            }

            return maxVal;
        }

        public void move(int dir) {
            moveCnt += 1;
            isMoved = false;

            if (dir < 2) {
                for (int y = 0; y < N; y++) {
                    if (dir == LEFT) {
                        for (int x = 0; x < N; x++) {
                            if (map[y][x] > 0) {
                                Point pos = new Point();
                                checkPos(y, x, dir, pos);
                                moveNum(y, x, dir, pos);
                                // printMap();
                            }
                        }
                    } else if (dir == RIGHT) {
                        for (int x = N - 1; x >= 0; x--) {
                            if (map[y][x] > 0) {
                                Point pos = new Point();
                                checkPos(y, x, dir, pos);
                                moveNum(y, x, dir, pos);
                            }
                        }
                    }
                }
            } else {
                if (dir == UP) {
                    for (int y = 0; y < N; y++) {
                        for (int x = 0; x < N; x++) {
                            if (map[y][x] > 0) {
                                Point pos = new Point();
                                checkPos(y, x, dir, pos);
                                moveNum(y, x, dir, pos);
                            }
                        }
                    }
                } else if (dir == DOWN) {
                    for (int y = N - 1; y >= 0; y--) {
                        for (int x = 0; x < N; x++) {
                            if (map[y][x] > 0) {
                                Point pos = new Point();
                                checkPos(y, x, dir, pos);
                                moveNum(y, x, dir, pos);
                            }
                        }
                    }
                }
            }

            cleanMap();
        }

        public void checkPos(int y, int x, int dir, Point pos) {
            pos.y = y;
            pos.x = x;

            if (dir == LEFT) {
                for (int tx = x - 1; tx >= 0; tx--) {
                    if (!outOfMap(y, tx)) {
                        if (map[y][tx] == 0) {
                            pos.x = tx;
                        } else if (map[y][tx] == -1) {
                            pos.x = tx;
                            return;
                        } else if (map[y][tx] == map[y][x]) {
                            pos.x = tx;
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else if (dir == RIGHT) {
                for (int tx = x + 1; tx < N; tx++) {
                    if (!outOfMap(y, tx)) {
                        if (map[y][tx] == 0) {
                            pos.x = tx;
                        } else if (map[y][tx] == -1) {
                            pos.x = tx;
                            return;
                        } else if (map[y][tx] == map[y][x]) {
                            pos.x = tx;
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else if (dir == UP) {
                for (int ty = y - 1; ty >= 0; ty--) {
                    if (!outOfMap(ty, x)) {
                        if (map[ty][x] == 0) {
                            pos.y = ty;
                        } else if (map[ty][x] == -1) {
                            pos.y = ty;
                            return;
                        } else if (map[ty][x] == map[y][x]) {
                            pos.y = ty;
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else if (dir == DOWN) {
                for (int ty = y + 1; ty < N; ty++) {
                    if (!outOfMap(ty, x)) {
                        if (map[ty][x] == 0) {
                            pos.y = ty;
                        } else if (map[ty][x] == -1) {
                            pos.y = ty;
                            return;
                        } else if (map[ty][x] == map[y][x]) {
                            pos.y = ty;
                            return;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        public void moveNum(int y, int x, int dir, Point pos) {
            if (y == pos.y && x == pos.x) {
                return;
            }

            if (map[pos.y][pos.x] == map[y][x]) {
                map[pos.y][pos.x] *= 2;
                map[y][x] = 0;
                map[pos.y + dy[dir] * (-1)][pos.x + dx[dir] * (-1)] = -1;
            } else if (map[pos.y][pos.x] <= 0) {
                map[pos.y][pos.x] = map[y][x];
                map[y][x] = 0;
            }

            isMoved = true;
        }

        public boolean outOfMap(int ty, int tx) {
            return !((0 <= ty) && (ty < N) && (0 <= tx) && (tx < N));
        }

        public void cleanMap() {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] == -1) {
                        map[y][x] = 0;
                    }
                }
            }
        }

        public void printMap() {
            System.out.println();
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    System.out.printf("%-5d ", map[y][x]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        Board board = new Board();

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                board.map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int maxVal = board.getMaxVal();
        Queue<Board> queue = new LinkedList<>();
        queue.add(board);

        while (!queue.isEmpty()) {
            Board bd = queue.poll();
            for (int i = 0; i < 4; i++) {
                Board tmp = new Board(bd);
                tmp.move(i);
                if (tmp.isMoved) {
                    maxVal = Math.max(maxVal, tmp.getMaxVal());
                    if (tmp.moveCnt < 5) {
                        queue.add(tmp);
                    }
                }
            }
        }

        bw.write(String.valueOf(maxVal));
        bw.flush();
        bw.close();
        br.close();
    }
}