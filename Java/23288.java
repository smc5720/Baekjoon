import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int N, M, K, top, front, right, answer;
	public static int[][] map;
	public static final int RIGHT = 0;
	public static final int UP = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;
	public static int[] dy = { 0, -1, 0, 1 };
	public static int[] dx = { 1, 0, -1, 0 };
	public static int dir = RIGHT;
	public static Point pos;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		pos = new Point(1, 1);

		top = 1;
		front = 5;
		right = 3;

		answer = 0;

		for (int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int x = 1; x <= M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			int ty = pos.y + dy[dir];
			int tx = pos.x + dx[dir];

			if (!visitable(ty, tx)) {
				// System.out.printf("(%d, %d)로는 이동할 수 없습니다.\n", ty, tx);

				if (dir == 0) {
					dir = 2;
				} else if (dir == 2) {
					dir = 0;
				} else if (dir == 1) {
					dir = 3;
				} else {
					dir = 1;
				}

				ty = pos.y + dy[dir];
				tx = pos.x + dx[dir];
			}

			// System.out.printf("(%d, %d) → (%d, %d)\n", pos.y, pos.x, ty, tx);
			pos = new Point(tx, ty);

			if (dir == RIGHT) {
				int tmp = top;
				top = 7 - right;
				right = tmp;
			} else if (dir == UP) {
				int tmp = front;
				front = 7 - top;
				top = tmp;
			} else if (dir == LEFT) {
				int tmp = right;
				right = 7 - top;
				top = tmp;
			} else if (dir == DOWN) {
				int tmp = front;
				front = top;
				top = 7 - tmp;
			}

			int score = BFS(pos.y, pos.x);
			// System.out.printf("점수: %d\n", score);

			answer += score;

			int bottom = 7 - top;
			// System.out.printf("주사위 아래: %d\t맵: %d\n", bottom, map[pos.y][pos.x]);

			if (bottom > map[pos.y][pos.x]) {
				int tmp = dir;
				dir -= 1;

				if (dir < 0) {
					dir = 3;
				}
			} else if (bottom < map[pos.y][pos.x]) {
				dir += 1;

				if (dir > 3) {
					dir = 0;
				}
			}
		}

		bw.write(String.valueOf(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	public static boolean visitable(int y, int x) {
		return (1 <= y) && (y <= N) && (1 <= x) && (x <= M);
	}

	public static void printDice() {
		System.out.println();
		System.out.printf("   %d\n", 7 - front);
		System.out.printf("%d  %d  %d\n", 7 - right, top, right);
		System.out.printf("   %d\n", front);
		System.out.printf("   %d\n\n", 7 - top);
	}

	public static int BFS(int y, int x) {
		boolean[][] visited = new boolean[N + 1][M + 1];
		visited[y][x] = true;

		int val = map[y][x];
		int cnt = 0;

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			cnt += 1;

			for (int i = 0; i < 4; i++) {
				int ty = p.y + dy[i];
				int tx = p.x + dx[i];

				if (visitable(ty, tx) && !visited[ty][tx]) {
					if (map[ty][tx] == val) {
						queue.add(new Point(tx, ty));
						visited[ty][tx] = true;
					}
				}
			}
		}

		return val * cnt;
	}
}